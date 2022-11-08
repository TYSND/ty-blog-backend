package com.ty_home_backend.JWT;

import com.ty_home_backend.JWT.token.Token;
import org.apache.shiro.SecurityUtils;

public class JWTUtils {
    public static Token BuildTokenObject(String token) {
        return new Token(token);
    }
    /*获取当前用户jwtToken对象*/
    public static Token getCurrentJwtToken(){
        Token token= (Token) SecurityUtils.getSubject().getPrincipal();
        return BuildTokenObject(token.getToken());
    }
}
