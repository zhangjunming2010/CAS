package com.tinymore.cas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinymore.cas.dao.MOptionMapper;
import com.tinymore.cas.model.MOption;
import com.tinymore.cas.service.IOption;

@Service("IOptionService")
public class IOptionImpl implements IOption {

	@Autowired
	private MOptionMapper dao;
	
	@Override
	public int addOption(MOption record) {
		return dao.insert(record);
	}

	@Override
	public int updateOption(MOption record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public List<MOption> getOptionListByCqId(String cqId) {
		return dao.selectByCqId(cqId);
	}

	@Override
	public int delOption(String coId) {
		return dao.deleteByPrimaryKey(coId);
	}

	@Override
	public int delOptionByCqId(String cqId) {
		return dao.deleteByCqId(cqId);
	}

}
