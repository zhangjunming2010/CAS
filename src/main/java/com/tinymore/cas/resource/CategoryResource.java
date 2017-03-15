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
import com.alibaba.fastjson.JSONObject;
import com.tinymore.cas.model.MCategory;
import com.tinymore.cas.model.MQuestion;
import com.tinymore.cas.service.ICategory;
import com.tinymore.cas.service.IOption;
import com.tinymore.cas.service.IQuestion;
import com.tinymore.cas.utils.BaseUtil;

@Controller  
@RequestMapping("/category")
@CrossOrigin(origins="*")
public class CategoryResource {
	
	private static final Logger log = (Logger) LogManager.getLogger(EvaluationAssessResource.class);
	
	@Autowired
	private ICategory service;
	@Autowired
	private IQuestion iq;
	@Autowired
	private IOption io;

	@RequestMapping(value = "/add",method = RequestMethod.POST,produces="application/json; charset=utf-8")
    @ResponseBody
	public String addCategory(@RequestBody String ccName) {
		String code = "-1";
		JSONObject obj = new JSONObject();
		MCategory category = service.getCategoryByName(ccName);
		if(category == null) {
			 category = new MCategory();
			category.setCcId(BaseUtil.UUID());
			category.setCcName(ccName);
			int count = service.addCategory(category);
			if(count > 0) {
				code = "0";
			}
		}
		obj.put("code", code);
		return JSON.toJSONString(obj);
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String updateCategory(@RequestBody MCategory record){
		String code = "-1";
		JSONObject obj = new JSONObject();
		MCategory category = service.getCategoryByName(record.getCcName());
		if(category == null) {
			int count = service.updateCategory(record);
			if(count > 0) {
				code = "0";
			}
		}
		obj.put("code", code);
		return JSON.toJSONString(obj);
	}
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MCategory> getCategoryList(@RequestBody String searchKey){
		List<MCategory> categories = service.getCategoryListByParams(searchKey);
		log.info(JSON.toJSON(categories));
		return categories;
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String delCategory(@RequestBody MCategory record) {
		String code = "-1";
		JSONObject obj = new JSONObject();
		List<MQuestion> questions = iq.getQuestionListByParams("ALL", record.getCcId(),"ALL");
		for(MQuestion question : questions) {
			io.delOptionByCqId(question.getCqId());
			iq.delQuestion(question.getCqId());
		}
		int count = service.delCategory(record.getCcId());
		if(count > 0) {
			code = "0";
		}
		obj.put("code", code);
		return JSON.toJSONString(obj);
	}
}
