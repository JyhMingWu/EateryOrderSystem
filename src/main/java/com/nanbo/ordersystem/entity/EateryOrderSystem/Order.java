package com.nanbo.ordersystem.entity.EateryOrderSystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel("Order-订单")
public class Order {
    @ApiModelProperty(value = "订单主键guid",example = "1",position =1)
    private String orderGuid;
    @ApiModelProperty(value = "自增序号",position =2)
    private int orderId;
    @ApiModelProperty(value = "订单编号(由时间生成)",position =3)
    private String orderSid;
    @ApiModelProperty(value = "订单所属商户guid",position =4)
    private String orderComGuid;
    @ApiModelProperty(value = "订单所属商户名称",position =5)
    private String orderComName;
    @ApiModelProperty(value = "总金额",position =6)
    private BigDecimal orderSumPay;
    @ApiModelProperty(value = "总数量",position =7)
    private int orderSumNumber;
    @ApiModelProperty(value = "下单时间",position =8)
    private String orderStartTime;
    @ApiModelProperty(value = "订单完成时间",position =9)
    private String orderEndTime;
    @ApiModelProperty(value = "接单状态(0:未接单 1:已接单) --暂不使用",position =10)
    private int orderReceiveStatus;
    @ApiModelProperty(value = "订单状态(0:未完成 1:已完成)",position =11)
    private int orderStatus;
    @ApiModelProperty(value = "支付时间",position =12)
    private String orderPayTime;
    @ApiModelProperty(value = "支付单号(流水号)",position =13)
    private String orderPayNumber;
    @ApiModelProperty(value = "支付状态(0:未支付 1:已支付)",position =14)
    private int orderPayStatus;
    @ApiModelProperty(value = "留言备注",position =15)
    private String orderRemarks;
    @ApiModelProperty(value = "收件人",position =16)
    private String orderPeople;
    @ApiModelProperty(value = "电话",position =17)
    private String orderPhone;
    @ApiModelProperty(value = "收件地址",position =18)
    private String orderAddress;
    @ApiModelProperty(value = "菜品口味",position =19)
    private String orderFoodTaste;
    @ApiModelProperty(value = "明细",position =20)
    private List<OrderDetail> listOrderDetail;
    @ApiModelProperty(value = "学生学号",position =21)
    private String orderXh;


    public String getOrderComName() {
        return orderComName;
    }

    public void setOrderComName(String orderComName) {
        this.orderComName = orderComName;
    }

    public String getOrderGuid() {
        return orderGuid;
    }

    public void setOrderGuid(String orderGuid) {
        this.orderGuid = orderGuid;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderSid() {
        return orderSid;
    }

    public void setOrderSid(String orderSid) {
        this.orderSid = orderSid;
    }

    public String getOrderComGuid() {
        return orderComGuid;
    }

    public void setOrderComGuid(String orderComGuid) {
        this.orderComGuid = orderComGuid;
    }

    public BigDecimal getOrderSumPay() {
        return orderSumPay;
    }

    public void setOrderSumPay(BigDecimal orderSumPay) {
        this.orderSumPay = orderSumPay;
    }

    public int getOrderSumNumber() {
        return orderSumNumber;
    }

    public void setOrderSumNumber(int orderSumNumber) {
        this.orderSumNumber = orderSumNumber;
    }

    public String getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(String orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public String getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(String orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public int getOrderReceiveStatus() {
        return orderReceiveStatus;
    }

    public void setOrderReceiveStatus(int orderReceiveStatus) {
        this.orderReceiveStatus = orderReceiveStatus;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(String orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public String getOrderPayNumber() {
        return orderPayNumber;
    }

    public void setOrderPayNumber(String orderPayNumber) {
        this.orderPayNumber = orderPayNumber;
    }

    public int getOrderPayStatus() {
        return orderPayStatus;
    }

    public void setOrderPayStatus(int orderPayStatus) {
        this.orderPayStatus = orderPayStatus;
    }

    public String getOrderRemarks() {
        return orderRemarks;
    }

    public void setOrderRemarks(String orderRemarks) {
        this.orderRemarks = orderRemarks;
    }

    public String getOrderPeople() {
        return orderPeople;
    }

    public void setOrderPeople(String orderPeople) {
        this.orderPeople = orderPeople;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderFoodTaste() {
        return orderFoodTaste;
    }

    public void setOrderFoodTaste(String orderFoodTaste) {
        this.orderFoodTaste = orderFoodTaste;
    }

    public List<OrderDetail> getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(List<OrderDetail> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

    public String getOrderXh() {
        return orderXh;
    }

    public void setOrderXh(String orderXh) {
        this.orderXh = orderXh;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderGuid='" + orderGuid + '\'' +
                ", orderId=" + orderId +
                ", orderSid='" + orderSid + '\'' +
                ", orderComGuid='" + orderComGuid + '\'' +
                ", orderSumPay=" + orderSumPay +
                ", orderSumNumber=" + orderSumNumber +
                ", orderStartTime='" + orderStartTime + '\'' +
                ", orderEndTime='" + orderEndTime + '\'' +
                ", orderReceiveStatus=" + orderReceiveStatus +
                ", orderStatus=" + orderStatus +
                ", orderPayTime='" + orderPayTime + '\'' +
                ", orderPayNumber='" + orderPayNumber + '\'' +
                ", orderPayStatus=" + orderPayStatus +
                ", orderRemarks='" + orderRemarks + '\'' +
                ", orderPeople='" + orderPeople + '\'' +
                ", orderPhone='" + orderPhone + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderFoodTaste='" + orderFoodTaste + '\'' +
                ", listOrderDetail=" + listOrderDetail +
                ", order_xh='" + orderXh + '\'' +
                '}';
    }
}
