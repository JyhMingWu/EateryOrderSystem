package com.nanbo.ordersystem.entity.EateryOrderSystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("Commerce-商户")
public class Commerce {

    @ApiModelProperty(value = "商户主键guid",example = "1",position =1)
    private String comGuid;

    @ApiModelProperty(value = "自增序号",position =2 )
    private int comId;

    @ApiModelProperty(value = "商户名称",position =3)
    private String comName;

    @ApiModelProperty(value = "商户简称",position =4)
    private String comSimpleName;

    @ApiModelProperty(value = "联系人",position =5)
    private String comPeople;

    @ApiModelProperty(value = "联系电话",position =6)
    private String comPhone;

    @ApiModelProperty(value = "备注",position =7)
    private String comRemarks;


    public String getComGuid() {
        return comGuid;
    }

    public void setComGuid(String comGuid) {
        this.comGuid = comGuid;
    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComSimpleName() {
        return comSimpleName;
    }

    public void setComSimpleName(String comSimpleName) {
        this.comSimpleName = comSimpleName;
    }

    public String getComPeople() {
        return comPeople;
    }

    public void setComPeople(String comPeople) {
        this.comPeople = comPeople;
    }

    public String getComPhone() {
        return comPhone;
    }

    public void setComPhone(String comPhone) {
        this.comPhone = comPhone;
    }

    public String getComRemarks() {
        return comRemarks;
    }

    public void setComRemarks(String comRemarks) {
        this.comRemarks = comRemarks;
    }

    @Override
    public String toString() {
        return "Commerce{" +
                "comGuid='" + comGuid + '\'' +
                ", comId=" + comId +
                ", comName='" + comName + '\'' +
                ", comSimpleName='" + comSimpleName + '\'' +
                ", comPeople='" + comPeople + '\'' +
                ", comPhone='" + comPhone + '\'' +
                ", comRemarks='" + comRemarks + '\'' +
                '}';
    }
}
