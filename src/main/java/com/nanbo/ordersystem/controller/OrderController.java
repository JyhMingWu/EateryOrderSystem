package com.nanbo.ordersystem.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nanbo.ordersystem.entity.EateryOrderSystem.*;
import com.nanbo.ordersystem.entity.Result.Data;
import com.nanbo.ordersystem.entity.Result.Results;
import com.nanbo.ordersystem.server.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Order")
@Api(value="订单controller",tags={"订单controller-后台专用"},position = 4)
public class OrderController {

    @Autowired
    private OrderService orderService;



    @ApiOperation(value="获取所有订单(根据支付状态)",position =2,notes = "获取所有订单(根据支付状态)--后台专用")
    @RequestMapping(value = "/GetAllOrderInPayStatus",method = RequestMethod.GET)
    public Data GetAllOrderInPayStatus(int orderPayStatus){
        List<Order> list=orderService.GetAllOrderInPayStatus(orderPayStatus);
        Data data=new Data();
        data.setData(list);
        data.setStatus(true);
        data.setMessage("获取成功！");
        return data;
    }

    @ApiOperation(value="获取单条订单信息",position =2,notes = "获取单条订单信息--后台专用")
    @ApiImplicitParam(name="orderGuid",value="订单Guid",dataType="String", paramType = "query",required = true)
    @RequestMapping(value = "/GetOrderInfo",method = RequestMethod.GET)
    public Results GetOrderInfo(String orderGuid){
        Results results=new Results();
        try {
            Order order=orderService.GetOrderInfo(orderGuid);
            if(order!=null){
                results.setData(order);
                results.setStatus(true);
                results.setMessage("获取成功！");
            }else{
                results.setStatus(false);
                results.setMessage("空数据对象！");
            }


        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("获取单条订单信息GetOrderInfo异常:"+e);
        }
        return results;
    }





    @ApiOperation(value="删除订单/用户取消订单",position =8,notes = "删除订单/用户取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name="orderGuid",value="订单Guid",dataType="String", paramType = "query",required = true),
            @ApiImplicitParam(name="orderSid",value="订单编号",dataType="String", paramType = "query",required = true)
    })
    @RequestMapping(value = "/DeleteOrder",method = RequestMethod.POST)
    public Results DeleteOrder(String orderGuid,String orderSid){
        Results results=new Results();
        try {
            Order order=orderService.GetOrderInfo(orderGuid);
            if(order.getOrderSid().equals(orderSid)){
                //如果订单编号一致，可以进行下部分操作
                int DeleteDetailStatus=orderService.DeleteOrderDetail(orderSid);
                if(order.getListOrderDetail().size()==DeleteDetailStatus){
                    //如果订单明细删除条数一致，可以进行下部分操作
                    int DeleteOrderStatus=orderService.DeleteOrder(orderGuid);
                    if(DeleteOrderStatus==1){
                        results.setStatus(true);
                        results.setMessage("操作订单成功！");
                    }else{
                        results.setStatus(false);
                        results.setMessage("操作订单失败！");
                    }
                }else {
                    results.setStatus(false);
                    results.setMessage("操作明细订单失败！");
                }
            }else{
                results.setStatus(false);
                results.setMessage("订单明细编号与订单编号不一致，不可操作！");
            }
        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("删除订单/用户取消订单DeleteOrder异常:"+e);
        }
        return results;
    }


    //******************************以下小程序调用*******************************

    @ApiOperation(value="购物车结算",position =7,notes = "购物车结算--将生成支付订单",tags = "订单业务--小程序调用")
    @ApiImplicitParams({
            @ApiImplicitParam(name="listOrderDetail",value="OrderDetail对象List集合",dataType="String", paramType = "query",required = true),
            @ApiImplicitParam(name="orderComGuid",value="商户Guid",dataType="String", paramType = "query",required = true),
            @ApiImplicitParam(name="orderComName",value="商户名称",dataType="String", paramType = "query",required = true),
            @ApiImplicitParam(name="orderXh",value="学号",dataType="String", paramType = "query",required = true)
    })
    @RequestMapping(value = "/ShoppingCartSettlement",method = RequestMethod.POST)
   public Results ShoppingCartSettlement(String listOrderDetail,String orderComGuid,String orderComName,String orderXh){
//    public Results ShoppingCartSettlement(@RequestBody String listOrderDetail){

//        String s=JSON.toJSONString(listOrderDetail);
//        Order jsonObject = JSON.parseObject(s,Order.class);
//        System.out.println("输出listOrderDetail："+jsonObject);
        //购物车结算流程：
        /*
        ** 1、前端传来 订单明细对象List,进行总量和总金额计算
        *  2、前端传来 订单所属商户Guid和商户名称
        *  3、前端传来 学生学号
        *
        *  4、获得数据后对 订单明细对象List,进行总量和总金额计算
        *  5、获取学生学号是否有默认地址，有的话则进行设置进order对象中
         */
        Results results=new Results();
        //接受前端传来的List<OrderDetail>详细订单内容，由此结算
        List<OrderDetail> list= JSON.parseArray(listOrderDetail,OrderDetail.class);

        Order order=new Order();
        //用于计算总数量
        int foodCount=0;
        //用于计算总金额
        BigDecimal foodSumPrice=new BigDecimal("0.0").setScale(2, BigDecimal.ROUND_DOWN);

        long  timeNew =  System.currentTimeMillis();
        //此处生成订单编号--开始
        Date d = new Date();
        int randomNum = (int)((Math.random()*9+1)*100000); // 随机数 6 位
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmssSSSSS");
        String DateNow = sdf.format(d);
        String OrderSid=DateNow+randomNum;
        //此处生成订单编号--结束
        order.setOrderSid(OrderSid);


        //设置所属商户Guid
        order.setOrderComGuid(orderComGuid);
        //设置所属商户名称
        order.setOrderComName(orderComName);

        //计算总数量、总金额
        for(int i=0;i<list.size();i++){
            //获取单个金额
            BigDecimal price=new BigDecimal(list.get(i).getOrderDFoodPrice().toString()).setScale(2, BigDecimal.ROUND_DOWN);
            //获取单个数量
            BigDecimal number=new BigDecimal(list.get(i).getOrderDNumber()).setScale(2, BigDecimal.ROUND_DOWN);
            //进行乘法运算
            BigDecimal multiply = price.multiply(number).setScale(2, BigDecimal.ROUND_DOWN);
            //进行累加进总金额
            foodSumPrice=foodSumPrice.add(multiply);
            //累计总数量
            foodCount+=list.get(i).getOrderDNumber();
            //嵌入订单编号
            list.get(i).setOrderDOrderSid(OrderSid);
            //设置明细编号
            list.get(i).setOrderDNumId(i);
        }
        //设置需要支付总金额
        order.setOrderSumPay(foodSumPrice);
        //设置总数量
        order.setOrderSumNumber(foodCount);
        //获取是否有默认使用的地址
        UserAddress userAddress=orderService.GetUserDefaultAddressInfo(orderXh);
        if(userAddress==null){
            //若为空，则需要再次去选择地址
        }else{
            //不为空，则表示存在默认使用的地址
            //设置默认收件人
            order.setOrderPeople(userAddress.getuAPeople());
            //设置默认联系电话
            order.setOrderPhone(userAddress.getuAPhone());
            //设置默认地址
            order.setOrderAddress(userAddress.getuAAddress());
        }
        //设置订单中学生学号
        order.setOrderXh(orderXh);
        //设置订单菜品明细详情
        order.setListOrderDetail(list);


        results.setStatus(true);
        results.setData(order);

//        try {
//
//            System.out.println("长度为："+list.size());
//            for (int i=0;i<list.size();i++){
//                System.out.println("输出 "+i+" :"+list.get(i).toString());
//            }
//
//        }catch (Exception e){
//            results.setStatus(false);
//            results.setMessage("获取获取所有菜品异常："+e);
//        }
        return results;
    }



    @ApiOperation(value="提交订单",position =8,notes = "用户下单",tags = "订单业务--小程序调用")
    @ApiImplicitParam(name="order",value="订单对象",dataType="Order", paramType = "query",required = true)
    @RequestMapping(value = "/SubmissionOfOrders",method = RequestMethod.POST)
    public Results SubmissionOfOrders(String order){
        Results results=new Results();
        Order orders=JSON.parseObject(order,Order.class);
        System.out.println("输出："+orders);
        try {
            //收件人、收件地址、联系电话不能为空
            if(orders.getOrderPeople().equals("")||orders.getOrderAddress().equals("")||orders.getOrderPhone().equals("")){
                results.setStatus(false);
                results.setMessage("输入的参数有异常空值!");
            } else {
                //此处生成下单时间--开始
                Date d = new Date();
                SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                String DateNow = sdfs.format(d);
                //此处生成下单时间--结束
                //设置下单时间
                orders.setOrderStartTime(DateNow);
                //设置支付状态为0未支付
                orders.setOrderPayStatus(0);
                //设置订单状态-默认为0::为完成
                orders.setOrderStatus(0);
                //获取成功批量插入明细的条数
                List<OrderDetail> listOrderDetail=orders.getListOrderDetail();
                for(int i=0;i<listOrderDetail.size();i++){
                    BigDecimal price=new BigDecimal(listOrderDetail.get(i).getOrderDFoodPrice().toString()).setScale(2, BigDecimal.ROUND_DOWN);;
                    listOrderDetail.get(i).setOrderDFoodPrice(price);
                    System.out.println("输出："+listOrderDetail.get(i).getOrderDFoodPrice());
                }
                //获取成功插入订单的条数
                int orderDetails_result=orderService.AddOrderDetails(listOrderDetail);
                if(orderDetails_result==listOrderDetail.size()){
                    //明细添加成功
                    int order_result=orderService.AddOrder(orders);
                    if(order_result==1){
                        results.setStatus(true);
                        results.setMessage("提交订单成功!");
                    }else{
                        results.setStatus(false);
                        results.setMessage("提交订单失败!");
                    }
                }else {
                    results.setStatus(false);
                    results.setMessage("批量插入明细不正常！");
                }
            }
        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("提交订单异常："+e);
        }
        return results;
    }


}
