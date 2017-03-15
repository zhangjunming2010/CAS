package com.tinymore.cas.resource;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tinymore.cas.model.MOption;
import com.tinymore.cas.service.IOption;

@Controller  
@RequestMapping("/option")
@CrossOrigin(origins="*")
public class OptionResource {
	
	private static final Logger log = (Logger) LogManager.getLogger(OptionResource.class);

	@Autowired
	private IOption service;
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MOption> getOptionList(@RequestBody String cqId){
		List<MOption> options =  service.getOptionListByCqId(cqId);
		log.info(JSON.toJSON(options));
		return options;
	}
	
}
