package com.nanbo.ordersystem.server;


import com.nanbo.ordersystem.entity.EateryOrderSystem.Order;
import com.nanbo.ordersystem.entity.EateryOrderSystem.OrderDetail;
import com.nanbo.ordersystem.entity.EateryOrderSystem.UserAddress;
import com.nanbo.ordersystem.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderMapper {

    @Autowired
    private OrderMapper orderMapper;


    //后台查找订单管理
    public List<Order> GetAllOrderInPayStatus(int orderPayStatus){
        return orderMapper.GetAllOrderInPayStatus(orderPayStatus);
    }

    //根据orderGuid查找订单详细数据
    public Order GetOrderInfo(String orderGuid){
        return orderMapper.GetOrderInfo(orderGuid);
    }


    //删除订单（后台删除订单/用户取消订单）
    public int DeleteOrder(String orderGuid){
        return orderMapper.DeleteOrder(orderGuid);
    }

    //删除订单明细的数据（后台删除订单/用户取消订单）
    public int DeleteOrderDetail(String orderDOrderSid){
        return orderMapper.DeleteOrderDetail(orderDOrderSid);
    }








    //根据学号获取用户是否有默认地址信息
    public UserAddress GetUserDefaultAddressInfo(String uAXh){
        return orderMapper.GetUserDefaultAddressInfo(uAXh);
    }

    //批量插入订单明细
    public int AddOrderDetails(List<OrderDetail> list){
        return orderMapper.AddOrderDetails(list);
    }

    //添加地址信息
    public int AddOrder(Order order){
        return orderMapper.AddOrder(order);
    }



}
