package com.tinymore.cas.service;

import java.util.List;

import com.tinymore.cas.model.MEvaluationRelationKey;
import com.tinymore.cas.model.MQuestion;

public interface IEvaluationRelation {
	
	public List<MQuestion> getRelationQuestions(MEvaluationRelationKey record);
	
	public int addRelation(MEvaluationRelationKey record);
	
	public int delRealation(MEvaluationRelationKey record);

}
