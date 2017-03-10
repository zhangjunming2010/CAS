package com.tinymore.cas.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinymore.cas.model.MEvaluationHistory;
import com.tinymore.cas.service.IEvaluationHistory;

@Controller  
@RequestMapping("/evaluationhistory")
@CrossOrigin(origins="*")
public class EvaluationHistoryResource {
	
	@Autowired
	private IEvaluationHistory service;
	
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MEvaluationHistory> getEvaluationHistoryListByCuAccount(@RequestBody String cuAccount){
		return service.getEvaluationHistoryByCuAccount(cuAccount);
	}
	
	@RequestMapping(value = "/get",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public MEvaluationHistory getEvaluationHistoryByCehId(@RequestBody String cehId){
		return service.getEvaluationHistoryByPrimaryKey(cehId);
	}
}
