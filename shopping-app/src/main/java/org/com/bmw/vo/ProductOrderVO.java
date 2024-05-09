package org.com.bmw.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.com.bmw.model.BaseModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductOrderVO extends BaseVO implements Serializable {
    @NotNull
    private Long productId;

    private String productName;

    private Long activityId;
    private Long storeId;

    private BigDecimal orderAmount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date orderTime;

    private Integer productCount;

    @NotNull
    private Integer productTypeId;
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

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
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
