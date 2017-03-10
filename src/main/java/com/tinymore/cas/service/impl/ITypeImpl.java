package com.tinymore.cas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinymore.cas.dao.MTypeMapper;
import com.tinymore.cas.model.MType;
import com.tinymore.cas.service.IType;

@Service("ITypeService")
public class ITypeImpl implements IType {
	
	@Autowired
	private MTypeMapper dao;

	@Override
	public int addType(MType record) {
		return dao.insert(record);
	}

	@Override
	public int updateType(MType record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public MType getTypeByName(String ctName) {
		return dao.selectByCtname(ctName);
	}

	@Override
	public List<MType> getTypeListByParams(String searchKey) {
		return dao.selectByParams(searchKey);
	}

	@Override
	public int delType(String ccId) {
		return dao.deleteByPrimaryKey(ccId);
	}
	

}
