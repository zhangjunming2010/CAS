package com.tinymore.cas.resource.app;

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
import com.alibaba.fastjson.JSONObject;
import com.tinymore.cas.model.MUser;
import com.tinymore.cas.service.IUser;
import com.tinymore.cas.utils.Base64Util;
import com.tinymore.cas.utils.BaseUtil;

@Controller  
@RequestMapping("/app/user")
@CrossOrigin(origins="*")
public class UserAppResource {

	private static final Logger log = (Logger) LogManager.getLogger(UserAppResource.class);

	@Autowired
	private IUser service;
	
	@RequestMapping(value = "/login",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String userLogin(HttpServletRequest params) {
		String code = "-1";
		String data = "";
		JSONObject ret = new JSONObject();
		String account = params.getParameter("account");
		String password = params.getParameter("password");
		String type = params.getParameter("type");
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
		log.info(JSON.toJSONString(ret));
		return JSON.toJSONString(ret);
	}
	
	@RequestMapping(value = "/get",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String getBackPwd(HttpServletRequest params) {
		String code = "-1";
		String data = "";
		JSONObject ret = new JSONObject();
		String account = params.getParameter("account");
		String email =  params.getParameter("email");
		String type =  params.getParameter("type");
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
		log.info(JSON.toJSONString(ret));
		return JSON.toJSONString(ret);
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String userRegister(HttpServletRequest params) {
		String code = "-1";
		String data = "注册用户失败，";
		JSONObject ret = new JSONObject();
		String account = params.getParameter("account");
		String password =  params.getParameter("password");
		String email =  params.getParameter("email");
		String type =  params.getParameter("type");
		String name =  params.getParameter("name");
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
			data = data + "该账号已经存在！";
		}
		ret.put("code", code);
		ret.put("data", data);
		log.info(JSON.toJSONString(ret));
		return JSON.toJSONString(ret);
	}

}
