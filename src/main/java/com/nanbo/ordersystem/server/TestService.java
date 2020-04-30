package com.nanbo.ordersystem.server;

import com.nanbo.ordersystem.entity.EateryOrderSystem.Test;
import com.nanbo.ordersystem.entity.EateryOrderSystem.Test2;
import com.nanbo.ordersystem.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService implements TestMapper {

    @Autowired
    private TestMapper testMapper;

    public int AddTest(Test test){
        return testMapper.AddTest(test);
    }
    //添加附件信息
    public int AddTest2(Test2 test2){
        return testMapper.AddTest2(test2);
    }
}
