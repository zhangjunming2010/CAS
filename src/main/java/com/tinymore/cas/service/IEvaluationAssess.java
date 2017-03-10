package com.tinymore.cas.service;

import java.util.List;

import com.tinymore.cas.model.MEvaluationAssess;

public interface IEvaluationAssess {

	public int addEvaluationAssess(MEvaluationAssess record);

	public List<MEvaluationAssess> getEvaluationAssessByCeId(String ceId);
	
	public int delEvaluationAssess (String ceaId);
	
	public int delEvaluationAssessByCeId (String ceId);
	
	public int updateEvaluationAssess(MEvaluationAssess record);
	
}
