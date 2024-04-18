package org.com.bmw.dao;

import io.lettuce.core.dynamic.annotation.Param;
import org.com.bmw.model.Permission;
import org.com.bmw.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {
    public List<Permission> selectPermissionByUserId(@Param("id") Long id);

    public void insertUser(User user);
}
