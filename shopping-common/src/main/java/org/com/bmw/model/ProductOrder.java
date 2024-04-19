package org.com.bmw.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProductOrder extends BaseModel{
    private Long productId;

    private String productName;

    private Long activityId;
    private Long storeId;

    private BigDecimal orderAmount;

    private Date orderTime;

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

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", activityId=" + activityId +
                ", storeId=" + storeId +
                ", orderAmount=" + orderAmount +
                ", orderTime=" + orderTime +
                '}';
    }
}
