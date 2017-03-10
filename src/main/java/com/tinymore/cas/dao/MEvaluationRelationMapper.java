package com.tinymore.cas.dao;

import java.util.List;

import com.tinymore.cas.model.MEvaluationRelationKey;
import com.tinymore.cas.model.MQuestion;

public interface MEvaluationRelationMapper {
	
	List<MQuestion> selectRelationQuestions(MEvaluationRelationKey record);
	
    int deleteByPrimaryKey(MEvaluationRelationKey key);

    int insert(MEvaluationRelationKey record);

    int insertSelective(MEvaluationRelationKey record);
}