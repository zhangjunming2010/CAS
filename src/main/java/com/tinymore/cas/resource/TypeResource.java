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
import com.tinymore.cas.model.MQuestion;
import com.tinymore.cas.model.MType;
import com.tinymore.cas.service.IOption;
import com.tinymore.cas.service.IQuestion;
import com.tinymore.cas.service.IType;
import com.tinymore.cas.utils.BaseUtil;

@Controller  
@RequestMapping("/type")
@CrossOrigin(origins="*")
public class TypeResource {
	
	private static final Logger log = (Logger) LogManager.getLogger(TypeResource.class);
	
	@Autowired
	private IType service;
	@Autowired
	private IQuestion iq;
	@Autowired
	private IOption io;

	@RequestMapping(value = "/add",method = RequestMethod.POST,produces="application/json; charset=utf-8")
    @ResponseBody
	public String addType(@RequestBody String ctName) {
		String code = "-1";
		JSONObject obj = new JSONObject();
		MType Type = service.getTypeByName(ctName);
		if(Type == null) {
			Type = new MType();
			Type.setCtId(BaseUtil.UUID());
			Type.setCtName(ctName);
			int count = service.addType(Type);
			if(count > 0) {
				code = "0";
				log.info("新增类型："+ctName+"成功！");
			}else {
				log.info("新增类型："+ctName+"失败，该类型已存在！");
			}
		}
		obj.put("code", code);
		return JSON.toJSONString(obj);
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String updateType(@RequestBody MType record){
		String code = "-1";
		JSONObject obj = new JSONObject();
		MType Type = service.getTypeByName(record.getCtName());
		if(Type == null) {
			int count = service.updateType(record);
			if(count > 0) {
				code = "0";
				log.info("更新类型："+record.getCtName()+"成功！");
			}else {
				log.info("更新类型："+record.getCtName()+"失败，该类型已存在！");
			}
		}
		obj.put("code", code);
		return JSON.toJSONString(obj);
	}
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MType> getTypeList(@RequestBody String searchKey){
		List<MType> types = service.getTypeListByParams(searchKey);
		log.info(JSON.toJSON(types));
		return types;
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String delType(@RequestBody MType record) {
		String code = "-1";
		JSONObject obj = new JSONObject();
		List<MQuestion> questions = iq.getQuestionListByParams("ALL","ALL",record.getCtId());
		for(MQuestion question : questions) {
			io.delOptionByCqId(question.getCqId());
			iq.delQuestion(question.getCqId());
		}
		int count = service.delType(record.getCtId());
		if(count > 0) {
			code = "0";
			log.info("删除类型："+record.getCtName()+"成功！");
		}
		obj.put("code", code);
		return JSON.toJSONString(obj);
	}
}
