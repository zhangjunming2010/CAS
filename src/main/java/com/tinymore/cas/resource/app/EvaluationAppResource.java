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
import com.tinymore.cas.model.MEvaluation;
import com.tinymore.cas.service.IEvaluation;

@Controller  
@RequestMapping("/app/evaluation")
@CrossOrigin(origins="*")
public class EvaluationAppResource {
	
	private static final Logger log = (Logger) LogManager.getLogger(EvaluationAppResource.class);
	
	@Autowired
	private IEvaluation service;
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MEvaluation> getEvaluationList(HttpServletRequest params){
		String ceTitle = params.getParameter("ceTitle");
		int ceStatus = Integer.parseInt(params.getParameter("ceStatus"));
		List<MEvaluation> evaluations = service.getEvaluationByParams(ceTitle, ceStatus);
		log.info(JSON.toJSON(evaluations));
		return evaluations;
	}
}
