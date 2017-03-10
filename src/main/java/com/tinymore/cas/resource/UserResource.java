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
import com.alibaba.fastjson.JSONObject;
import com.tinymore.cas.model.MUser;
import com.tinymore.cas.service.IEvaluationHistory;
import com.tinymore.cas.service.IUser;
import com.tinymore.cas.utils.Base64Util;
import com.tinymore.cas.utils.BaseUtil;

@Controller  
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserResource {

	@Autowired
	private IUser service;
	@Autowired
	private IEvaluationHistory ieh;
	
	@RequestMapping(value = "/login",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String userLogin(@RequestBody String params) {
		String code = "-1";
		String data = "";
		JSONObject ret = new JSONObject();
		JSONObject obj = JSON.parseObject(params);
		String account = obj.getString("account");
		String password = obj.getString("password");
		String type = obj.getString("type");
		MUser user = service.getUserByAccountAndPassword(account,Base64Util.encode(password));
		if(user == null) {
			data = "用户名或者密码错误！";
		}else {
			if(user.getCuType().equals(type)) {
				if(user.getCuStatus() == true) {
					code = "0";
					user.setCuPassword("");
					data = JSON.toJSONString(user);
				}else {
					data = "该用户已经被禁用！";
				}
			}else {
				data = "该用户类型不能够登录该系统！";
			}
		}
		ret.put("code", code);
		ret.put("data", data);
		return JSON.toJSONString(ret);
	}
	
	@RequestMapping(value = "/get",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String getBackPwd(@RequestBody String params) {
		String code = "-1";
		String data = "";
		JSONObject ret = new JSONObject();
		JSONObject obj = JSON.parseObject(params);
		String account = obj.getString("account");
		String email = obj.getString("email");
		String type = obj.getString("type");
		MUser user = service.selectByAccount(account);
		if(user == null) {
			data = "用户名或者电子邮箱错误！";
		}else {
			if(user.getCuEmail().equals(email)) {
				if(user.getCuType().equals(type)) {
					if(user.getCuStatus() == true) {
						code = "0";
						data = Base64Util.decode(user.getCuPassword());
					}else {
						data = "该用户已经被禁用！";
					}
				}else {
					data = "该用户类型不支持密码找回！";
				}
			}else {
				data = "用户名或者电子邮箱错误！";
			}
		}
		ret.put("code", code);
		ret.put("data", data);
		return JSON.toJSONString(ret);
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String userRegister(@RequestBody String params) {
		String code = "-1";
		String data = "注册用户失败，";
		JSONObject ret = new JSONObject();
		JSONObject obj = JSON.parseObject(params);
		String account = obj.getString("account");
		String password = obj.getString("password");
		String email = obj.getString("email");
		String type = obj.getString("type");
		String name = obj.getString("name");
		MUser user = service.selectByAccount(account);
		if(user == null) {
			user = new MUser();
			user.setCuId(BaseUtil.UUID());
			user.setCuAccount(account);
			user.setCuPassword(Base64Util.encode(password));
			user.setCuEmail(email);
			user.setCuStatus(true);
			user.setCuType(type);
			user.setCuRole(0);
			user.setCuName(name);
			int count = service.addUser(user);
			if(count > 0) {
				code = "0";
				data = "注册成功！";
			}else {
				data = "注册失败！";
			}
		}else {
			data = data + "该用户已经存在！";
		}
		ret.put("code", code);
		ret.put("data", data);
		return JSON.toJSONString(ret);
	}
	
	@RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MUser> getUserList(@RequestBody String params){
		return service.selectByParams(params);
	}
	
	@RequestMapping(value = "/detail",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public MUser getUser(@RequestBody String cuAccount){
		return service.selectByAccount(cuAccount);
	}

	@RequestMapping(value = "/delete",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public int delUser(@RequestBody String cuId){
		ieh.delEvaluationHistoryByCuId(cuId);
		return service.delUser(cuId);
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String updateUser(@RequestBody MUser user){
		String code = "-1";
		String data = "更新用户失败，";
		JSONObject ret = new JSONObject();
		String newPwd = user.getCuPassword();
		JSONObject pwd = JSON.parseObject(newPwd);
		if(pwd.getBooleanValue("reset")) {
			user.setCuPassword(Base64Util.encode(pwd.getString("password")));
		}else {
			user.setCuPassword(pwd.getString("password"));
		}
		int count = service.updateUser(user);
		if(count > 0) {
			code = "0";
			data = "更新用户成功！";
		}
		ret.put("code", code);
		ret.put("data", data);
		return JSON.toJSONString(ret);
	}
	
}
