package com.nanbo.ordersystem.server;

import com.nanbo.ordersystem.entity.Alliance;
import com.nanbo.ordersystem.mapper.AllianceMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllianceService implements AllianceMapper {

    @Autowired
    private AllianceMapper allianceMapper;

    //获取高校联盟
    public List<Alliance> GetAllAlliance(){
        return allianceMapper.GetAllAlliance();
    }

    //增加高校联盟
    public int AddAlliance(Alliance alliance){
        return allianceMapper.AddAlliance(alliance);
    }

    //更改高校联盟数据
    public int UpdateAllianceData(Alliance alliance){
        return allianceMapper.UpdateAllianceData(alliance);
    }

    //获取单条高校联盟数据
    public List<Alliance> GetOneAllianceData(@Param("alliance_id")int alliance_id){
        return allianceMapper.GetOneAllianceData(alliance_id);
    }

    //删除单条联盟数据
    public int DeleteAlliance(@Param("alliance_id")int alliance_id){
        return allianceMapper.DeleteAlliance(alliance_id);
    }

    //批量删除多条联盟数据
    public int BatchDeleteAlliance(List list){
        return allianceMapper.BatchDeleteAlliance(list);
    }
}
