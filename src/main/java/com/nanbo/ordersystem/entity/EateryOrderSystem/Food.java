package com.nanbo.ordersystem.entity.EateryOrderSystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("Food-菜品")
public class Food {
    @ApiModelProperty(value = "菜品主键guid",example = "1",position =1)
    private String foodGuid;
    @ApiModelProperty(value = "自增序号",position =2)
    private int foodId;
    @ApiModelProperty(value = "菜品名称",position =3)
    private String foodName;
    @ApiModelProperty(value = "菜品类别(1:套餐 2:单品)",position =4)
    private String foodType;
    @ApiModelProperty(value = "价格",position =5)
    private BigDecimal foodPrice;
    @ApiModelProperty(value = "菜品图片路劲",position =6)
    private String foodImage;
    @ApiModelProperty(value = "菜品内容",position =7)
    private String foodContent;
    @ApiModelProperty(value = "状态(1:上架0:未上架)",position =8)
    private int foodStatus;
    @ApiModelProperty(value = "所属商户",position =9)
    private String foodComGuid;
    @ApiModelProperty(value = "上品日期",position =10)
    private String foodUploadDate;
    @ApiModelProperty(value = "上传者",position =11)
    private String foodUploadPeople;
    @ApiModelProperty(value = "菜品备注",position =12)
    private String foodRemarks;
    @ApiModelProperty(value = "菜品口味",position =13)
    private String foodTaste;

    public String getFoodGuid() {
        return foodGuid;
    }

    public void setFoodGuid(String foodGuid) {
        this.foodGuid = foodGuid;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodContent() {
        return foodContent;
    }

    public void setFoodContent(String foodContent) {
        this.foodContent = foodContent;
    }

    public int getFoodStatus() {
        return foodStatus;
    }

    public void setFoodStatus(int foodStatus) {
        this.foodStatus = foodStatus;
    }

    public String getFoodComGuid() {
        return foodComGuid;
    }

    public void setFoodComGuid(String foodComGuid) {
        this.foodComGuid = foodComGuid;
    }

    public String getFoodUploadDate() {
        return foodUploadDate;
    }

    public void setFoodUploadDate(String foodUploadDate) {
        this.foodUploadDate = foodUploadDate;
    }

    public String getFoodUploadPeople() {
        return foodUploadPeople;
    }

    public void setFoodUploadPeople(String foodUploadPeople) {
        this.foodUploadPeople = foodUploadPeople;
    }

    public String getFoodRemarks() {
        return foodRemarks;
    }

    public void setFoodRemarks(String foodRemarks) {
        this.foodRemarks = foodRemarks;
    }

    public String getFoodTaste() {
        return foodTaste;
    }

    public void setFoodTaste(String foodTaste) {
        this.foodTaste = foodTaste;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodGuid='" + foodGuid + '\'' +
                ", foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", foodType='" + foodType + '\'' +
                ", foodPrice=" + foodPrice +
                ", foodImage='" + foodImage + '\'' +
                ", foodContent='" + foodContent + '\'' +
                ", foodStatus=" + foodStatus +
                ", foodComGuid='" + foodComGuid + '\'' +
                ", foodUploadDate='" + foodUploadDate + '\'' +
                ", foodUploadPeople='" + foodUploadPeople + '\'' +
                ", foodRemarks='" + foodRemarks + '\'' +
                ", foodTaste='" + foodTaste + '\'' +
                '}';
    }
}
