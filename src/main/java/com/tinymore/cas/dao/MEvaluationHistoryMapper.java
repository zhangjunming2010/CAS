package com.tinymore.cas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tinymore.cas.model.MEvaluationHistory;

public interface MEvaluationHistoryMapper {
    int deleteByPrimaryKey(String cehId);
    
    int deleteByCuId(@Param("cuId")String cuId);

    int insert(MEvaluationHistory record);

    int insertSelective(MEvaluationHistory record);

    MEvaluationHistory selectByPrimaryKey(String cehId);
    
    List<MEvaluationHistory> selectByCuId(@Param("cuId")String cuId);
    
    List<MEvaluationHistory> selectByAccount(@Param("cuAccount")String cuAccount);

    int updateByPrimaryKeySelective(MEvaluationHistory record);

    int updateByPrimaryKeyWithBLOBs(MEvaluationHistory record);

    int updateByPrimaryKey(MEvaluationHistory record);
}