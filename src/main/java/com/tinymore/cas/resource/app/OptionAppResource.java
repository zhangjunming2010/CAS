package com.tinymore.cas.resource.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tinymore.cas.model.MOption;
import com.tinymore.cas.service.IOption;

@Controller  
@RequestMapping("/app/option")
@CrossOrigin(origins="*")
public class OptionAppResource {
	
	private static final Logger log = (Logger) LogManager.getLogger(OptionAppResource.class);

	@Autowired
	private IOption service;
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MOption> getOptionList(HttpServletRequest params){
		String cqId = params.getParameter("cqId");
		List<MOption> options = service.getOptionListByCqId(cqId);
		log.info(JSON.toJSON(options));
		return options;
	}
	
}
