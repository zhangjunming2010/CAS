package com.tinymore.cas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tinymore.cas.model.MCategory;

public interface MCategoryMapper {
    int deleteByPrimaryKey(String ccId);

    int insert(MCategory record);

    int insertSelective(MCategory record);

    MCategory selectByPrimaryKey(String ccId);
    
    MCategory selectByCcname(String ccName);
    
    List<MCategory> selectByParams(@Param("searchKey")String searchKey);
    
    int updateByPrimaryKeySelective(MCategory record);

    int updateByPrimaryKey(MCategory record);
    
}