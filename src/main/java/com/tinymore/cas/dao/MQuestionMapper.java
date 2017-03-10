package com.tinymore.cas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tinymore.cas.model.MQuestion;

public interface MQuestionMapper {
	
    int deleteByPrimaryKey(String cqId);
    
    int insert(MQuestion record);

    int insertSelective(MQuestion record);

    MQuestion selectByPrimaryKey(String cqId);
    
    MQuestion selectByTitle(String cqTitle);
    
    List<MQuestion> selectByParams(@Param("searchKey")String searchKey,@Param("ccId")String ccId,@Param("ctId")String ctId);

    int updateByPrimaryKeySelective(MQuestion record);

    int updateByPrimaryKey(MQuestion record);
}