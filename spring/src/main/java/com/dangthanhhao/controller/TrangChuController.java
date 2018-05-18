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
import com.dangthanhhao.entity.User;

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
	
	public String Login() {
		return "login";
	}
	@RequestMapping("/product")
	public String Product(){
		return "products";
	}
	@RequestMapping("/productdetail")
	public String ProductDetail(){
		return "productdetail";
	}
	
	@Transactional
	@RequestMapping("/hiber")
	public String TestHibernate(ModelMap model){
		Session session=factory.getCurrentSession();
		Query query= session.createQuery("FROM User");
		List<User> list=query.getResultList();
		model.addAttribute("list", list);
		
		return "index";
	}
}
