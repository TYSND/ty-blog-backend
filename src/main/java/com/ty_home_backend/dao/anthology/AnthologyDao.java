package com.ty_home_backend.dao.anthology;

import com.ty_home_backend.dao.CRUDDao;
import com.ty_home_backend.model.anthology.AnthologyModel;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnthologyDao extends CRUDDao {
    List<AnthologyModel> getAllAnthology();
}
