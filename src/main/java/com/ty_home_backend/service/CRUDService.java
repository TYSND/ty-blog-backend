package com.ty_home_backend.service;

import com.ty_home_backend.model.CRUDModel;
import com.ty_home_backend.utils.Response.Pager;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;

public interface CRUDService {
    public ResponseMessage getAll();
    public ResponseMessage getPage(Pager pager);
    public ResponseMessage getById(int id);
    public ResponseMessage create(CRUDModel crudModel);
    public ResponseMessage delete(int id);
    public ResponseMessage update(CRUDModel crudModel);
}
