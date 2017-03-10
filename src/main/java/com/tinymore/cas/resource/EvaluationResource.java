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
import com.tinymore.cas.model.MEvaluation;
import com.tinymore.cas.model.MEvaluationAssess;
import com.tinymore.cas.model.MEvaluationRelationKey;
import com.tinymore.cas.service.IEvaluation;
import com.tinymore.cas.service.IEvaluationAssess;
import com.tinymore.cas.service.IEvaluationRelation;
import com.tinymore.cas.utils.BaseUtil;

@Controller  
@RequestMapping("/evaluation")
@CrossOrigin(origins="*")
public class EvaluationResource {
	
	@Autowired
	private IEvaluation service;
	@Autowired
	private IEvaluationRelation ier;
	@Autowired
	private IEvaluationAssess iea;
	
	@RequestMapping(value = "/add",method = RequestMethod.POST,produces="application/json; charset=utf-8")
    @ResponseBody
	public String addEvaluation(@RequestBody String params) {
		String code = "-1";
		JSONObject ret = new JSONObject();
		JSONObject obj = JSON.parseObject(params);
		String ceTitle = obj.getString("ceTitle");
		String ceDes = obj.getString("ceDes");
		String options = obj.getString("options");
		MEvaluation eval = service.getEvaluationByTitle(ceTitle);
		if(eval == null) {
			String ceId = BaseUtil.UUID();
			eval = new MEvaluation();
			eval.setCeId(ceId);
			eval.setCeTitle(ceTitle);
			eval.setCeDes(ceDes);
			eval.setCeStatus(false);
			service.addEvaluation(eval);
			JSONArray arry = JSON.parseArray(options);
			for (Object object : arry) {
				JSONObject option = JSON.parseObject(object.toString());
				MEvaluationAssess assess = new MEvaluationAssess();
				assess.setCeId(ceId);
				assess.setCeaId(BaseUtil.UUID());
				assess.setCeaMin(option.getInteger("min"));
				assess.setCeaMax(option.getInteger("max"));
				assess.setCeaDesc(option.getString("content"));
				iea.addEvaluationAssess(assess);
			}
			code = "0";
		}
		ret.put("code", code);
		return JSON.toJSONString(ret);
	}
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MEvaluation> getEvaluationList(@RequestBody String params){
		JSONObject obj = JSON.parseObject(params);
		String ceTitle = obj.getString("ceTitle");
		int ceStatus = obj.getInteger("ceStatus");
		return service.getEvaluationByParams(ceTitle, ceStatus);
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String delEvaluation(@RequestBody MEvaluation record) {
		String code = "-1";
		JSONObject obj = new JSONObject();
		MEvaluationRelationKey mer = new MEvaluationRelationKey();
		mer.setCeId(record.getCeId());
		ier.delRealation(mer);
		iea.delEvaluationAssessByCeId(record.getCeId());
		int count = service.delEvaluation(record.getCeId());
		if(count > 0) {
			code = "0";
		}
		obj.put("code", code);
		return JSON.toJSONString(obj);
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces="application/json; charset=utf-8")
    @ResponseBody
	public String updateEvaluation(@RequestBody String params) {
		String code = "-1";
		JSONObject ret = new JSONObject();
		JSONObject obj = JSON.parseObject(params);
		String ceId = obj.getString("ceId");
		Boolean ceStatus = obj.getBoolean("ceStatus");
		String ceTitle = obj.getString("ceTitle");
		String ceDes = obj.getString("ceDes");
		MEvaluation eval = service.getEvaluationByTitle(ceTitle);
		int count = 0;
		if(eval == null) {
			eval = new MEvaluation();
			eval.setCeId(ceId);
			eval.setCeTitle(ceTitle);
			eval.setCeDes(ceDes);
			eval.setCeStatus(ceStatus);
			count = service.updateEvaluation(eval);
			if(count > 0) {
				String delCeaIds = obj.getString("delCeaIds");
				if(delCeaIds != null) {
					JSONArray delCeaIdArr = JSON.parseArray(delCeaIds);
					for(int i = 0;i<delCeaIdArr.size();i++) {
						iea.delEvaluationAssess(delCeaIdArr.getString(i));
					}
				}
				String options = obj.getString("options");
				if(options != null) {
					JSONArray arr = JSON.parseArray(options);
					for(int n=0;n<arr.size();n++) {
						JSONObject pj = JSON.parseObject(arr.getString(n));
						String ceaId = pj.getString("ceaId");
						Integer min = Integer.parseInt(pj.getString("min"));
						Integer max = Integer.parseInt(pj.getString("max"));
						String content = pj.getString("content");
						MEvaluationAssess assess = new MEvaluationAssess();
						assess.setCeaMin(min);
						assess.setCeaMax(max);
						assess.setCeId(ceId);
						assess.setCeaDesc(content);
						if(ceaId == "null" || ceaId == null) {
							ceaId = BaseUtil.UUID();
							assess.setCeaId(ceaId);
							iea.addEvaluationAssess(assess);
						}else {
							assess.setCeaId(ceaId);
							iea.updateEvaluationAssess(assess);
						}
					}
				}
				code = "0";
			}
		}else {
			if(eval.getCeId().equals(ceId)) {
				eval.setCeId(ceId);
				eval.setCeTitle(ceTitle);
				eval.setCeDes(ceDes);
				eval.setCeStatus(ceStatus);
				count = service.updateEvaluation(eval);
				if(count > 0) {
					String delCeaIds = obj.getString("delCeaIds");
					if(delCeaIds != null) {
						JSONArray delCeaIdArr = JSON.parseArray(delCeaIds);
						for(int i = 0;i<delCeaIdArr.size();i++) {
							iea.delEvaluationAssess(delCeaIdArr.getString(i));
						}
					}
					String options = obj.getString("options");
					if(options != null) {
						JSONArray arr = JSON.parseArray(options);
						for(int n=0;n<arr.size();n++) {
							JSONObject pj = JSON.parseObject(arr.getString(n));
							String ceaId = pj.getString("ceaId");
							Integer min = Integer.parseInt(pj.getString("min"));
							Integer max = Integer.parseInt(pj.getString("max"));
							String content = pj.getString("content");
							MEvaluationAssess assess = new MEvaluationAssess();
							assess.setCeaMin(min);
							assess.setCeaMax(max);
							assess.setCeId(ceId);
							assess.setCeaDesc(content);
							if(ceaId == "null" || ceaId == null) {
								ceaId = BaseUtil.UUID();
								assess.setCeaId(ceaId);
								iea.addEvaluationAssess(assess);
							}else {
								assess.setCeaId(ceaId);
								iea.updateEvaluationAssess(assess);
							}
						}
					}
					code = "0";
				}
			}
		}
		ret.put("code", code);
		return JSON.toJSONString(ret);
	}

}
