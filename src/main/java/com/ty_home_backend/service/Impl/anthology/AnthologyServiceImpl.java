package com.ty_home_backend.service.Impl.anthology;

import com.ty_home_backend.dao.anthology.AnthologyDao;
import com.ty_home_backend.service.Impl.CRUDServiceImpl;
import com.ty_home_backend.service.anthology.AnthologyService;
import com.ty_home_backend.utils.Response.DataObj;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseSuccessMessage;
import lombok.var;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AnthologyServiceImpl extends CRUDServiceImpl implements AnthologyService {
    @Resource
    AnthologyDao anthologyDao;

    public AnthologyServiceImpl(AnthologyDao anthologyDao) {super(anthologyDao);}

    @Override
    public ResponseMessage getAllAnthology() {
        var res = anthologyDao.getAllAnthology();
        return new ResponseSuccessMessage(new DataObj(res));
    }
}
