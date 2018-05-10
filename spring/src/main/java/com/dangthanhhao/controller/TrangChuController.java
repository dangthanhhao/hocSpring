package com.dangthanhhao.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("login")
public class TrangChuController {
@RequestMapping("/")

	public String HelloWord() {
		return "index";
	}

	@RequestMapping(method=RequestMethod.GET,value="/login")
	@ResponseBody
	public String Login(HttpServletRequest request) {
		Object d= request.getSession().getAttribute("login");
		if (d==null) return " null sestion";
		if((Boolean)d==true) return "U arre logined";
		else return "u arre not login";
	}
	@RequestMapping(method=RequestMethod.POST, value="/login")
	public String postmethod(String name, Model mode, HttpServletRequest request) {
		request.getSession().setAttribute("login", true);	
		System.out.println("This is post");
		mode.addAttribute("name",name);
		
		return "reg";
	}
	
}
