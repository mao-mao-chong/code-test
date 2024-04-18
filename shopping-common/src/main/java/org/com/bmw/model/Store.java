package org.com.bmw.model;

import java.io.Serializable;

public class Store extends BaseModel{
    //店铺名称
    private String storeName;
    //证件
    private String businessLicense;
    //地区
    private String area;
    //地址
    private String address;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                ", businessLicense='" + businessLicense + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
