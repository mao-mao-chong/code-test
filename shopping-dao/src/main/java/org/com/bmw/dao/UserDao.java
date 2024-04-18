package org.com.bmw.dao;

import io.lettuce.core.dynamic.annotation.Param;
import org.com.bmw.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public User selectUserByPrimaryKey(@Param("id") Long id);
    public User selectUser(User user);

    public int insertUser(User user);

}
