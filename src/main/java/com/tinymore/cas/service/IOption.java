package com.tinymore.cas.service;

import java.util.List;

import com.tinymore.cas.model.MOption;

public interface IOption {
	
	public int addOption(MOption record);
	
	public int updateOption(MOption record);
	
	public List<MOption> getOptionListByCqId(String cqId);
	
	public int delOption(String coId);
	
	public int delOptionByCqId(String cqId);

}
