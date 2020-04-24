package com.nanbo.ordersystem.mapper;

import com.nanbo.ordersystem.entity.EateryOrderSystem.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserAddressMapper {

    //添加地址信息
    int AddUserAddressInfo(UserAddress userAddress);

    //根据学号获取用户信息地址
    List<UserAddress> GetAllUserAddressInfo(String uAXh);

    //根据food_guid获取单条用户地址信息
    UserAddress GetOneUserAddressInfo(String uAGuid);

    //修改用户信息地址
    int UpdateUserAddressInfo(UserAddress userAddress);

    //根据uAGuid删除用户信息地址
    int DeleteUserAddressInfo(String uAGuid);

    //把默认地址改成不默认  开始
    //根据uAGuid获取单条用户地址信息
    int SetAddressDefaultToFalse(String uAGuid);

    //根据学号获取用户是否有默认地址信息
    UserAddress GetUserDefaultAddressInfo(String uAXh);
    //把默认地址改成不默认  结束
}
