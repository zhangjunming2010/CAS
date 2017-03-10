package com.tinymore.cas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinymore.cas.dao.MQuestionMapper;
import com.tinymore.cas.model.MQuestion;
import com.tinymore.cas.service.IQuestion;

@Service("IQuestionService")
public class IQuestionImpl implements IQuestion {
	
	@Autowired
	private MQuestionMapper dao;

	@Override
	public int addQuestion(MQuestion record) {
		return dao.insert(record);
	}

	@Override
	public int updateQuestion(MQuestion record) {
		return dao.updateByPrimaryKey(record);
	}
	
	@Override
	public MQuestion getQuestionByTitle(String cqTitle) {
		return dao.selectByTitle(cqTitle);
	}

	@Override
	public List<MQuestion> getQuestionListByParams(String searchKey, String ccId, String ctId) {
		return dao.selectByParams(searchKey, ccId, ctId);
	}

	@Override
	public int delQuestion(String cqId) {
		return dao.deleteByPrimaryKey(cqId);
	}

	@Override
	public MQuestion getQuestionByPrimaryKey(String cqId) {
		return dao.selectByPrimaryKey(cqId);
	}

}
