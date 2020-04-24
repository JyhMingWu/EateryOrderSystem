package com.nanbo.ordersystem.entity.U_Roles;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("SysUserRole-用户角色")
public class SysUserRole {

    @ApiModelProperty(value = "主键guid",position =1)
    private String uRGuid;
    @ApiModelProperty(value = "用户guid",position =2)
    private String userGuid;
    @ApiModelProperty(value = "角色guid",position =3)
    private String roleGuid;
    @ApiModelProperty(value = "自增ID",position =4)
    private int uRId;

    public String getuRGuid() {
        return uRGuid;
    }

    public void setuRGuid(String uRGuid) {
        this.uRGuid = uRGuid;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getRoleGuid() {
        return roleGuid;
    }

    public void setRoleGuid(String roleGuid) {
        this.roleGuid = roleGuid;
    }

    public int getuRId() {
        return uRId;
    }

    public void setuRId(int uRId) {
        this.uRId = uRId;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
                "uRGuid='" + uRGuid + '\'' +
                ", userGuid='" + userGuid + '\'' +
                ", roleGuid='" + roleGuid + '\'' +
                ", uRId=" + uRId +
                '}';
    }
}
