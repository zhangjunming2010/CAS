package com.tinymore.cas.service;

import java.util.List;

import com.tinymore.cas.model.MEvaluationHistory;

public interface IEvaluationHistory {
	
	public MEvaluationHistory getEvaluationHistoryByPrimaryKey(String cehId);
	
	public List<MEvaluationHistory> getEvaluationHistoryByCuId(String cuId);
	
	public List<MEvaluationHistory> getEvaluationHistoryByCuAccount(String cuAccount);

	public int addEvaluationHistory(MEvaluationHistory record);
	
	public int delEvaluationHistoryByCuId(String cuId);
}
