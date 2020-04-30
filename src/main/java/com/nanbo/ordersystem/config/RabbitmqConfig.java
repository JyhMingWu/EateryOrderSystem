//package com.nanbo.ordersystem.config;
//
//import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class RabbitmqConfig {
//
//    //操作单播、点播、广播等
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    //操作创建交换器、创建队列、创建绑定规则
//    @Autowired
//    AmqpAdmin amqpAdmin;
//    //将对象Json序列化，即定义消息转换器
//
//    @Bean
//    public MessageConverter messageConverter(){
//        return  new Jackson2JsonMessageConverter();
//    }
//
//
//
//
//    /**
//     * 死信交换机
//     *
//     * @return
//     */
//    @Bean
//    public void userOrderDelayExchange() {
//        System.out.println("创建交换机user.order.delay_exchange");
//        amqpAdmin.declareExchange(new DirectExchange("user.order.delay_exchange"));
////        return new DirectExchange("user.order.delay_exchange");
//        System.out.println("创建交换机user.order.delay_exchange--结束");
//    }
//
//    /**
//     * 死信队列
//     *
//     * @return
//     */
//    @Bean
//    public void userOrderDelayQueue() {
//        System.out.println("创建队列 user.order.delay_queue");
//        Map<String, Object> map = new HashMap<>(16);
//        map.put("x-dead-letter-exchange", "user.order.receive_exchange");
//        map.put("x-dead-letter-routing-key", "user.order.receive_key");
//        amqpAdmin.declareQueue(new Queue("user.order.delay_queue", true, false, false, map));
//        System.out.println("创建队列 user.order.delay_queue--结束");
////        return new Queue("user.order.delay_queue", true, false, false, map);
//    }
//
//    /**
//     * 给死信队列绑定交换机
//     *
//     * @return
//     */
//    @Bean
//    public void userOrderDelayBinding() {
//        amqpAdmin.declareBinding(new Binding("user.order.delay_queue",Binding.DestinationType.QUEUE,"user.order.delay_exchange","user.order.delay_key",null));
////        return BindingBuilder.bind(userOrderDelayQueue()).to(userOrderDelayExchange()).with("user.order.delay_key");
//    }
//
//    /**
//     * 死信接收交换机
//     *
//     * @return
//     */
//    @Bean
//    public void userOrderReceiveExchange() {
//        amqpAdmin.declareExchange(new DirectExchange("user.order.receive_exchange"));
////        return new DirectExchange("user.order.receive_exchange");
//    }
//
//    /**
//     * 死信接收队列
//     *
//     * @return
//     */
//    @Bean
//    public void userOrderReceiveQueue() {
//        amqpAdmin.declareQueue(new Queue("user.order.receive_queue"));
////        return new Queue("user.order.receive_queue");
//    }
//
//    /**
//     * 死信交换机绑定消费队列
//     *
//     * @return
//     */
//    @Bean
//    public void userOrderReceiveBinding() {
//        amqpAdmin.declareBinding(new Binding("user.order.receive_queue",Binding.DestinationType.QUEUE,"user.order.receive_exchange","user.order.receive_key",null));
////        return BindingBuilder.bind(userOrderReceiveQueue()).to(userOrderReceiveExchange()).with("user.order.receive_key");
//    }
//
//}
