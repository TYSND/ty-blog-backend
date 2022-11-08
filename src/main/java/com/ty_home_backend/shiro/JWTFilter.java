package com.ty_home_backend.shiro;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ty_home_backend.JWT.JWTUtils;
import com.ty_home_backend.JWT.token.Token;
import com.ty_home_backend.utils.JsonUtils;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseFailMessage;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;
import lombok.var;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends BasicHttpAuthenticationFilter {
    private Logger logger = LoggerFactory.getLogger(JWTFilter.class.getName());


    /* 拦截请求，检查JWT合法性和用户身份*/
    /**
     * basicauthenticationfilter不会抛出TokenExpiredException，
     * 而是把所有exception统一抛出AuthenticationException
     * 所以不能通过抛出TokenExpiredException与其他exception分类
     * 解决办法：通过e.getMessage判断是哪种exception，并相应处理
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //未登录也允许访问，但在接口处控制权限
        if (isLoginAttempt(request,response)){
            try {
                executeLogin(request, response);
                return true;
            } catch (JWTDecodeException e){
                responseAccessDenied(request,response);
            } catch (AuthenticationException e){
                return true;
//                if (Constants.NEED_REFRESH_CODE.equals(e.getMessage())) {
//                    responseRefreshToken(request, response);
//                    return false;
//                } else {
////                    responseAccessDenied(request, response);
//                    return true;
//                }
            }
        }
        else {
            responseAccessDenied(request,response);
            return false;
        }
        return true;
    }

    /* 检查Http请求Authorization头是否有JWT*/
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req=(HttpServletRequest) request;
        String token=req.getHeader("Authorization");
        return token!=null;
    }

    /* 取出JWT，交给ShiroRealm验证登录*/
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws AuthenticationException, TokenExpiredException {
        var req=(HttpServletRequest)request;
        String token=req.getHeader("Authorization");
        Token jwtToken= JWTUtils.BuildTokenObject(token);
        /* ShiroRealm验证JWT，若失败将抛出AuthenticationException，交由isAccessAllowed处理*/
        SecurityUtils.getSubject().login(jwtToken);
        return true;
    }

    /**
     * 当filter的isAccessAllowed结果为false，shiro自动调用BasicHttpAuthenticationFilter的onAccessdenied方法；
     * 根据官网代码https://shiro.apache.org/static/1.4.1/apidocs/src-html/org/apache/shiro/web/filter/authc/BasicHttpAuthenticationFilter.html#line.242
     * BasicHttpAuthenticationFilter的onAccessDenied方法会再执行executeLogin方法，即尝试重新登录，所以请求错误的异常
     * 会未经过catch（我们自己的filter的catch处理）直接写入response。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

    /**
     * 请求被拒绝，重定向，返回异常结果
     * 此处大坑：正常http请求可以通过sendRedirect让浏览器重定向访问
     * 但ajax请求（例如postman的请求）客户端不会重定向，所以不能重定向至controller层的
     * 401方法处理异常。
     * 解决办法：判断请求是否为ajax；是，则手动调用401方法，编写异常结果，转成json字符串后用
     * response的getWriter写入。若不是，可以直接sendRedirect。
     */
    private void responseAccessDenied(ServletRequest request, ServletResponse response){
        try {
            HttpServletResponse httpServletResponse= WebUtils.toHttp(response);
//            String json= JsonUtils.writer.writeValueAsString(new ResponseFailMessage("invalid access token"));
            String json= JsonUtils.writer.writeValueAsString(new ResponseMessage("401",null,"need login"));
            httpServletResponse.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* token过期，让前端重新请求*/
    private void responseRefreshToken(ServletRequest request, ServletResponse response){
        var resp =(HttpServletResponse) response;
        try {
            HttpServletResponse httpServletResponse=WebUtils.toHttp(response);
            String json=JsonUtils.writer.writeValueAsString
                    (new ResponseMessage("401",null,"need login"));
            httpServletResponse.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}