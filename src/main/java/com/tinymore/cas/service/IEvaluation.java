package com.tinymore.cas.service;

import java.util.List;

import com.tinymore.cas.model.MEvaluation;

public interface IEvaluation {
	
	public int addEvaluation(MEvaluation record);
	
	public MEvaluation getEvaluationByTitle(String ceTitle);
	
	public MEvaluation getEvaluationByPrimaryKey(String ceId);
	
	public List<MEvaluation> getEvaluationByParams(String ceTitle,Integer ceStatus);
	
	public int delEvaluation(String ceId);
	
	public int updateEvaluation(MEvaluation record);

}
