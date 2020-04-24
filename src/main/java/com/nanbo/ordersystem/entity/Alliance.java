package com.nanbo.ordersystem.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Alliance-联盟")
public class Alliance {

    @ApiModelProperty(value = "联盟ID",example = "1")
    private int alliance_id;
    @ApiModelProperty(value = "联盟名称")
    private String alliance_name;
    @ApiModelProperty(value = "地址")
    private String alliance_address;
    @ApiModelProperty(value = "联系方式")
    private String alliance_telephone;
    @ApiModelProperty(value = "联盟图片")
    private String alliance_img;

    @Override
    public String toString() {
        return "Alliance{" +
                "alliance_id=" + alliance_id +
                ", alliance_name='" + alliance_name + '\'' +
                ", alliance_address='" + alliance_address + '\'' +
                ", alliance_telephone='" + alliance_telephone + '\'' +
                ", alliance_img='" + alliance_img + '\'' +
                '}';
    }

    public int getAlliance_id() {
        return alliance_id;
    }

    public void setAlliance_id(int alliance_id) {
        this.alliance_id = alliance_id;
    }

    public String getAlliance_name() {
        return alliance_name;
    }

    public void setAlliance_name(String alliance_name) {
        this.alliance_name = alliance_name;
    }

    public String getAlliance_address() {
        return alliance_address;
    }

    public void setAlliance_address(String alliance_address) {
        this.alliance_address = alliance_address;
    }

    public String getAlliance_telephone() {
        return alliance_telephone;
    }

    public void setAlliance_telephone(String alliance_telephone) {
        this.alliance_telephone = alliance_telephone;
    }

    public String getAlliance_img() {
        return alliance_img;
    }

    public void setAlliance_img(String alliance_img) {
        this.alliance_img = alliance_img;
    }

}
