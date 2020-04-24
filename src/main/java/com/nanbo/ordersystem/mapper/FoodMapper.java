package com.nanbo.ordersystem.mapper;

import com.nanbo.ordersystem.entity.EateryOrderSystem.Commerce;
import com.nanbo.ordersystem.entity.EateryOrderSystem.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FoodMapper {

    //获取所有商户
    List<Food> GetAllFood();

    //获取单条菜品信息
    Food GetOneFoodInfo(@Param("foodGuid")String foodGuid);

    //增加菜品
    int AddFood(Food food);

    //更新菜品上下架
    int UpdateFoodStatus(@Param("foodStatus")int foodStatus, @Param("foodGuid")String foodGuid);

    //删除指定菜品
    int DeleteFood(@Param("foodGuid")String foodGuid);

    //批量删除菜品
    int BatchDeleteFood(List list);

    //修改菜品信息
    int UpdateFoodData(Food food);



    //********************************以下小程序调用*****************************

    //根据所属商户获取菜品
    List<Food> GetAllFoodToSmallRoutine(String foodComGuid);

}
