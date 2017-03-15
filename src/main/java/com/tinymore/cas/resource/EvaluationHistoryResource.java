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
import com.tinymore.cas.model.MEvaluationHistory;
import com.tinymore.cas.service.IEvaluationHistory;

@Controller  
@RequestMapping("/evaluationhistory")
@CrossOrigin(origins="*")
public class EvaluationHistoryResource {
	
	private static final Logger log = (Logger) LogManager.getLogger(EvaluationHistoryResource.class);
	
	@Autowired
	private IEvaluationHistory service;
	
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MEvaluationHistory> getEvaluationHistoryListByCuAccount(@RequestBody String cuAccount){
		List<MEvaluationHistory> histories = service.getEvaluationHistoryByCuAccount(cuAccount);
		log.info(JSON.toJSON(histories));
		return histories;
	}
	
	@RequestMapping(value = "/get",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public MEvaluationHistory getEvaluationHistoryByCehId(@RequestBody String cehId){
		MEvaluationHistory history = service.getEvaluationHistoryByPrimaryKey(cehId);
		log.info(JSON.toJSON(history));
		return history;
	}
}
