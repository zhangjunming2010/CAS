package com.tinymore.cas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinymore.cas.dao.MEvaluationAssessMapper;
import com.tinymore.cas.model.MEvaluationAssess;
import com.tinymore.cas.service.IEvaluationAssess;

@Service("IEvaluationAssessService")
public class IEvaluationAssessImpl implements IEvaluationAssess {
	
	@Autowired
	private MEvaluationAssessMapper dao;

	@Override
	public List<MEvaluationAssess> getEvaluationAssessByCeId(String ceId) {
		return dao.selectByCeId(ceId);
	}

	@Override
	public int addEvaluationAssess(MEvaluationAssess record) {
		return dao.insert(record);
	}

	@Override
	public int delEvaluationAssess(String ceaId) {
		return dao.deleteByPrimaryKey(ceaId);
	}

	@Override
	public int updateEvaluationAssess(MEvaluationAssess record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int delEvaluationAssessByCeId(String ceId) {
		return dao.deleteByCeId(ceId);
	}

}
