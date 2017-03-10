package com.tinymore.cas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tinymore.cas.model.MUser;

public interface MUserMapper {
    int deleteByPrimaryKey(String cuId);

    int insert(MUser record);

    int insertSelective(MUser record);

    MUser selectByPrimaryKey(String cuId);
    
    MUser selectByAccountAndPassword(@Param("cuAccount")String cuAccount,@Param("cuPassword")String cuPassword);
    
    MUser selectByAccount(@Param("cuAccount")String account);
    
    List<MUser> selectByParams(@Param("cuName")String cuName,@Param("cuStatus")Integer cuStatus);

    int updateByPrimaryKeySelective(MUser record);

    int updateByPrimaryKey(MUser record);
}