package com.ty_home_backend.shiro;

import com.ty_home_backend.model.account.AccountModel;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.springframework.stereotype.Component;

@Component
public class SecureUtils {
    private String getNewSalt(){
        return new SecureRandomNumberGenerator().nextBytes(32).toHex();
    }
    public String getNewSecret(){return new SecureRandomNumberGenerator().nextBytes(32).toHex(); }

    private String hashPwd(String plainPwd,String salt){
        return new Sha512Hash(plainPwd,salt, 1000).toHex();
    }
    /* 输入密码和盐，验证*/
    public boolean passwordMatch(AccountModel accountModel, String plainPwd){
        return hashPwd(plainPwd,accountModel.getPwdSalt()).equals(accountModel.getHashedPwd());
    }
    /* 为密码生成新盐，并哈希，返回两个结果*/
    public AccountModel saltAndHashPwd(String plainPwd){
        String newSalt = getNewSalt();
        String hashedPwd = hashPwd(plainPwd,newSalt);
        AccountModel accountModel = new AccountModel();
        accountModel.setPwdSalt(newSalt);
        accountModel.setHashedPwd(hashedPwd);
        return accountModel;
    }
}
