package com.nanbo.ordersystem.controller;

import com.nanbo.ordersystem.entity.Alliance;
import com.nanbo.ordersystem.entity.EateryOrderSystem.Commerce;
import com.nanbo.ordersystem.entity.EateryOrderSystem.Food;
import com.nanbo.ordersystem.entity.Result.Data;
import com.nanbo.ordersystem.entity.Result.Results;
import com.nanbo.ordersystem.server.FoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/Food")
@Api(value="菜品controller",tags={"菜品controller-后台专用"},position = 3)
public class FoodController {


    @Autowired
    private FoodService foodService;

    @ApiOperation(value="获取所有菜品",position =2,notes = "获取所有菜品--后台专用")
    @RequestMapping(value = "/GetAllFood",method = RequestMethod.GET)
    public Data GetAllFood(){
        List<Food> list=foodService.GetAllFood();
        Data data=new Data();
        data.setData(list);
        data.setStatus(true);
        data.setMessage("获取成功！");
        return data;
    }

    @ApiOperation(value="获取单条菜品信息",position =2,notes = "获取单条菜品信息--后台专用")
    @ApiImplicitParam(name="foodGuid",value="菜品Guid",dataType="String", paramType = "query",required = true)
    @RequestMapping(value = "/GetOneFoodInfo",method = RequestMethod.GET)
    public Results GetOneFoodInfo(String foodGuid){
        Results results=new Results();
        try {
            Food food=foodService.GetOneFoodInfo(foodGuid);

            results.setData(food);
            results.setStatus(true);
            results.setMessage("获取成功！");

        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("获取单条菜品信息GetOneFoodInfo:"+e);
        }
        return results;
    }

    @ApiOperation(value="更改菜品上下架状态",position =2,notes = "更改菜品上下架状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name="foodStatus",value="状态",dataType="int", paramType = "query",required = true),
            @ApiImplicitParam(name="foodGuid",value="菜品Guid标识",dataType="string", paramType = "query",required = true)
    })
    @RequestMapping(value = "/UpdateFoodStatus",method = RequestMethod.GET)
    public Results UpdateFoodStatus(int foodStatus,String foodGuid){
        Results results=new Results();

        try{
            int status=foodService.UpdateFoodStatus(foodStatus,foodGuid);
            if(status==1){
                results.setStatus(true);
                results.setMessage("更改成功！");
            }else{
                results.setStatus(false);
                results.setMessage("更改失败！");
            }
        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("更改异常："+e);
        }
        return results;
    }

    //需要--删除指定菜品权限
//    @RequiresPermissions("deleteCarouse")
    @ApiOperation(value="删除指定菜品",position = 7,notes = "删除指定菜品")
    @RequestMapping(value = "/DeleteFood",method = RequestMethod.GET)
    @ApiImplicitParam(name="foodGuid",value="菜品Guid",dataType="String", paramType = "query",required = true)
    public Results DeleteFood(String foodGuid){
        Results results=new Results();
        try{
            int i=foodService.DeleteFood(foodGuid);
            if(i==1){
                results.setStatus(true);
                results.setMessage("删除成功！");
            }else{
                results.setStatus(false);
                results.setMessage("删除失败！");
            }
        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("删除指定菜品异常---失败："+e);
        }
        return results;
    }

    //需要--删除指定轮播图权限
//    @RequiresPermissions("deleteCarouse")
    @ApiOperation(value="批量删除菜品",position = 8,notes = "批量删除菜品")
    @RequestMapping(value = "/BatchDeleteFood",method = RequestMethod.GET)
    @ApiImplicitParam(name="cidList",value="菜品标识数组,以逗号分隔",dataType="String[]", paramType = "query",required = true)
    public Results BatchDeleteFood(String cidList){
        Results results=new Results();
        String regex="[\\[ \\] \\-\" -]";
        String strArr=cidList.replaceAll(regex,"");//去除: [ ] \ " 号
        String[] stringArr = strArr.split(",");
        //字符串数组转化为List集合
        List<String> cidFromList = new ArrayList<>(Arrays.asList(stringArr));
        //删除List集合中的空元素
        cidFromList.removeAll(Collections.singletonList(""));

        try{
            int i=foodService.BatchDeleteFood(cidFromList);
            if(i!=0){
                results.setStatus(true);
                results.setMessage("成功删除"+i+"条指定菜品");
            }else{
                results.setStatus(false);
                results.setMessage("删除指定菜品失败！");
            }
        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("删除指定菜品异常---失败："+e);
        }
        return results;
    }

    @ApiOperation(value="菜品默认图上传",position = 2)
    @ApiImplicitParam(name="food",value="菜品对象",dataType="Food", paramType = "query",required = true)
    @RequestMapping(value = "/AddFondDefaultImage",method = RequestMethod.POST)
    public Results AddFondDefaultImage(Food food){
        Results results=new Results();

        //设置上传的默认图片
        food.setFoodImage("http://localhost:9417/images/upload/Food_img/default.jpg");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String DateNow = sdf.format(d);
        //设置上传时间
        food.setFoodUploadDate(DateNow);
        try {
            int status=foodService.AddFood(food);
            if(status==1){
                results.setStatus(true);
                results.setMessage("菜品创建成功！");
            }else{
                results.setStatus(false);
                results.setMessage("菜品创建失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            results.setStatus(false);
            results.setMessage("菜品创建异常AddFondDefaultImage："+e);
        }
        return results;
    }



    //******************************以下小程序调用*******************************


    @ApiOperation(value="根据商户获取所有菜品",position =7,notes = "菜品业务--小程序调用",tags = "菜品业务-小程序调用")
    @ApiImplicitParam(name="foodComGuid",value="所属商户Guid",dataType="String", paramType = "query",required = true)
    @RequestMapping(value = "/GetAllFoodToSmallRoutine",method = RequestMethod.GET)
    public Results GetAllFoodToSmallRoutine(String foodComGuid){
        Results results=new Results();
        try {
            List<Food> list=foodService.GetAllFoodToSmallRoutine(foodComGuid);
            if(list.size()!=0){
                results.setData(list);
                results.setStatus(true);
                results.setMessage("获取成功！");
            }else {
                results.setStatus(false);
                results.setMessage("获取数据为空！");
            }
        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("获取获取所有菜品异常："+e);
        }
        return results;
    }
}
