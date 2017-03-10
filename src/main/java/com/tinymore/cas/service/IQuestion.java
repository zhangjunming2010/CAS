package com.tinymore.cas.service;

import java.util.List;

import com.tinymore.cas.model.MQuestion;

public interface IQuestion {
	
	public int addQuestion(MQuestion record);
	
	public int updateQuestion(MQuestion record);
	
	public List<MQuestion> getQuestionListByParams(String searchKey,String ccId,String ctId);
	
	public MQuestion getQuestionByTitle(String cqTitle);
	
	public MQuestion getQuestionByPrimaryKey(String cqId);
	
	public int delQuestion(String cqId); 
}
