package com.tinymore.cas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tinymore.cas.model.MType;

public interface MTypeMapper {
    int deleteByPrimaryKey(String ctId);

    int insert(MType record);

    int insertSelective(MType record);

    MType selectByPrimaryKey(String ctId);

    int updateByPrimaryKeySelective(MType record);

    int updateByPrimaryKey(MType record);
    
    MType selectByCtname(String ctName);
    
    List<MType> selectByParams(@Param("searchKey")String searchKey);
}