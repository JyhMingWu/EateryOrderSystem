package com.nanbo.ordersystem.entity.Result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Data-(后台前端分页所用)")
public class Data {

    @ApiModelProperty(value = "返回数据")
    private Object data;

    @ApiModelProperty(value = "返回提示信息")
    private String Message;

    @ApiModelProperty(value = "响应状态")
    private boolean Status;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
