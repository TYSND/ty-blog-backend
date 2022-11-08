package com.ty_home_backend.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.ty_home_backend.model.CRUDModel;

public interface CRUDDao {
    public List<CRUDModel> getAll();
    public List<CRUDModel> getPage(@Param("offset") int offset, @Param("limit") int limit);
    public int create(CRUDModel crudModel);
    public int update(CRUDModel crudModel);
    public CRUDModel getById(@Param("id") int id);
    public int deleteById(int id);
    public int getCount();
}
