package com.tinymore.cas.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tinymore.cas.model.MEvaluationRelationKey;
import com.tinymore.cas.model.MQuestion;
import com.tinymore.cas.service.IEvaluationRelation;

@Controller  
@RequestMapping("/evaluationrelation")
@CrossOrigin(origins="*")
public class EvaluationRelationResource {
	
	@Autowired
	private IEvaluationRelation service;

	@RequestMapping(value = "/add",method = RequestMethod.POST,produces="application/json; charset=utf-8")
    @ResponseBody
	public void addRelation(@RequestBody String params) {
		JSONObject obj = JSON.parseObject(params);
		String ceId = obj.getString("ceId");
		String cqIds = obj.getString("cqIds");
		MEvaluationRelationKey relation = new MEvaluationRelationKey();
		relation.setCeId(ceId);
		relation.setCqId(cqIds);
		service.delRealation(relation);
		JSONArray arry = JSON.parseArray(cqIds);
		for (Object object : arry) {
			JSONObject jsonObj = JSON.parseObject(object.toString());
			relation = new MEvaluationRelationKey();
			relation.setCeId(ceId);
			relation.setCqId(jsonObj.getString("cqId"));
			service.addRelation(relation);
		}
	}
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MQuestion> getRelationQuestionsList(@RequestBody String params){
		JSONObject obj = JSON.parseObject(params);
		String ceId = obj.getString("ceId");
		String cqId = obj.getString("cqId");
		MEvaluationRelationKey relation = new MEvaluationRelationKey();
		relation.setCeId(ceId);
		relation.setCqId(cqId);
		List<MQuestion> questions = service.getRelationQuestions(relation);
		return questions;
	}
}
