package com.nanbo.ordersystem.entity.EateryOrderSystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("UserAddress-地址对象")
public class UserAddress {

    @ApiModelProperty(value = "用户地址主键guid(新增的时候使用默认值即可)",example = "1",position =1)
    private String uAGuid;
    @ApiModelProperty(value = "自增序号(新增的时候使用默认值即可)",example = "1",position =2)
    private int uAId;
    @ApiModelProperty(value = "学号",position =3)
    private String uAXh;
    @ApiModelProperty(value = "联系电话",position =4)
    private String uAPhone;
    @ApiModelProperty(value = "收件人",position =5)
    private String uAPeople;
    @ApiModelProperty(value = "地址",position =6)
    private String uAAddress;
    @ApiModelProperty(value = "默认使用(true:是 false:否)",position =7)
    private boolean uADefault;


    public String getuAGuid() {
        return uAGuid;
    }

    public void setuAGuid(String uAGuid) {
        this.uAGuid = uAGuid;
    }

    public int getuAId() {
        return uAId;
    }

    public void setuAId(int uAId) {
        this.uAId = uAId;
    }

    public String getuAXh() {
        return uAXh;
    }

    public void setuAXh(String uAXh) {
        this.uAXh = uAXh;
    }

    public String getuAPhone() {
        return uAPhone;
    }

    public void setuAPhone(String uAPhone) {
        this.uAPhone = uAPhone;
    }

    public String getuAPeople() {
        return uAPeople;
    }

    public void setuAPeople(String uAPeople) {
        this.uAPeople = uAPeople;
    }

    public String getuAAddress() {
        return uAAddress;
    }

    public void setuAAddress(String uAAddress) {
        this.uAAddress = uAAddress;
    }

    public boolean getuADefault() {
        return uADefault;
    }

    public void setuADefault(boolean uADefault) {
        this.uADefault = uADefault;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "uAGuid='" + uAGuid + '\'' +
                ", uAId=" + uAId +
                ", uAXh='" + uAXh + '\'' +
                ", uAPhone='" + uAPhone + '\'' +
                ", uAPeople='" + uAPeople + '\'' +
                ", uAAddress='" + uAAddress + '\'' +
                ", uADefault=" + uADefault +
                '}';
    }
}
