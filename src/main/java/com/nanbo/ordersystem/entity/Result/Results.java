package com.nanbo.ordersystem.entity.Result;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Results-返回类")
public class Results {

    @ApiModelProperty(value = "返回数据")
    private Object Data;

    @ApiModelProperty(value = "返回信息")
    private String Message;

    @ApiModelProperty(value = "响应状态")
    private boolean Status;

    @Override
    public String toString() {
        return "Results{" +
                "Data=" + Data +
                ", Message='" + Message + '\'' +
                ", Status=" + Status +
                '}';
    }
    @JsonProperty("Data")
    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @JsonProperty("Status")
    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }


}
