package org.com.bmw.service.impl;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.com.bmw.model.AdminUser;
import org.com.bmw.model.User;
import org.com.bmw.service.AdminTokenService;
import org.com.bmw.util.JWTUtils;
import org.com.bmw.util.RedisUtil;
import org.com.bmw.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class AdminTokenServiceImpl implements AdminTokenService {

    @Value("${token.expire.seconds}")
    private Integer expireSeconds;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public AdminUser getLoginUser(String token) {
        //解析token
        Claims claims = JWTUtils.getClaimsBody(token);
        Integer userId = (Integer) claims.get("id");
        Object value = redisUtil.get("user:"+userId);
        if (value == null) {
            return null;
        }
        return JSON.parseObject(value.toString(), AdminUser.class);
    }

    @Override
    public Token saveToken(AdminUser loginUser) {
        String token = JWTUtils.createToken(loginUser.getId());
        loginUser.setToken(token.toString());
        refresh(loginUser);
        return new Token(token.toString(), loginUser.getLoginTime(), loginUser.getId());
    }

    @Override
    public void deleteToken(String token) {
        //解析token
        Claims claims = JWTUtils.getClaimsBody(token);
        Integer userId = (Integer) claims.get("id");
        String key= "user:"+userId;
        if (redisUtil.hasKey(key)) {
            redisUtil.del(key);
        }
    }

    /**
     * 更新缓存的用户信息
     */
    @Override
    public void refresh(AdminUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireSeconds * 1000);
        // 缓存
        String key = "user:"+loginUser.getId();
        redisUtil.setex(key, expireSeconds, JSON.toJSONString(loginUser));
    }

}
