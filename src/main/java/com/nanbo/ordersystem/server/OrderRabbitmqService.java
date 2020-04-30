package com.nanbo.ordersystem.server;

import com.nanbo.ordersystem.entity.EateryOrderSystem.Order;
import com.nanbo.ordersystem.mapper.OrderMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class OrderRabbitmqService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    OrderMapper orderMapper;
//    //监听-死信队列
//    @RabbitListener(queues = "user.order.delay_queue")
//    public  void delayQueue(Message message){
//
//        System.out.println("收到(死信队列)消息："+message);
//    }

    //监听-死信接收队列
    @Transactional(rollbackFor=Exception.class)//数据库事务注解(回滚必加)
    @RabbitListener(queues = "user.order.receive_queue")
    public  void receiveQueue(Order order){
        try {
            List<Order> list= orderMapper.GetOrderInPayStatusForOrderSid(order.getOrderSid());
            if(list.size()==1){
                //查找到的数据是唯一的
                if(list.get(0).getOrderPayStatus()==1){
                    //用户已经支付了--完事
                    System.out.println("用户已支付了哈");
                }else {
                    if(list.get(0).getOrderPayStatus()==-1){
                        //订单已取消
                        System.out.println("订单已取消");
                    }else {
                        System.out.println("用户没有支付o(╥﹏╥)o");
                        //用户没有进行支付（订单无效）--把订单状态设置成（未支付无效）
                        int status=orderMapper.UpdateOrderInvalid(order.getOrderGuid());
                    }
                }
            }else{
                if(list.size()==0){
                    //订单已被删除（查不到订单数据）
                }else {
                    //查找到的数据不唯一，有麻烦
                }

            }
            System.out.println("收到(死信接收队列)消息："+order);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
            //有异常
        }
    }



//    @RabbitListener(queues = "atguigu")
//    public void receive02(Message message){
//        System.out.println(message.getBody());
//        System.out.println(message.getMessageProperties());
//    }


    public void SendToDelayQueue(Order order) {

//        TODO：设置超时，用mq处理已超时的下单记录（一旦记录超时，则处理为无效）
        rabbitTemplate.convertAndSend("user.order.delay_exchange", "user.order.delay_key", order, message -> {
            message.getMessageProperties().setExpiration("60000");//60秒后过期
            return message;
        });
//        return "创建订单成功";
    }

}
