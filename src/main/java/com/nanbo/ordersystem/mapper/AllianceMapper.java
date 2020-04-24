package com.nanbo.ordersystem.mapper;

import com.nanbo.ordersystem.entity.Alliance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Repository
@Mapper
public interface AllianceMapper {

    //获取所有的高校联盟
    List<Alliance> GetAllAlliance();

    //增加高校联盟
    int AddAlliance(Alliance alliance);

    //更改高校联盟数据
    int UpdateAllianceData(Alliance alliance);

    //获取单条高校联盟数据
    List<Alliance> GetOneAllianceData(@Param("alliance_id") int alliance_id);

    //删除单条联盟数据
    int DeleteAlliance(@Param("alliance_id") int alliance_id);

    //批量删除多条联盟数据
    int BatchDeleteAlliance(List list);
}
