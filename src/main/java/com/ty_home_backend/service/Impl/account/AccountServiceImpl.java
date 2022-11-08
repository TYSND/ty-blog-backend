package com.ty_home_backend.service.Impl.account;

import com.ty_home_backend.JWT.token.AccessToken;
import com.ty_home_backend.dao.account.AccountDao;
import com.ty_home_backend.model.account.AccountInfoModel;
import com.ty_home_backend.model.account.AccountModel;
import com.ty_home_backend.model.account.LoginParamModel;
import com.ty_home_backend.service.Impl.CRUDServiceImpl;
import com.ty_home_backend.service.account.AccountService;
import com.ty_home_backend.shiro.SecureUtils;
import com.ty_home_backend.utils.Response.DataObj;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseFailMessage;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseSuccessMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

@Service
public class AccountServiceImpl extends CRUDServiceImpl implements AccountService {
    @Resource
    AccountDao accountDao;
    @Resource
    SecureUtils secureUtils;
    @Resource
    PlatformTransactionManager platformTransactionManager;

    int ExpireTime = 2*7*24*60*60*1000;

    public AccountServiceImpl(AccountDao accountDao) {
        super(accountDao);
    }

    public ResponseMessage login(LoginParamModel loginParamModel) {
        try {
            AccountInfoModel accountInfoModel = accountDao.getByAccount(loginParamModel.getAccount());
            AccountModel accountModel = (AccountModel)accountDao.getById(accountInfoModel.getUserId());
            // 生成新token
            if (secureUtils.passwordMatch(accountModel, loginParamModel.getPassword())) {
                AccessToken accessToken = new AccessToken(accountModel.getUserId(), new Date(System.currentTimeMillis() + ExpireTime));
                String accessTokenString = accessToken.sign(accountModel.getJwtSecret());
                HashMap<String, Object> response = new HashMap<>();
                response.put("accessToken", accessTokenString);
                return new ResponseSuccessMessage(new DataObj(response));
            } else {
                return new ResponseFailMessage("invalid login info");
            }
        } catch (Exception e) {
            return new ResponseFailMessage("invalid login info");
        }
    }

    public ResponseMessage register(LoginParamModel registerParamModel) {
        if (registerParamModel.getAccount() == "" || registerParamModel.getPassword() == "") {
            return new ResponseFailMessage("Invalid register info");
        }
        System.out.println("////////////////////////////// " + accountDao.getByAccount("ty"));
        if (accountDao.getByAccount(registerParamModel.getAccount()) != null) {
            return new ResponseFailMessage("account already exist");
        }
        // 事务
        TransactionStatus tx= platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            AccountModel accountModel = secureUtils.saltAndHashPwd(registerParamModel.getPassword());
            accountModel.setJwtSecret(secureUtils.getNewSecret());
            accountModel.setAccount(registerParamModel.getAccount());

            accountDao.create(accountModel);
            platformTransactionManager.commit(tx);
            return new ResponseSuccessMessage(new DataObj(accountModel));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            platformTransactionManager.rollback(tx);
            return new ResponseFailMessage("register failed");
        }
    }
}
