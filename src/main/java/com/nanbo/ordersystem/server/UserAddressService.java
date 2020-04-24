package com.nanbo.ordersystem.server;


import com.nanbo.ordersystem.entity.EateryOrderSystem.UserAddress;
import com.nanbo.ordersystem.mapper.UserAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressService implements UserAddressMapper {

    @Autowired
    private UserAddressMapper userAddressMapper;


    //添加地址信息
    public int AddUserAddressInfo(UserAddress userAddress){
        return userAddressMapper.AddUserAddressInfo(userAddress);
    }

    //根据学号获取用户信息地址
    public List<UserAddress> GetAllUserAddressInfo(String uAXh){
        return userAddressMapper.GetAllUserAddressInfo(uAXh);
    }

    //根据food_guid获取单条用户地址信息
    public UserAddress GetOneUserAddressInfo(String uAGuid){
        return userAddressMapper.GetOneUserAddressInfo(uAGuid);
    }

    //修改用户信息地址
    public int UpdateUserAddressInfo(UserAddress userAddress){
        return userAddressMapper.UpdateUserAddressInfo(userAddress);
    }

    //根据uAGuid删除用户信息地址
    public int DeleteUserAddressInfo(String uAGuid){
        return userAddressMapper.DeleteUserAddressInfo(uAGuid);
    }

    //把默认地址改成不默认  开始
    //根据uAGuid把默认地址改为false
    public int SetAddressDefaultToFalse(String uAGuid){
        return userAddressMapper.SetAddressDefaultToFalse(uAGuid);
    }

    //根据学号获取用户是否有默认地址信息
    public UserAddress GetUserDefaultAddressInfo(String uAXh){
        return userAddressMapper.GetUserDefaultAddressInfo(uAXh);
    }
    //把默认地址改成不默认  结束
}
