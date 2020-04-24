package com.nanbo.ordersystem.entity.U_Roles;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("SysRolePermission-角色权限")
public class SysRolePermission {
    @ApiModelProperty(value = "主键guid",example = "1",position =1)
    private String rPGuid;
    @ApiModelProperty(value = "角色guid",position =2)
    private String roleGuid;
    @ApiModelProperty(value = "权限guid",position =3)
    private String pGuid;
    @ApiModelProperty(value = "自增ID",position =4)
    private int rPId;


    public String getrPGuid() {
        return rPGuid;
    }

    public void setrPGuid(String rPGuid) {
        this.rPGuid = rPGuid;
    }

    public String getRoleGuid() {
        return roleGuid;
    }

    public void setRoleGuid(String roleGuid) {
        this.roleGuid = roleGuid;
    }

    public String getpGuid() {
        return pGuid;
    }

    public void setpGuid(String pGuid) {
        this.pGuid = pGuid;
    }

    public int getrPId() {
        return rPId;
    }

    public void setrPId(int rPId) {
        this.rPId = rPId;
    }

    @Override
    public String toString() {
        return "SysRolePermission{" +
                "rPGuid='" + rPGuid + '\'' +
                ", roleGuid='" + roleGuid + '\'' +
                ", pGuid='" + pGuid + '\'' +
                ", rPId=" + rPId +
                '}';
    }
}
