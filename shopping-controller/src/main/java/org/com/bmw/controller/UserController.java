package org.com.bmw.controller;

import lombok.extern.slf4j.Slf4j;
import org.com.bmw.model.*;
import org.com.bmw.service.UserService;
import org.com.bmw.util.Constant;
import org.com.bmw.vo.RegisterVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    //注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ReturnMsg register(@RequestBody RegisterVO vo){
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try{
            log.info("用户注册数据：{}",vo);
            User user = new User();
            Store store = new Store();
            //处理遗留问题，大小写错误
            user.setUserName(vo.getUsername());
            user.setPassword(vo.getPassWord());
            BeanUtils.copyProperties(vo,user);
            BeanUtils.copyProperties(vo,store);
            returnMsg = userService.registerUser(user,store);
        }catch(Exception e){
            log.error("注册发生异常：{}",e);
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        log.info("用户注册结果：{}",returnMsg);
        return returnMsg;
    }
    //登录
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ReturnMsg login(User user){
//        log.info("真实的登录方法=======");
//        Map<String,Object> map = new HashMap<String,Object>();
//        Map loginMap = userService.login(user);
//        User loginUser = (User)loginMap.get("user");
//        ReturnMsg returnMsg = (ReturnMsg)loginMap.get("returnMsg");
//        map.put("token",loginMap.get("token"));
//        map.put("permission",returnMsg.getData());
//        returnMsg.setData(map);
//        return returnMsg;
//    }
    //登出
//    @RequestMapping(value = "/logout", method = RequestMethod.POST)
//    public ReturnMsg logout(User user){
//        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
//        return returnMsg;
//    }

    //测试使用
    @RequestMapping(value = "/selectUserByPrimaryKey", method = RequestMethod.POST)
    public ReturnMsg selectUserByPrimaryKey(Long id){
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        User user = userService.selectUserByPrimaryKey(id);
        returnMsg.setData(user);
        return returnMsg;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

}
