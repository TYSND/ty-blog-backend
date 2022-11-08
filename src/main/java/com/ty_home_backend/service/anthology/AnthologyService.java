package com.ty_home_backend.service.anthology;

import com.ty_home_backend.service.CRUDService;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;

public interface AnthologyService extends CRUDService {
    ResponseMessage getAllAnthology();
}
