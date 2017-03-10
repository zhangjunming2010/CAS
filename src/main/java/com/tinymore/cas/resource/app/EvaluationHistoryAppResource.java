package com.tinymore.cas.resource.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tinymore.cas.model.MEvaluation;
import com.tinymore.cas.model.MEvaluationAssess;
import com.tinymore.cas.model.MEvaluationHistory;
import com.tinymore.cas.service.IEvaluation;
import com.tinymore.cas.service.IEvaluationAssess;
import com.tinymore.cas.service.IEvaluationHistory;
import com.tinymore.cas.utils.BaseUtil;

@Controller  
@RequestMapping("/app/evaluationhistory")
@CrossOrigin(origins="*")
public class EvaluationHistoryAppResource {
	
	@Autowired
	private IEvaluationHistory service;
	@Autowired
	private IEvaluation  ie;
	@Autowired
	private IEvaluationAssess iea;
	
	@RequestMapping(value = "/add",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String addEvaluationHistory(HttpServletRequest params) {
		String code = "-1";
		String data = "提交测评失败，";
		JSONObject ret = new JSONObject();
		String cuId = params.getParameter("cuId");
		String ceId =  params.getParameter("ceId");
		String options =  params.getParameter("options");
		MEvaluation evaluation = ie.getEvaluationByPrimaryKey(ceId);
		MEvaluationHistory history = new MEvaluationHistory();
		history.setCehId(BaseUtil.UUID());
		history.setCuId(cuId);
		history.setCehTitle(evaluation.getCeTitle());
		history.setCehStatus(true);
		history.setCehDate(BaseUtil.SysteTime());
		history.setCehOptions(options);
		//需要计算得分
		int score = 0;
		JSONArray arr = JSON.parseArray(options);
		for (int i = 0; i < arr.size(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			score = score + Integer.parseInt(obj.get("coScore").toString());
		}
		history.setCehScore(score);
		//需要获取对应测评记录关联的评鉴信息进行区间比对
		List<MEvaluationAssess> lists = iea.getEvaluationAssessByCeId(ceId);
		String desc = "";
		int min = 0;
		int max = 0;
		for(MEvaluationAssess assess : lists) {
			min = assess.getCeaMin();
			max = assess.getCeaMax();
			if(score >= min && score<=max) {
				desc = assess.getCeaDesc();
				break;
			}
		}
		history.setCehDesc(desc);
		int count = service.addEvaluationHistory(history);
		if(count > 0) {
			code = "0";
			data = "总分："+score+"。<br>";
			data = data + "区间分数："+min+"-"+max+"。"+desc;
		}else {
			data = data + "写入记录失败！";
		}
		ret.put("code", code);
		ret.put("data", data);
		return JSON.toJSONString(ret);
	}
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MEvaluationHistory> getEvaluationHistoryList(HttpServletRequest params){
		String cuId = params.getParameter("cuId");
		List<MEvaluationHistory> arr = service.getEvaluationHistoryByCuId(cuId);
		return arr;
	}
	
	@RequestMapping(value = "/get",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public MEvaluationHistory getEvaluationHistoryByCehId(HttpServletRequest params){
		String cehId = params.getParameter("cehId");
		return service.getEvaluationHistoryByPrimaryKey(cehId);
	}
}
