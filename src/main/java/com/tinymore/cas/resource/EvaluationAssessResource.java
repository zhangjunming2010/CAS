package com.tinymore.cas.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinymore.cas.model.MEvaluationAssess;
import com.tinymore.cas.service.IEvaluationAssess;

@Controller  
@RequestMapping("/evaluationassess")
@CrossOrigin(origins="*")
public class EvaluationAssessResource {
	
	@Autowired
	private IEvaluationAssess service;
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MEvaluationAssess> getOptionList(@RequestBody String ceId){
		return service.getEvaluationAssessByCeId(ceId);
	}

}
