package com.ty_home_backend.controller;

import com.ty_home_backend.service.picture.PictureService;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/picture")
@CrossOrigin("*")
@Api(tags="图片相关")
public class PictureController {
    @Resource
    PictureService pictureService;

    @PostMapping("/upload-picture")
    public ResponseMessage uploadPicture(@RequestBody MultipartFile picture) {
        return pictureService.uploadPicture(picture);
    }

    @GetMapping(value = "/get-picture", produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_GIF_VALUE})
    public byte[] getPicture(@RequestParam String md5) throws IOException {
        return pictureService.getPictureByteArrayByMd5(md5);
    }
}
