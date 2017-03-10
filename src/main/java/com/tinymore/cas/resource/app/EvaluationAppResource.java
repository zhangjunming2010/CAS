package com.tinymore.cas.resource.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinymore.cas.model.MEvaluation;
import com.tinymore.cas.service.IEvaluation;

@Controller  
@RequestMapping("/app/evaluation")
@CrossOrigin(origins="*")
public class EvaluationAppResource {
	
	@Autowired
	private IEvaluation service;
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MEvaluation> getEvaluationList(HttpServletRequest params){
		String ceTitle = params.getParameter("ceTitle");
		int ceStatus = Integer.parseInt(params.getParameter("ceStatus"));
		return service.getEvaluationByParams(ceTitle, ceStatus);
	}
}
