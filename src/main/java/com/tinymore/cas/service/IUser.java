package com.tinymore.cas.service;

import java.util.List;

import com.tinymore.cas.model.MUser;

public interface IUser {
	
	public MUser getUserByAccountAndPassword(String cuAccount,String cuPassword);
	
	public int addUser(MUser record);
	
	public MUser selectByAccount(String account);
	
	public List<MUser> selectByParams(String params);
	
	public int updateUser(MUser record);
	
	public int delUser(String cuId);
}
