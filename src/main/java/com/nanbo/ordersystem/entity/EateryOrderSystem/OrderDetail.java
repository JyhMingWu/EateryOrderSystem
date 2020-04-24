package com.nanbo.ordersystem.entity.EateryOrderSystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("OrderDetail-订单明细")
public class OrderDetail {
    @ApiModelProperty(value = "订单明细主键guid",example = "1",position =1)
    private String orderDGuid;
    @ApiModelProperty(value = "自增序号",position =2)
    private int orderDId;
    @ApiModelProperty(value = "明细编号",position =3)
    private int orderDNumId;
    @ApiModelProperty(value = "菜品guid",position =4)
    private String orderDFoodGuid;
    @ApiModelProperty(value = "订单编号(由时间生成)",position =5)
    private String orderDOrderSid;
    @ApiModelProperty(value = "菜品名称",position =6)
    private String orderDFoodName;
    @ApiModelProperty(value = "单价",position =7)
    private BigDecimal orderDFoodPrice;
    @ApiModelProperty(value = "排序序号--暂不使用",position =8)
    private int orderDSequence;
    @ApiModelProperty(value = "数量",position =9)
    private int orderDNumber;
    @ApiModelProperty(value = "菜品口味",position =10)
    private String orderDTaste;
    @ApiModelProperty(value = "学生学号",position =11)
    private String orderDXh;

    public String getOrderDGuid() {
        return orderDGuid;
    }

    public void setOrderDGuid(String orderDGuid) {
        this.orderDGuid = orderDGuid;
    }

    public int getOrderDId() {
        return orderDId;
    }

    public void setOrderDId(int orderDId) {
        this.orderDId = orderDId;
    }

    public int getOrderDNumId() {
        return orderDNumId;
    }

    public void setOrderDNumId(int orderDNumId) {
        this.orderDNumId = orderDNumId;
    }

    public String getOrderDFoodGuid() {
        return orderDFoodGuid;
    }

    public void setOrderDFoodGuid(String orderDFoodGuid) {
        this.orderDFoodGuid = orderDFoodGuid;
    }

    public String getOrderDOrderSid() {
        return orderDOrderSid;
    }

    public void setOrderDOrderSid(String orderDOrderSid) {
        this.orderDOrderSid = orderDOrderSid;
    }

    public String getOrderDFoodName() {
        return orderDFoodName;
    }

    public void setOrderDFoodName(String orderDFoodName) {
        this.orderDFoodName = orderDFoodName;
    }

    public BigDecimal getOrderDFoodPrice() {
        return orderDFoodPrice;
    }

    public void setOrderDFoodPrice(BigDecimal orderDFoodPrice) {
        this.orderDFoodPrice = orderDFoodPrice;
    }

    public int getOrderDSequence() {
        return orderDSequence;
    }

    public void setOrderDSequence(int orderDSequence) {
        this.orderDSequence = orderDSequence;
    }

    public int getOrderDNumber() {
        return orderDNumber;
    }

    public void setOrderDNumber(int orderDNumber) {
        this.orderDNumber = orderDNumber;
    }

    public String getOrderDTaste() {
        return orderDTaste;
    }

    public void setOrderDTaste(String orderDTaste) {
        this.orderDTaste = orderDTaste;
    }

    public String getOrderDXh() {
        return orderDXh;
    }

    public void setOrderDXh(String orderDXh) {
        this.orderDXh = orderDXh;
    }


    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDGuid='" + orderDGuid + '\'' +
                ", orderDId=" + orderDId +
                ", orderDNumId=" + orderDNumId +
                ", orderDFoodGuid='" + orderDFoodGuid + '\'' +
                ", orderDOrderGuid='" + orderDOrderSid + '\'' +
                ", orderDFoodName='" + orderDFoodName + '\'' +
                ", orderDFoodPrice=" + orderDFoodPrice +
                ", orderDSequence=" + orderDSequence +
                ", orderDNumber=" + orderDNumber +
                ", orderDTaste='" + orderDTaste + '\'' +
                ", orderDXh='" + orderDXh + '\'' +
                '}';
    }
}
