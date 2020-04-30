package com.nanbo.ordersystem.mapper;

import com.nanbo.ordersystem.entity.EateryOrderSystem.Order;
import com.nanbo.ordersystem.entity.EateryOrderSystem.OrderDetail;
import com.nanbo.ordersystem.entity.EateryOrderSystem.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper {


    //后台查找订单管理
    List<Order> GetAllOrderInPayStatus(int orderPayStatus);

    //根据orderGuid查找订单详细数据
    Order GetOrderInfo(String orderGuid);

    //删除订单（后台删除订单/用户取消订单）
    int DeleteOrder(String orderGuid);

    //删除订单明细的数据（后台删除订单/用户取消订单）
    int DeleteOrderDetail(String orderDOrderSid);







    //******************************以下小程序调用*******************************

    //根据学号获取用户是否有默认地址信息
    UserAddress GetUserDefaultAddressInfo(String uAXh);

    //批量插入订单明细
    int AddOrderDetails(List<OrderDetail> list);

    //添加地址信息
    int AddOrder(Order order);


    //******************************以下RabbitMQ死信接受队列进行查询*******************************
    //RabbitMQ死信接受队列-查找订单支付状态
    List<Order> GetOrderInPayStatusForOrderSid(String orderSid);

    //RabbitMQ死信接受队列-修改订单成（未支付无效状态/取消订单）
    int UpdateOrderInvalid(String orderGuid);
}
