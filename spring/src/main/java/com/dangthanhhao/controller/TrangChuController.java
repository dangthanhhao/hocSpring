package com.dangthanhhao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.dangthanhhao.entity.HangHoa;

@Controller

public class TrangChuController {
	@Autowired
	SessionFactory factory;
	
	

	@RequestMapping("/")
	public String HelloWord(Model model) {
		ArrayList<String> listName=new ArrayList<String>();
//		listName.add("Hao");
//		listName.add("Nguyen");
//		listName.add("An");
		model.addAttribute("listName", listName);
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
	
	@Transactional
	@RequestMapping("/hiber")
	
	public String TestHibernate(ModelMap model){
		Session session=factory.getCurrentSession();
		Query query= session.createQuery("FROM HangHoa");
		List<HangHoa> list=query.list();
		model.addAttribute("list", list);
		return "index";
	}
}
