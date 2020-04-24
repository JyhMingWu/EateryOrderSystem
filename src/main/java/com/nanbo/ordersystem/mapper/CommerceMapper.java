package com.nanbo.ordersystem.mapper;

import com.nanbo.ordersystem.entity.EateryOrderSystem.Commerce;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommerceMapper {

    //获取所有商户
    List<Commerce> GetAllCommerce();

    //获取单条商户信息
    Commerce GetOneCommerceInfo(String comGuid);

    //增加商户
    int AddCommerce(Commerce commerce);

    //更改商户信息
    int UpdateCommerceInfo(Commerce commerce);

    //删除单条商户数据
    int DeleteOneCommerce(String comGuid);

}
