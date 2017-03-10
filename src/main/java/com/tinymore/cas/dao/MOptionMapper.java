package com.tinymore.cas.dao;

import java.util.List;

import com.tinymore.cas.model.MOption;

public interface MOptionMapper {
    int deleteByPrimaryKey(String coId);

    int deleteByCqId(String cqId);
    
    int insert(MOption record);

    int insertSelective(MOption record);

    MOption selectByPrimaryKey(String coId);
    
    List<MOption> selectByCqId(String cqId);

    int updateByPrimaryKeySelective(MOption record);

    int updateByPrimaryKey(MOption record);
}