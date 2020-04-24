package com.nanbo.ordersystem.server;


import com.nanbo.ordersystem.entity.EateryOrderSystem.Commerce;
import com.nanbo.ordersystem.mapper.CommerceMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommerceService implements CommerceMapper {

    @Autowired
    private CommerceMapper commerceMapper;

    //获取所有商户
    public List<Commerce> GetAllCommerce(){
        return commerceMapper.GetAllCommerce();
    }

    //获取单条商户信息
    public Commerce GetOneCommerceInfo(String comGuid){
        return commerceMapper.GetOneCommerceInfo(comGuid);
    }

    //增加商户
    public int AddCommerce(Commerce commerce){return commerceMapper.AddCommerce(commerce);}

    //更改商户信息
    public int UpdateCommerceInfo(Commerce commerce){
        return commerceMapper.UpdateCommerceInfo(commerce);
    }

    //删除商户
    public int DeleteOneCommerce(String comGuid){
        return commerceMapper.DeleteOneCommerce(comGuid);
    }
}
