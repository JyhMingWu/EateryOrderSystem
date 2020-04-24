package com.nanbo.ordersystem.server;

import com.nanbo.ordersystem.entity.EateryOrderSystem.Food;
import com.nanbo.ordersystem.mapper.FoodMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService implements FoodMapper {

    @Autowired
    FoodMapper foodMapper;

    //增加高校联盟
    public int AddFood(Food food){
        return foodMapper.AddFood(food);
    }

    //获取所有商户
    public List<Food> GetAllFood(){
        return foodMapper.GetAllFood();
    }

    //获取单条菜品信息
    public Food GetOneFoodInfo(String foodGuid){
        return foodMapper.GetOneFoodInfo(foodGuid);
    }

    //更新菜品上下架
    public int UpdateFoodStatus(int foodStatus, String foodGuid){
        return foodMapper.UpdateFoodStatus(foodStatus,foodGuid);
    }

    //删除指定菜品
    public int DeleteFood(String foodGuid){
        return foodMapper.DeleteFood(foodGuid);
    }

    //批量删除菜品
    public int BatchDeleteFood(List list){
        return foodMapper.BatchDeleteFood(list);
    }

    //修改菜品信息
    public int UpdateFoodData(Food food){
        return foodMapper.UpdateFoodData(food);
    }

    //********************************以下小程序调用*****************************

    //根据所属商户获取菜品
    public List<Food> GetAllFoodToSmallRoutine(String foodComGuid){
        return foodMapper.GetAllFoodToSmallRoutine(foodComGuid);
    }
}
