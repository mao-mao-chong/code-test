package org.com.bmw.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class RegisterVO implements Serializable {
    private String userName;
    private String password;
    private Long storeId;

    //店铺名称
    private String storeName;
    //证件
    private String businessLicense;
    //地区
    private String area;
    //地址
    private String address;

}
