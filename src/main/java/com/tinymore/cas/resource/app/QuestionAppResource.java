package com.tinymore.cas.resource.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinymore.cas.model.MQuestion;
import com.tinymore.cas.service.IQuestion;

@Controller  
@RequestMapping("/app/question")
@CrossOrigin(origins="*")
public class QuestionAppResource {
	
	@Autowired
	private IQuestion service;
	
	@RequestMapping(value = "/get",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public MQuestion getQuestionList(HttpServletRequest params){
		String cqId = params.getParameter("cqId");
		return service.getQuestionByPrimaryKey(cqId);
	}
	

}
