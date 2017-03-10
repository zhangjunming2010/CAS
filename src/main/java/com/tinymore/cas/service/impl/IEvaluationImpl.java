package com.tinymore.cas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinymore.cas.dao.MEvaluationMapper;
import com.tinymore.cas.model.MEvaluation;
import com.tinymore.cas.service.IEvaluation;

@Service("IEvaluationService")
public class IEvaluationImpl implements IEvaluation {
	
	@Autowired
	private MEvaluationMapper dao;
	
	@Override
	public int addEvaluation(MEvaluation record) {
		return dao.insert(record);
	}

	@Override
	public MEvaluation getEvaluationByTitle(String ceTitle) {
		return dao.selectByTitle(ceTitle);
	}

	@Override
	public List<MEvaluation> getEvaluationByParams(String ceTitle, Integer ceStatus) {
		return dao.selectByParams(ceTitle, ceStatus);
	}

	@Override
	public int delEvaluation(String ceId) {
		return dao.deleteByPrimaryKey(ceId);
	}

	@Override
	public int updateEvaluation(MEvaluation record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public MEvaluation getEvaluationByPrimaryKey(String ceId) {
		return dao.selectByPrimaryKey(ceId);
	}

}
