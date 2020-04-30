package com.nanbo.ordersystem.mapper;

import com.nanbo.ordersystem.entity.EateryOrderSystem.Test;
import com.nanbo.ordersystem.entity.EateryOrderSystem.Test2;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestMapper {

    int AddTest(Test test);

    //添加附件信息
    int AddTest2(Test2 test2);
}
