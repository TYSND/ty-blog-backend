package com.ty_home_backend.JWT.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

public class AccessToken extends Token{
    private Integer userId;
    private Date expiresAt;
    public AccessToken(String token) {
        super(token);
    }
    public AccessToken(int userId, Date expiresAt) {
        super(null);
        this.userId = userId;
        this.expiresAt = expiresAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }


    /* 解析类的token字符串，更新类的属性*/
    private void parseTokenClaims(){
        DecodedJWT decodedJWT= JWT.decode(token);
        Map<String, Claim> claims=decodedJWT.getClaims();

        userId=claims.get("userId").asInt();
        expiresAt=claims.get("expiresAt").asDate();
    }

    /* 验证token真实性*/
    public boolean verify(String secret){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userId",userId)
                    .withClaim("expiresAt",expiresAt)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /* 生成新token字符串并签名*/
    public String sign(String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("userId",userId)
                    .withClaim("expiresAt",expiresAt)
                    .sign(algorithm);
        } catch (IllegalArgumentException | JWTCreationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
