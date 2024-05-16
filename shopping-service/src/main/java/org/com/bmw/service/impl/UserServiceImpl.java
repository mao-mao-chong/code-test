package org.com.bmw.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.com.bmw.dao.*;
import org.com.bmw.model.*;
import org.com.bmw.service.UserService;
import org.com.bmw.util.Constant;
import org.com.bmw.util.JWTUtils;
import org.com.bmw.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    PermissionDao permissionDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    StoreDao storeDao;

    @Autowired
    private RedisUtil redisUtil;


    private List<Permission> delPermission(List<Permission> permissionList){
        List<Permission> result = new ArrayList<Permission>();
        if(!CollectionUtils.isEmpty(permissionList)){
            Map<String,List<Permission>> map = new HashMap<String,List<Permission>>();
            //处理子节点
            for(Permission permission : permissionList){
                if(StringUtils.isNotEmpty(permission.getParentCode())){
                    List<Permission> sonList = new ArrayList<Permission>();
                    if(map.get(permission.getParentCode())!=null){
                        sonList = map.get(permission.getParentCode());
                    }
                    sonList.add(permission);
                    map.put(permission.getParentCode(),sonList);
                }
            }
            //处理父节点
            for(Permission permission : permissionList){
                if(StringUtils.isEmpty(permission.getParentCode())){
                    permission.setSonPermission(map.get(permission.getPermissionCode()));
                    result.add(permission);
                }
            }
        }
        return result;
    }
    public User selectUserByPrimaryKey(Long id){
        return userDao.selectUserByPrimaryKey(id);
    }

    @Override
    public User selectByUser(User user) {
        return userDao.selectUser(user);
    }

    @Override
    public List<Role> selectUserRole(Long userId) {
        return roleDao.selectUserRole(userId);
    }

    @Override
    public List<Permission> selectPermissionList(Long userId) {
        List<Permission> permissionList = permissionDao.selectPermissionByUserId(userId);
        return delPermission(permissionList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnMsg registerUser(User user, Store store) {
        //查询是否存在
        User existUser = userDao.selectUser(user);
        if(existUser!=null){
            return new ReturnMsg(Constant.USER_EXIST.getCode(), Constant.USER_EXIST.getMessage());
        }
        Store existStore = storeDao.selectExistStore(store);
        if(existStore!=null){
            return new ReturnMsg(Constant.STORE_EXIST.getCode(), Constant.STORE_EXIST.getMessage());
        }
        //注册店铺
        storeDao.insertStore(store);
        //注册用户
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(user.getPassword());
        user.setStoreId(store.getId());
        user.setPassword(encode);
        userDao.insertUser(user);
        //添加角色-商户
        UserRole useRole = new UserRole();
        useRole.setUserId(user.getId());
        useRole.setRoleId(2L);//商户---只支持商户注册
        roleDao.insertRoleForUser(useRole);
        return new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
    }
}

