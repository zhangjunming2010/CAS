package com.tinymore.cas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tinymore.cas.model.MEvaluationAssess;

public interface MEvaluationAssessMapper {
    int deleteByPrimaryKey(String ceaId);
    
    int deleteByCeId(@Param("ceId")String ceId);

    int insert(MEvaluationAssess record);

    int insertSelective(MEvaluationAssess record);

    MEvaluationAssess selectByPrimaryKey(String ceaId);
    
    List<MEvaluationAssess> selectByCeId(@Param("ceId")String ceId);

    int updateByPrimaryKeySelective(MEvaluationAssess record);

    int updateByPrimaryKey(MEvaluationAssess record);
}