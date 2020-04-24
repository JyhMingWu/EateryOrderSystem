package com.nanbo.ordersystem.entity.U_Roles;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("SysPermission-权限")
public class SysPermission {

    @ApiModelProperty(value = "主键guid",example = "1",position =1)
    private String pGuid;
    @ApiModelProperty(value = "功能标题",position =2)
    private String name;
    @ApiModelProperty(value = "权限编码",position =3)
    private String perms;
    @ApiModelProperty(value = "使用状态 0正常 1已删除",position =4)
    private int del_flag;
    @ApiModelProperty(value = "类型",position =5)
    private int type;


    public String getpGuid() {
        return pGuid;
    }

    public void setpGuid(String pGuid) {
        this.pGuid = pGuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "pGuid='" + pGuid + '\'' +
                ", name='" + name + '\'' +
                ", perms='" + perms + '\'' +
                ", del_flag=" + del_flag +
                ", type=" + type +
                '}';
    }
}
