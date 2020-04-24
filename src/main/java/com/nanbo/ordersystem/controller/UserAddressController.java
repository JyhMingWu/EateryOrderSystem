package com.nanbo.ordersystem.controller;


import com.nanbo.ordersystem.entity.EateryOrderSystem.Food;
import com.nanbo.ordersystem.entity.EateryOrderSystem.UserAddress;
import com.nanbo.ordersystem.entity.Result.Results;
import com.nanbo.ordersystem.mapper.UserAddressMapper;
import com.nanbo.ordersystem.server.UserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/UserAddress")
@Api(value="用户地址controller",tags={"用户地址业务-小程序调用"},position = 8)
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;


    @ApiOperation(value="用户添加地址信息",position =2,notes = "注：uAGuid和uAId不需要填写")
    @ApiImplicitParams({
            @ApiImplicitParam(name="uAXh",value="学号",dataType="String", paramType = "query",required = true),
            @ApiImplicitParam(name="uAPhone",value="联系电话",dataType="String", paramType = "query",required = true),
            @ApiImplicitParam(name="uAPeople",value="联系人",dataType="String", paramType = "query",required = true),
            @ApiImplicitParam(name="uAAddress",value="收件地址",dataType="String", paramType = "query",required = true),
            @ApiImplicitParam(name="uADefault",value="是否设为默认",dataType="BOOLEAN", paramType = "query",required = true)
    })
    @RequestMapping(value = "/UserAddressInfo",method = RequestMethod.POST)
    public Results UserAddressInfo(String uAXh,String uAPhone,String uAPeople,String uAAddress,boolean uADefault){
        Results results=new Results();
        int status=0;
        String error="";
        if(uAPeople.equals("")||uAAddress.equals("")||uAPhone.equals("")||uAXh.equals("")){
            results.setStatus(false);
            results.setMessage("输入数据格式有误，或为空，请重新输入！");
        }else{
            try {
                UserAddress userAddress=new UserAddress();
                userAddress.setuAXh(uAXh);
                userAddress.setuAPeople(uAPeople);
                userAddress.setuAAddress(uAAddress);
                userAddress.setuAPhone(uAPhone);
                userAddress.setuADefault(uADefault);
                if(!uADefault){
                    //如果不是默认
                    status=userAddressService.AddUserAddressInfo(userAddress);
                }else{
                    //如果设置为默认地址true
                    //先查看是否已经有默认的地址
                    UserAddress userAddress_default=userAddressService.GetUserDefaultAddressInfo(uAXh);
                    if(userAddress_default==null){
                        //没有已设置的默认地址，则直接进行插入
                        status=userAddressService.AddUserAddressInfo(userAddress);
                    }else{
                        //已有已设置的默认地址，则先将已默认的设置为false
                        int s=userAddressService.SetAddressDefaultToFalse(userAddress_default.getuAGuid());
                        if(s==1){
                            //已把默认的更改完，现在可以进行插入
                            status=userAddressService.AddUserAddressInfo(userAddress);
                        }else {
                            status=userAddressService.AddUserAddressInfo(userAddress);
                            //更改已设置默认的改为false失败
                            error="但设置默认地址失败！请稍后再试";
                        }
                    }
                }
                if(status==1){
                    results.setStatus(true);
                    results.setMessage("操作成功！"+error);
                }else{
                    results.setStatus(false);
                    results.setMessage("新增地址失败！"+error);
                }

            }catch (Exception e){
                results.setStatus(false);
                results.setMessage("创建用户信息地址异常UserAddressInfo："+e);
            }
        }
        return results;
    }

    @ApiOperation(value="根据学号获取用户信息地址",position =2,notes = "注：")
    @ApiImplicitParam(name="uAXh",value="学号",dataType="String", paramType = "query",required = true)
    @RequestMapping(value = "/GetAllUserAddressInfo",method = RequestMethod.GET)
    public Results GetAllUserAddressInfo(String uAXh){
        Results results=new Results();
        if(uAXh.equals("")){
            results.setStatus(false);
            results.setMessage("输入数据格式有误，或为空，请重新输入！");
        }else{
            try {
                List<UserAddress> list=userAddressService.GetAllUserAddressInfo(uAXh);
                if(list.size()==0){
                    results.setStatus(false);
                    results.setMessage("数据为空！");
                }else{
                    results.setData(list);
                    results.setStatus(true);
                    results.setMessage("获取成功！");
                }
            }catch (Exception e){
                results.setStatus(false);
                results.setMessage("根据学号获取用户信息地址异常GetAllUserAddressInfo："+e);
            }
        }
        return results;
    }


    @ApiOperation(value="根据uAGuid获取单条用户地址信息",position =2,notes = "注：")
    @ApiImplicitParam(name="uAGuid",value="用户地址Guid",dataType="String", paramType = "query",required = true)
    @RequestMapping(value = "/GetOneUserAddressInfo",method = RequestMethod.GET)
    public Results GetOneUserAddressInfo(String uAGuid){
        Results results=new Results();
        if(uAGuid.equals("")){
            results.setStatus(false);
            results.setMessage("输入数据格式有误，或为空，请重新输入！");
        }else{
            try {
                UserAddress userAddress=userAddressService.GetOneUserAddressInfo(uAGuid);
                results.setData(userAddress);
                results.setStatus(true);
                results.setMessage("获取成功！");
            }catch (Exception e){
                results.setStatus(false);
                results.setMessage("根据food_guid获取单条用户地址信息异常GetOneUserAddressInfo："+e);
            }
        }
        return results;
    }


    @ApiOperation(value="修改用户信息地址",position =2,notes = "注：uAId不需要填写")
    @ApiImplicitParams({
            @ApiImplicitParam(name="uAGuid",value="用户地址主键guid",dataType="String", paramType = "query",required = true),
            @ApiImplicitParam(name="uAXh",value="学号",dataType="String", paramType = "query",required = true),
            @ApiImplicitParam(name="uAPhone",value="联系电话",dataType="String", paramType = "query",required = true),
            @ApiImplicitParam(name="uAPeople",value="联系人",dataType="String", paramType = "query",required = true),
            @ApiImplicitParam(name="uAAddress",value="收件地址",dataType="String", paramType = "query",required = true),
            @ApiImplicitParam(name="uADefault",value="是否设为默认",dataType="BOOLEAN", paramType = "query",required = true)
    })
    @RequestMapping(value = "/UpdateUserAddressInfo",method = RequestMethod.POST)
    public Results UpdateUserAddressInfo(String uAGuid,String uAXh,String uAPhone,String uAPeople,String uAAddress,boolean uADefault){
        Results results=new Results();
        int status=0;
        String error="";
        if(uAPeople.equals("")||uAAddress.equals("")||uAPhone.equals("")||uAXh.equals("")){
            results.setStatus(false);
            results.setMessage("输入数据格式有误，或为空，请重新输入！");
        }else{
            try {
                UserAddress userAddress=new UserAddress();
                userAddress.setuAGuid(uAGuid);
                userAddress.setuAXh(uAXh);
                userAddress.setuAPeople(uAPeople);
                userAddress.setuAAddress(uAAddress);
                userAddress.setuAPhone(uAPhone);
                userAddress.setuADefault(uADefault);
                if(!uADefault){
                    //如果不是默认
                    status=userAddressService.UpdateUserAddressInfo(userAddress);
                }else{
                    //如果设置为默认地址true
                    //先查看是否已经有默认的地址
                    UserAddress userAddress_default=userAddressService.GetUserDefaultAddressInfo(uAXh);
                    if(userAddress_default==null){
                        //没有已设置的默认地址，则直接进行修改
                        status=userAddressService.UpdateUserAddressInfo(userAddress);
                    }else{
                        //已有已设置的默认地址，则先将已默认的设置为false
                        int s=userAddressService.SetAddressDefaultToFalse(userAddress_default.getuAGuid());
                        if(s==1){
                            //已把默认的更改完，现在可以进行修改
                            status=userAddressService.UpdateUserAddressInfo(userAddress);
                        }else {
                            status=userAddressService.UpdateUserAddressInfo(userAddress);
                            //更改已设置默认的改为false失败
                            error="但设置默认地址失败！请稍后再试";
                        }
                    }
                }
                if(status==1){
                    results.setStatus(true);
                    results.setMessage("操作成功！"+error);
                }else{
                    results.setStatus(false);
                    results.setMessage("修改地址失败！"+error);
                }

            }catch (Exception e){
                results.setStatus(false);
                results.setMessage("修改用户信息地址异常UpdateUserAddressInfo："+e);
            }
        }
        return results;
    }

    @ApiOperation(value="根据uAGuid删除用户信息地址",position =2,notes = "注：")
    @ApiImplicitParam(name="uAGuid",value="用户地址Guid",dataType="String", paramType = "query",required = true)
    @RequestMapping(value = "/DeleteUserAddressInfo",method = RequestMethod.GET)
    public Results DeleteUserAddressInfo(String uAGuid){
        Results results=new Results();
        try {
            int status=userAddressService.DeleteUserAddressInfo(uAGuid);
            if(status==1){
                results.setStatus(true);
                results.setMessage("删除成功！");
            }else{
                results.setStatus(false);
                results.setMessage("删除用户信息地址失败！");
            }
        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("删除用户信息地址异常DeleteUserAddressInfo："+e);
        }

        return results;
    }
}
