package com.tinymore.cas.service;

import java.util.List;

import com.tinymore.cas.model.MCategory;

public interface ICategory {
	
	public int addCategory(MCategory record);
	
	public int updateCategory(MCategory record);
	
	public MCategory getCategoryByName(String ccName);
	
	public List<MCategory> getCategoryListByParams(String searchKey);
	
	public int delCategory(String ccId);
}
