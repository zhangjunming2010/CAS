package com.tinymore.cas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinymore.cas.dao.MEvaluationHistoryMapper;
import com.tinymore.cas.model.MEvaluationHistory;
import com.tinymore.cas.service.IEvaluationHistory;

@Service("IEvaluationHistoryService")
public class IEvaluationHistoryImpl implements IEvaluationHistory {
	
	@Autowired
	private MEvaluationHistoryMapper dao;

	@Override
	public int addEvaluationHistory(MEvaluationHistory record) {
		return dao.insert(record);
	}

	@Override
	public List<MEvaluationHistory> getEvaluationHistoryByCuId(String cuId) {
		return dao.selectByCuId(cuId);
	}

	@Override
	public MEvaluationHistory getEvaluationHistoryByPrimaryKey(String cehId) {
		return dao.selectByPrimaryKey(cehId);
	}

	@Override
	public List<MEvaluationHistory> getEvaluationHistoryByCuAccount(String cuAccount) {
		return dao.selectByAccount(cuAccount);
	}

	@Override
	public int delEvaluationHistoryByCuId(String cuId) {
		return dao.deleteByCuId(cuId);
	}
	
}
