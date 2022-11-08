package com.ty_home_backend.JWT.token;

import org.apache.shiro.authc.AuthenticationToken;

public class Token implements AuthenticationToken {
    protected String token;
    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public Object getCredentials() {
        return this;
    }
}
