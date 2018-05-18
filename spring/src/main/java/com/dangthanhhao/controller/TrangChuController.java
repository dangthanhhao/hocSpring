package com.dangthanhhao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.dangthanhhao.DAO.AccountDAO;
import com.dangthanhhao.entity.Account;
import com.dangthanhhao.entity.User;

@Controller

public class TrangChuController {
	@Autowired
	SessionFactory factory;
	@RequestMapping("/")
	public String HelloWord(Model model) {
		return "index";
	}

	@RequestMapping("/register")
	public String Product(Model model){
		Account a=new Account();
		User b=new User();
		model.addAttribute("newacc", a);
		a.setUsersName("HAo");
		b.setName("dangthanh");
		model.addAttribute("newuser", b);
		return "register";
	}
	@RequestMapping("/doregister")
	@Transactional
	public String doRegister(Account a,User b, Model model){
		System.out.println(b);
		System.out.println(a);
		b.setAccount(a);
		Session session=factory.getCurrentSession();
		session.save(a);
		session.save(b);
		
		return "index";
	}
	@RequestMapping("/productdetail")
	public String ProductDetail(){
		return "productdetail";
	}
	
	@Transactional
	@RequestMapping("/hiber")
	public String TestHibernate(ModelMap model){
		Session session=factory.getCurrentSession();
		Query query= session.createQuery("FROM Account");
		List<Account> list=query.getResultList();
		
		for (Account account : list) {
			System.out.println(account);
		}
		return "index";
	}
	

	
	@RequestMapping("/login")
	public String Login(HttpServletRequest request,@CookieValue(value = "login", defaultValue="none") String loginCookie, Model model){
		
		System.out.println(loginCookie);
		if (loginCookie.equals("none")) {
			return "login";
		}
		model.addAttribute("mess", "Bạn đã đăng nhập rồi");
		return "noti";
	}
	
	@RequestMapping("/dologin")
	@Transactional
	public String doLogin(HttpServletRequest request,HttpServletResponse response, @RequestParam("username") String userName, @RequestParam("pass") String password,Model model){
		AccountDAO account=new AccountDAO(factory.getCurrentSession());
		User user=account.checkLogin(userName, password);
		if(user==null) {
			model.addAttribute("mess", "Đăng nhập thất bại");
		}
		else {
		model.addAttribute("mess", "Chào mừng "+ user.getName()+ " đăng nhập thành công!");
		
		response.addCookie(new Cookie("login", user.getUserID().toString()));
		//System.out.println(WebUtils.getCookie(request, "login").getValue());
		}
		return "noti";
	}
	@RequestMapping("/deletecookies")
	public String Register(HttpServletResponse response, ModelMap model){
		response.addCookie(new Cookie("login", "none"));
		model.addAttribute("mess", "Xóa cookies thành công, bạn có thể đăng nhập!");
		return "noti";
	}
}
