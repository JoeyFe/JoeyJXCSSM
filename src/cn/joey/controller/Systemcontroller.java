package cn.joey.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.joey.entity.User;
import cn.joey.service.UserService;

/**
 * 系统操作类控制器
 * @author Joey_Fe
 *
 */
@Controller
@RequestMapping("/system")
public class Systemcontroller {
 
	@Autowired
	private UserService userService;

/**
 * 登录后主页
 * @param model
 * @return
 */
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("system/index");
		return model;
 }
/**
 * 系统登陆后欢迎界面	
 * @param model
 * @return
 */
	@RequestMapping(value = "/welcome",method = RequestMethod.GET)
	public ModelAndView welcome(ModelAndView model) {
		model.setViewName("system/welcome");
		return model;
 }
/**
 * 登录界面	
 * @param model
 * @return
 */
	@RequestMapping(value = "/login",method =RequestMethod.GET )
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");
		return model;
	}
/**
 * 登录表单提交控制器
 * @param user request
 * @return
 */
	@RequestMapping(value = "/login",method =RequestMethod.POST )
	@ResponseBody
	public Map<String, String> loginAct(User user,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if (user==null) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户信息");
			return ret;
		}
		if (StringUtils.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户信息");
			return ret;
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户信息");
			return ret;
		}
		User findByUsername = userService.findByUsername(user.getUsername());
		if(findByUsername == null)
		{
			ret.put("type", "error");
			ret.put("msg", "不存在");
			return ret;
		}
		if(!user.getPassword().equals(findByUsername.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "密码错误");
			return ret;
		}
		request.getSession().setAttribute("admin",findByUsername);
		ret.put("type", "success");
		ret.put("msg", "登陆成功");
		return ret;
	}
}
