package com.ty_home_backend.service.picture;

import com.ty_home_backend.service.CRUDService;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService extends CRUDService {
    ResponseMessage uploadPicture(MultipartFile multipartFile);
    byte[] getPictureByteArrayByMd5(String md5) throws IOException;
}
