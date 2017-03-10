package com.tinymore.cas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinymore.cas.dao.MEvaluationRelationMapper;
import com.tinymore.cas.model.MEvaluationRelationKey;
import com.tinymore.cas.model.MQuestion;
import com.tinymore.cas.service.IEvaluationRelation;

@Service("IEvaluationRelation")
public class IEvaluationRelationImpl implements IEvaluationRelation {
	
	@Autowired
	private MEvaluationRelationMapper dao;

	@Override
	public int addRelation(MEvaluationRelationKey record) {
		return dao.insert(record);
	}

	@Override
	public int delRealation(MEvaluationRelationKey record) {
		return dao.deleteByPrimaryKey(record);
	}

	@Override
	public List<MQuestion> getRelationQuestions(MEvaluationRelationKey record) {
		return dao.selectRelationQuestions(record);
	}

}
