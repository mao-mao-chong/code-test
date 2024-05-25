package org.com.bmw.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ActivityEnroll extends BaseModel{
    //活动id
    private Long activityId;
    //活动名称
    private String activityName;
    //店铺id
    private Long storeId;
    //店铺名称
    private String storeName;
    //产品id
    private Long productId;
    //产品名称
    private String productName;
    //商品价格
    private BigDecimal productPrice;
    //商品库存
    private Long productInventory;

    private Long saledInventory;


    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Long getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(Long productInventory) {
        this.productInventory = productInventory;
    }

    public Long getSaledInventory() {
        return saledInventory;
    }

    public void setSaledInventory(Long saledInventory) {
        this.saledInventory = saledInventory;
    }

    @Override
    public String toString() {
        return "ActivityEnroll{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productInventory=" + productInventory +
                '}';
    }
}
