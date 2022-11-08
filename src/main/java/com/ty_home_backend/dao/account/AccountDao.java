package com.ty_home_backend.dao.account;

import com.ty_home_backend.dao.CRUDDao;
import com.ty_home_backend.model.account.AccountInfoModel;
import com.ty_home_backend.model.account.AccountModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface AccountDao extends CRUDDao {
    AccountInfoModel getByAccount(String account);
}
