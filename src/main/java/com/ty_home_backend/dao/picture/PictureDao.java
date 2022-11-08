package com.ty_home_backend.dao.picture;

import com.ty_home_backend.dao.CRUDDao;
import com.ty_home_backend.model.picture.PictureModel;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PictureDao extends CRUDDao {
    int uploadPicture(PictureModel pictureModel);
    PictureModel getPictureByMd5(String md5);
}
