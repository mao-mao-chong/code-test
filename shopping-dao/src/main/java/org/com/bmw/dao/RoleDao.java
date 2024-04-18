package org.com.bmw.dao;

import io.lettuce.core.dynamic.annotation.Param;
import org.com.bmw.model.Role;
import org.com.bmw.model.User;
import org.com.bmw.model.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    public List<Role> selectUserRole(Long userId);

    public int insertRoleForUser(UserRole userRole);
}
