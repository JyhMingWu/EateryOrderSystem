package com.nanbo.ordersystem.entity.U_Roles;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("SysUser-用户")
public class SysUser{

    @ApiModelProperty(value = "主键guid",example = "1",position =1)
    private String userGuid;
    @ApiModelProperty(value = "自增序号",position =2)
    private int userId;
    @ApiModelProperty(value = "用户名",position =3)
    private String username;
    @ApiModelProperty(value = "密码",position =4)
    private String password;
    @ApiModelProperty(value = "商户guid",position =5)
    private String userComGuid;
    @ApiModelProperty(value = "角色guid",position =6)
    private String userRoleGuid;
    @ApiModelProperty(value = "角色名称",position =7)
    private String userRoleName;
    @ApiModelProperty(value = "角色描述",position =8)
    private String description;

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserComGuid() {
        return userComGuid;
    }

    public void setUserComGuid(String userComGuid) {
        this.userComGuid = userComGuid;
    }

    public String getUserRoleGuid() {
        return userRoleGuid;
    }

    public void setUserRoleGuid(String userRoleGuid) {
        this.userRoleGuid = userRoleGuid;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userGuid='" + userGuid + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userComGuid='" + userComGuid + '\'' +
                ", userRoleGuid='" + userRoleGuid + '\'' +
                ", userRoleName='" + userRoleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}