package com.light.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

@Controller
public class LoginController {
	
	@Autowired
	private SecurityManager sm;

	@RequestMapping("login")
	public String login(String userName, String password,HttpServletRequest request) {
		// 首次登录
		if ("admin".equals(userName) && "admin".equals(password)) {
			
			
			SecurityUtils.setSecurityManager(sm);
			Subject subject = SecurityUtils.getSubject();
			// 使用 shiro 的 session 保存数据
			Session session = subject.getSession();
			session.setAttribute("userName", userName);
			
			return "manageUI";
		}
		
		// 如果已经登录过，从另一个 tomcat 访问该方法，跳转到 manageUI 页面可以查看 session 信息
		if ("".equals(userName) && "".equals(password)) {
			return "manageUI";
		}
		
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
	
		session.removeAttribute("userName");
		session.removeAttribute("url");
		
		return "redirect:/index.jsp";
	}
	
}
