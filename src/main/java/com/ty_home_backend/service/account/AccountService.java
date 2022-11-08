package com.ty_home_backend.service.account;

import com.ty_home_backend.model.account.LoginParamModel;
import com.ty_home_backend.service.CRUDService;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;

public interface AccountService extends CRUDService {
    ResponseMessage login(LoginParamModel loginParamModel);
    ResponseMessage register(LoginParamModel registerParamModel);
}
