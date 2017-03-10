package com.tinymore.cas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinymore.cas.dao.MCategoryMapper;
import com.tinymore.cas.model.MCategory;
import com.tinymore.cas.service.ICategory;

@Service("ICategoryService")
public class ICategoryImpl implements ICategory {
	
	@Autowired
	private MCategoryMapper dao;

	@Override
	public int addCategory(MCategory record) {
		return dao.insert(record);
	}

	@Override
	public int updateCategory(MCategory record) {
		return dao.updateByPrimaryKey(record);	
	}

	@Override
	public MCategory getCategoryByName(String ccName) {
		return dao.selectByCcname(ccName);
	}

	@Override
	public List<MCategory> getCategoryListByParams(String searchKey) {
		return dao.selectByParams(searchKey);
	}

	@Override
	public int delCategory(String ccId) {
		return dao.deleteByPrimaryKey(ccId);
	}
	

}
