package com.tinymore.cas.service;

import java.util.List;

import com.tinymore.cas.model.MType;

public interface IType {
	
	public int addType(MType record);
	
	public int updateType(MType record);
	
	public MType getTypeByName(String ccName);
	
	public List<MType> getTypeListByParams(String searchKey);
	
	public int delType(String ccId);
}
