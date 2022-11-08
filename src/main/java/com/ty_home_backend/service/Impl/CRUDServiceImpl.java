package com.ty_home_backend.service.Impl;

import com.ty_home_backend.dao.CRUDDao;
import com.ty_home_backend.model.CRUDModel;
import com.ty_home_backend.service.CRUDService;
import com.ty_home_backend.utils.Response.DataObj;
import com.ty_home_backend.utils.Response.Pager;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseFailMessage;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseSuccessMessage;

import java.util.List;

public class CRUDServiceImpl implements CRUDService {
    CRUDDao crudDao;

    public CRUDServiceImpl(CRUDDao crudDao) {
        this.crudDao=crudDao;
    }

    public ResponseMessage getAll() {
        List<CRUDModel> res=  crudDao.getAll();
        return new ResponseSuccessMessage(new DataObj(res,res.size()));
    }

    public ResponseMessage getPage(Pager pager){
        List<CRUDModel> res=crudDao.getPage(pager.getOffset(),pager.getLimit());
        int totalNum=crudDao.getCount();
        return new ResponseSuccessMessage(new DataObj(res,totalNum));
    }

    @Override
    public ResponseMessage getById(int id) {
        CRUDModel res=crudDao.getById(id);
        return new ResponseSuccessMessage(new DataObj(res));
    }

    public ResponseMessage create(CRUDModel crudModel){
        if (crudDao.getById(crudModel.getId())!=null){
            return new ResponseFailMessage("id already exists");
        }
        if (crudDao.create(crudModel)==0){
            return new ResponseFailMessage("create failed");
        }
        return new ResponseSuccessMessage();
    }

    public ResponseMessage delete(int id) {
        if (crudDao.deleteById(id)==0){
            return new ResponseFailMessage("delete by id failed");
        }
        return new ResponseSuccessMessage();
    }

    public ResponseMessage update(CRUDModel crudModel) {
        if (crudDao.getById(crudModel.getId())==null){
            return new ResponseFailMessage("no such id");
        }
        if (crudDao.update(crudModel)==0){
            return new ResponseFailMessage("update failed");
        }else
            return new ResponseSuccessMessage();
    }
}
