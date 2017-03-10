package com.tinymore.cas.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinymore.cas.model.MOption;
import com.tinymore.cas.service.IOption;

@Controller  
@RequestMapping("/option")
@CrossOrigin(origins="*")
public class OptionResource {

	@Autowired
	private IOption service;
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MOption> getOptionList(@RequestBody String cqId){
		return service.getOptionListByCqId(cqId);
	}
	
}
