package com.ty_home_backend.service.Impl.picture;

import com.ty_home_backend.JWT.JWTUtils;
import com.ty_home_backend.JWT.token.AccessToken;
import com.ty_home_backend.dao.picture.PictureDao;
import com.ty_home_backend.model.picture.PictureModel;
import com.ty_home_backend.service.Impl.CRUDServiceImpl;
import com.ty_home_backend.service.picture.PictureService;
import com.ty_home_backend.utils.Constants.Enums;
import com.ty_home_backend.utils.File.MyFileUtils;
import com.ty_home_backend.utils.Response.DataObj;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseFailMessage;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseSuccessMessage;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class PictureServiceImpl extends CRUDServiceImpl implements PictureService {
    @Resource
    PictureDao pictureDao;

    public PictureServiceImpl(PictureDao pictureDao) {
        super(pictureDao);
    }

    public ResponseMessage uploadPicture(MultipartFile picture) {
        System.out.println(picture.getSize());
        // 检查图片大小
        if (picture.getSize() > 1024*1024*4) {
            return new ResponseFailMessage("图片不能超过4M");
        }
        String md5= null;
        try {
            // 生成32位随机字符串
            md5 = MyFileUtils.getByteArrayMd5Hex(picture.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseFailMessage(e.getMessage());
        }
        String type = FilenameUtils.getExtension(picture.getOriginalFilename());
        Date date = new Date();
        PictureModel pictureModel = new PictureModel(
                new File(MyFileUtils.getImageStorePath(md5, date) + "." + type),
                md5,
                date,
                picture.getSize(),
                Enums.picType.valueOf(type)
        );
        if (pictureDao.getPictureByMd5(md5) != null) {
            return new ResponseMessage("200", new DataObj(md5), "image already exist");
        }
        return trySavePicture(pictureModel, picture);

    }
    public byte[] getPictureByteArrayByMd5(String md5) throws IOException {
        PictureModel pictureModel = pictureDao.getPictureByMd5(md5);
        return MyFileUtils.readFileByteArray(pictureModel.getPath());
    }

    private ResponseMessage trySavePicture(PictureModel pictureModel, MultipartFile multipartFile) {
        pictureDao.uploadPicture(pictureModel);
        try {
            MyFileUtils.saveFileToLocal(pictureModel.getPath(), multipartFile);
            return new ResponseSuccessMessage(new DataObj(pictureModel.getMd5()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseFailMessage(e.getMessage());
        }
    }
}
