package org.com.bmw.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


public class User extends BaseModel{

    private String userName;
    private String password;
    private Long storeId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", storeId=" + storeId +
                '}';
    }
}
