package org.com.bmw.dao;

import io.lettuce.core.dynamic.annotation.Param;
import org.com.bmw.model.ConsumerUser;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerUserDao {
    public ConsumerUser selectConsumerUserByPrimaryKey(@Param("id") Long id);
    public ConsumerUser selectConsumerUser(ConsumerUser user);

    public int insertConsumerUser(ConsumerUser user);

}
