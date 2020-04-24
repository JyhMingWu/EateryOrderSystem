package com.nanbo.ordersystem.entity.U_Roles;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("SysRole-角色")
public class SysRole {

    @ApiModelProperty(value = "主键guid",example = "1",position =1)
    private String roleGuid;
    @ApiModelProperty(value = "自增序号",position =2)
    private int roleId;
    @ApiModelProperty(value = "角色名称",position =3)
    private String roleName;
    @ApiModelProperty(value = "角色描述",position =4)
    private String description;


    public String getRoleGuid() {
        return roleGuid;
    }

    public void setRoleGuid(String roleGuid) {
        this.roleGuid = roleGuid;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "roleGuid='" + roleGuid + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}