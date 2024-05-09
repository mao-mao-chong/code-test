package org.com.bmw.security;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.com.bmw.model.AdminUser;
import org.com.bmw.model.Permission;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.service.AdminTokenService;
import org.com.bmw.service.UserService;
import org.com.bmw.util.Constant;
import org.com.bmw.util.CookieUtils;
import org.com.bmw.util.ResponseUtil;
import org.com.bmw.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@Slf4j
public class SecurityHandlerConfig {

    @Autowired
    private AdminTokenService tokenService;
    @Autowired
    private UserService userService;
    /**
     * 登陆成功，返回Token
     *
     * @return
     */
    @Bean(name = "authenticationSuccessHandler")
    public AuthenticationSuccessHandler loginSuccessHandler() {
        log.info("----------11 11 11---------------");

        return (request, response, authentication) -> {
            AdminUser loginUser = (AdminUser) authentication.getPrincipal();
            Token token = tokenService.saveToken(loginUser);
            List<Permission> permissions = userService.selectPermissionList(loginUser.getId());
            Map result = new HashMap();
            result.put("permission",permissions);
            result.put("token",token);
            ReturnMsg ok = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage(),result);
            ResponseUtil.responseJson(request, response, HttpStatus.OK.value(), ok);
        };
    }
//
    @Bean(name = "formAuthenticationSuccessHandler")
    public AuthenticationSuccessHandler formLoginSuccessHandler() {
        log.info("----------12121212---------------");

        return (request, response, authentication) -> {
            AdminUser loginUser = (AdminUser) authentication.getPrincipal();
            Token token = tokenService.saveToken(loginUser);
            //实际项目token要设置超时时间和path,此处我们是模拟，就先不设置了
            CookieUtils.setCookie(response, "token" , token.getToken());
            log.info(JSON.toJSONString(token));
            ResponseUtil.responseRedirectUrl(response, "/product/listPageSeckillProducts");
        };
    }


    /**
     * 登陆失败
     *
     * @return
     */
    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {

        return (request, response, exception) -> {
            ReturnMsg info;
            log.error("", exception);
            if (exception instanceof BadCredentialsException) {
                info = new ReturnMsg(Constant.LOGIN_PASS_ERROR.getCode(),Constant.LOGIN_PASS_ERROR.getMessage());
            } else if (exception.getCause() instanceof BaseAuthenticationException) {
                BaseAuthenticationException e = (BaseAuthenticationException) exception.getCause();
                info = e.resp();
            } else if (exception instanceof BaseAuthenticationException) {
                BaseAuthenticationException e = (BaseAuthenticationException) exception;
                info = e.resp();
            } else if (exception instanceof DisabledException) {
                info = new ReturnMsg(Constant.ACCOUNT_DISABLE.getCode(),Constant.ACCOUNT_DISABLE.getMessage());

            } else {
                info = new ReturnMsg(Constant.FAILED.getCode(),Constant.FAILED.getMessage());
            }

            ResponseUtil.responseJson(request, response, HttpStatus.OK.value(), info);
        };
    }

    /**
     * 退出处理
     *
     * @return
     */
    @Bean
    public LogoutSuccessHandler logoutSussHandler() {

        return (request, response, authentication) -> {
            String token = TokenFilter.getToken(request);
            tokenService.deleteToken(token);
            ResponseUtil.responseJson(request, response, HttpStatus.OK.value(), new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage()));
        };

    }

    /**
     * 未登录，返回401
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {

        return (request, response, authException) -> {
            log.error("", authException);
            ReturnMsg resp = new ReturnMsg(Constant.NO_LOGIN.getCode(),Constant.NO_LOGIN.getMessage());
            ResponseUtil.responseJson(request, response, HttpStatus.OK.value(), resp);
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            log.error("", accessDeniedException);
            ReturnMsg resp = new ReturnMsg(Constant.USER_NO_PERMISSION.getCode(),Constant.USER_NO_PERMISSION.getMessage());
            ResponseUtil.responseJson(request, response, HttpStatus.OK.value(), resp);
        };
    }

}
