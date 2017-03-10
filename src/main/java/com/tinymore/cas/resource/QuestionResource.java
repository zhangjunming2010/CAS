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
import com.tinymore.cas.model.MOption;
import com.tinymore.cas.model.MQuestion;
import com.tinymore.cas.service.IOption;
import com.tinymore.cas.service.IQuestion;
import com.tinymore.cas.utils.BaseUtil;

@Controller  
@RequestMapping("/question")
@CrossOrigin(origins="*")
public class QuestionResource {
	
	@Autowired
	private IQuestion service;
	
	@Autowired
	private IOption ios;
	
	@RequestMapping(value = "/add",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String addQuestion(@RequestBody String params) {
		String code = "-1";
		String data = "新增题目失败，";
		JSONObject obj = new JSONObject();
		JSONObject object = JSON.parseObject(params);
		String cqTitle = object.getString("cqTitle");
		String ccId = object.getString("ccId");
		String ctId = object.getString("ctId");
		MQuestion question = service.getQuestionByTitle(cqTitle);
		if(question == null) {
			String cqId = BaseUtil.UUID();
			question = new MQuestion();
			question.setCqId(cqId);
			question.setCqTitle(cqTitle);
			question.setCcId(ccId);
			question.setCtId(ctId);
			int count = service.addQuestion(question);
			if(count > 0) {
				String options = object.getString("options");
				JSONArray arr = JSON.parseArray(options);
				for(int n=0;n<arr.size();n++) {
					JSONObject op = JSON.parseObject(arr.getString(n));
					String coTitle = op.getString("coTitle");
					Integer coScore = Integer.parseInt(op.getString("coScore"));
					MOption option = new MOption();
					option.setCqId(cqId);
					option.setCoId(BaseUtil.UUID());
					option.setCoTitle(coTitle);
					option.setCoScore(coScore);
					ios.addOption(option);
				}
				code = "0";
				data = "新增题目成功！";
			}else {
				data = data + "调用数据库出错！";
			}
		}else {
			data = data + "题目已存在！";
		}
		obj.put("code", code);
		obj.put("data", data);
		return JSON.toJSONString(obj);
	}
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MQuestion> getQuestionList(@RequestBody String params){
		JSONObject obj = JSON.parseObject(params);
		String searchKey = obj.getString("searchKey");
		String ccId = obj.getString("ccId");
		String ctId = obj.getString("ctId");
		return service.getQuestionListByParams(searchKey, ccId, ctId);
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String delQuestion(@RequestBody MQuestion record) {
		String code = "-1";
		String data = "删除题目失败！";
		JSONObject obj = new JSONObject();
		ios.delOptionByCqId(record.getCqId());
		int count = service.delQuestion(record.getCqId());
		if(count > 0) {
			code = "0";
			data = "删除题目成功！";
		}else {
			data = data + "调用数据库出错！";
		}
		obj.put("code", code);
		obj.put("data", data);
		return JSON.toJSONString(obj);
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String updateQuestion(@RequestBody String params) {
		String code = "-1";
		String data = "修改题目失败，";
		JSONObject obj = new JSONObject();
		JSONObject object = JSON.parseObject(params);
		String cqId = object.get("cqId").toString();
		String cqTitle = object.get("cqTitle").toString();
		String ccId = object.get("ccId").toString();
		String ctId = object.get("ctId").toString();
		MQuestion question = service.getQuestionByTitle(cqTitle);
		question = new MQuestion();
		question.setCqId(cqId);
		question.setCqTitle(cqTitle);
		question.setCcId(ccId);
		question.setCtId(ctId);
		int count = service.updateQuestion(question);
		if(count > 0) {
			String delCoIds = object.getString("delCoIds");
			JSONArray delCoIdArr = JSON.parseArray(delCoIds);
			for(int i = 0;i<delCoIdArr.size();i++) {
				ios.delOption(delCoIdArr.getString(i));
			}
			String options = object.getString("options");
			JSONArray arr = JSON.parseArray(options);
			for(int n=0;n<arr.size();n++) {
				JSONObject op = JSON.parseObject(arr.getString(n));
				String coTitle = op.getString("coTitle");
				Integer coScore = Integer.parseInt(op.getString("coScore"));
				String coId =  op.getString("coId");
				MOption option = new MOption();
				option.setCqId(cqId);
				option.setCoTitle(coTitle);
				option.setCoScore(coScore);
				
				if(coId == "null" || coId == null) {
					coId = BaseUtil.UUID();
					option.setCoId(coId);
					ios.addOption(option);
				}else {
					option.setCoId(coId);
					ios.updateOption(option);
				}
			}
			code = "0";
			data = "修改题目成功！";
		}else {
			data = data + "调用数据库出错！";
		}
		obj.put("code", code);
		obj.put("data", data);
		return JSON.toJSONString(obj);
	}

}
