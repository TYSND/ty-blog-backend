package com.ty_home_backend.controller;

import com.ty_home_backend.service.anthology.AnthologyService;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/anthology")
@CrossOrigin("*")
@Api(tags = "文集相关")
public class AnthologyController {
    @Resource
    AnthologyService anthologyService;

    @GetMapping("/get-all-anthology")
    public ResponseMessage getAllAnthology() {
        return anthologyService.getAllAnthology();
    }

}
