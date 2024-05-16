package org.com.bmw.service;

import io.lettuce.core.dynamic.annotation.Param;
import org.com.bmw.model.*;

import java.util.List;
import java.util.Map;

public interface UserService {

    public User selectUserByPrimaryKey(Long id);

    public User selectByUser(User user);

    public List<Role> selectUserRole(Long userId);

    public List<Permission> selectPermissionList(Long userId);

    public ReturnMsg registerUser(User user, Store store);

}
