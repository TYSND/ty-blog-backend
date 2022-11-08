package com.ty_home_backend.controller;

import com.ty_home_backend.model.account.LoginParamModel;
import com.ty_home_backend.service.account.AccountService;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
@CrossOrigin("*")
@Api(tags = "账号相关")
public class AccountController {
    @Resource
    AccountService accountService;

    @PostMapping("/login")
    public ResponseMessage login(@RequestBody LoginParamModel loginParamModel) {
        return accountService.login(loginParamModel);
    }

    @PostMapping("/register")
    public ResponseMessage register(@RequestBody LoginParamModel registerParamModel) {
        return accountService.register(registerParamModel);
    }
}
