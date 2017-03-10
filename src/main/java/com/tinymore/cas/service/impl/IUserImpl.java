package com.tinymore.cas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tinymore.cas.dao.MUserMapper;
import com.tinymore.cas.model.MUser;
import com.tinymore.cas.service.IUser;

@Service("IUserService")
public class IUserImpl implements IUser {
	
	@Autowired
	private MUserMapper dao;

	@Override
	public MUser getUserByAccountAndPassword(String cuAccount,String cuPassword) {
		return dao.selectByAccountAndPassword(cuAccount,cuPassword);
	}

	@Override
	public int addUser(MUser record) {
		return dao.insert(record);
	}

	@Override
	public MUser selectByAccount(String account) {
		return dao.selectByAccount(account);
	}

	@Override
	public List<MUser> selectByParams(String params) {
		JSONObject obj = JSON.parseObject(params);
		return dao.selectByParams(obj.getString("searchKey"),obj.getInteger("userStatus"));
	}

	@Override
	public int updateUser(MUser record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int delUser(String cuId) {
		return dao.deleteByPrimaryKey(cuId);
	}
	

}
