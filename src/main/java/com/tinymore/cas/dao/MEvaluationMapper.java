package com.tinymore.cas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tinymore.cas.model.MEvaluation;

public interface MEvaluationMapper {
    int deleteByPrimaryKey(String ceId);

    int insert(MEvaluation record);

    int insertSelective(MEvaluation record);

    MEvaluation selectByPrimaryKey(String ceId);
    
    MEvaluation selectByTitle(String ceTitle);
    
    List<MEvaluation> selectByParams(@Param("ceTitle")String ceTitle,@Param("ceStatus")Integer ceStatus);

    int updateByPrimaryKeySelective(MEvaluation record);

    int updateByPrimaryKey(MEvaluation record);
}