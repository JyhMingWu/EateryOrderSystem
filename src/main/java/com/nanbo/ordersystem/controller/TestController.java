package com.nanbo.ordersystem.controller;

import com.nanbo.ordersystem.entity.EateryOrderSystem.Test;
import com.nanbo.ordersystem.entity.EateryOrderSystem.Test2;
import com.nanbo.ordersystem.entity.Result.Results;
import com.nanbo.ordersystem.mapper.TestMapper;
import com.nanbo.ordersystem.server.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/Test")
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Transactional(rollbackFor=Exception.class)//数据库事务注解(回滚必加)
    @RequestMapping("/test")
    public Results Test(){
        Results results=new Results();
       try {

            Test test=new Test();
            test.setName("abc");
            test.setPassword("202122");
            int i=testService.AddTest(test);
            if(i==1){
                Test2 test2=new Test2();
                test2.setCreated("这是test2");
                test2.setContent("这是Test2的内容");
                int s=testService.AddTest2(test2);
                results.setMessage("处理了s："+s);
            }else {
                results.setMessage("处理了i："+i);
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
            results.setMessage("出现了异常");
        }
        return results;
    }


}
