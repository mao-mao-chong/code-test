package org.com.bmw.dao;

import org.com.bmw.model.Role;
import org.com.bmw.model.Store;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreDao {
    public int insertStore(Store store);

    public Store selectExistStore(Store store);

}
