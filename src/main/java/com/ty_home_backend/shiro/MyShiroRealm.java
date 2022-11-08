package com.ty_home_backend.shiro;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ty_home_backend.JWT.token.AccessToken;
import com.ty_home_backend.dao.account.AccountDao;
import com.ty_home_backend.model.account.AccountModel;
import lombok.var;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Date;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    AccountDao accountDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AccessToken token= (AccessToken) principals.getPrimaryPrincipal();

        var simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException, TokenExpiredException {
        AccessToken accessToken = (AccessToken) token;
        Integer userId = accessToken.getUserId();
        //查出jwtSecret，验证
        AccountModel account = (AccountModel) accountDao.getById(userId);
        String jwtSecret = account.getJwtSecret();
        if (!accessToken.verify(jwtSecret))
            throw new AuthenticationException("invalid token");
        //检查token过期日期，用户权限更新日期
        Date expiresAt = accessToken.getExpiresAt();
        if (expiresAt.before(new Date()))
            throw new AuthenticationException("401");
        // 通过验证
        return new SimpleAuthenticationInfo(token, token, MyShiroRealm.class.getName());
    }
}
