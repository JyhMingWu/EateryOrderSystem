package com.nanbo.ordersystem.controller;



import com.nanbo.ordersystem.entity.EateryOrderSystem.*;
import com.nanbo.ordersystem.entity.Result.Data;
import com.nanbo.ordersystem.entity.Result.Results;
import com.nanbo.ordersystem.server.CommerceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/Commerce")
@Api(value="商户controller",tags={"商户controller-后台专用"},position = 2)
public class CommerceController {

    @Autowired
    private CommerceService commerceService;

    @ApiOperation(value="获取所有商户",position =2,notes = "获取所有商户--后台专用")
    @ApiImplicitParam(name="GetAllCommerce",value="获取所有商户",dataType="Commerce", paramType = "query",required = true)
    @RequestMapping(value = "/GetAllCommerce",method = RequestMethod.GET)
    public Data GetAllCommerce(){
        List<Commerce> list=commerceService.GetAllCommerce();
        Data data=new Data();
        data.setData(list);
        data.setStatus(true);
        data.setMessage("获取成功！");
        return data;
    }

    @ApiOperation(value="获取单条商户信息",position =2,notes = "获取单条商户信息--后台专用")
    @ApiImplicitParam(name="GetOneCommerceInfo",value="获取单条商户信息",dataType="String", paramType = "query",required = true)
    @RequestMapping(value = "/GetOneCommerceInfo",method = RequestMethod.GET)
    public Results GetOneCommerceInfo(String comGuid){
        Results results=new Results();
        try {
            Commerce commerce=commerceService.GetOneCommerceInfo(comGuid);

            results.setData(commerce);
            results.setStatus(true);
            results.setMessage("获取成功！");

        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("获取单条商户信息GetOneCommerceInfo:"+e);
        }
        return results;
    }


    @ApiOperation(value="增加商户",position =2,notes = "增加商户")
    @ApiImplicitParam(name="AddCommerce",value="增加商户",dataType="Commerce", paramType = "query",required = true)
    @RequestMapping(value = "/AddCommerce",method = RequestMethod.POST)
    public Results AddCommerce(Commerce commerce){
        Results results=new Results();
        try{
            int status=commerceService.AddCommerce(commerce);
            if(status==1){
                results.setStatus(true);
                results.setMessage("增加成功！");
            }else{
                results.setStatus(false);
                results.setMessage("增加失败！");
            }

        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("增加异常AddCommerce："+e);
        }
        return results;
    }

    @ApiOperation(value="修改商户",position =2,notes = "修改商户")
    @ApiImplicitParam(name="UpdateCommerceInfo",value="修改商户",dataType="Commerce", paramType = "query",required = true)
    @RequestMapping(value = "/UpdateCommerceInfo",method = RequestMethod.POST)
    public Results UpdateCommerceInfo(Commerce commerce){
        Results results=new Results();
        try{
            int status=commerceService.UpdateCommerceInfo(commerce);
            if(status==1){
                results.setStatus(true);
                results.setMessage("修改成功！");
            }else{
                results.setStatus(false);
                results.setMessage("修改失败！");
            }

        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("修改异常UpdateCommerce："+e);
        }
        return results;
    }

    @ApiOperation(value="删除商户",position =2,notes = "删除商户")
    @ApiImplicitParam(name="DeleteOneCommerce",value="删除商户",dataType="Commerce", paramType = "query",required = true)
    @RequestMapping(value = "/DeleteOneCommerce",method = RequestMethod.GET)
    public Results DeleteOneCommerce(String comGuid){
        Results results=new Results();
        try{
            int status=commerceService.DeleteOneCommerce(comGuid);
            if(status==1){
                results.setStatus(true);
                results.setMessage("删除商户成功！");
            }else{
                results.setStatus(false);
                results.setMessage("删除商户失败！");
            }

        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("删除商户异常DeleteOneCommerce："+e);
        }
        return results;
    }


    //******************************以下小程序调用*******************************

    @ApiOperation(value="获取所有商户--小程序调用",position =6,tags = "商户业务-小程序调用")
    @RequestMapping(value = "/GetAllCommerceToSmallRoutine",method = RequestMethod.GET)
    public Results GetAllCommerceToSmallRoutine (){
        Results results=new Results();
        List<Commerce> list=commerceService.GetAllCommerce();
        try{
            if(list.size()!=0){
                results.setData(list);
                results.setStatus(true);
                results.setMessage("获取成功！");
            }else{
                results.setStatus(false);
                results.setMessage("数据为空！暂无商户");
            }
        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("获取所有商户异常:"+e);
        }
        return results;
    }





}
