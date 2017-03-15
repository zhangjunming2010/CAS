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
import com.tinymore.cas.model.MEvaluationRelationKey;
import com.tinymore.cas.model.MQuestion;
import com.tinymore.cas.service.IEvaluationRelation;

@Controller  
@RequestMapping("/app/evaluationrelation")
@CrossOrigin(origins="*")
public class EvaluationRelationAppResource {
	
	private static final Logger log = (Logger) LogManager.getLogger(EvaluationRelationAppResource.class);
	
	@Autowired
	private IEvaluationRelation service;
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String getRelationQuestionsList(HttpServletRequest params){
		String ceId = params.getParameter("ceId");
		String cqId = params.getParameter("cqId");
		MEvaluationRelationKey relation = new MEvaluationRelationKey();
		relation.setCeId(ceId);
		relation.setCqId(cqId);
		List<MQuestion> questions = service.getRelationQuestions(relation);
		String ret = JSON.toJSONString(questions);
		log.info(ret);
		return ret;
	}
}
