package com.dangthanhhao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

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
import com.dangthanhhao.DAO.ProductDAO;
import com.dangthanhhao.DAO.SongDAO;
import com.dangthanhhao.entity.Account;
import com.dangthanhhao.entity.User;
import com.dangthanhhao.entity.Product;
import com.dangthanhhao.entity.SongExample;

@Controller

public class TrangChuController {
	@Autowired
	SessionFactory factory;
	// show homepage
	@RequestMapping("/")
	public String HelloWord(Model model) {
		return "index";
	}
	//account
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
	
	//product visit

	@Transactional
	@RequestMapping("/products")
	public String listProduct(Model model, @RequestParam(value="page",defaultValue="1") int page,@RequestParam(value="search",defaultValue="none") String search ) {
		System.out.println(search);
		Session session=factory.getCurrentSession();
		ProductDAO pd=new ProductDAO(session);
		ArrayList<Product> listProduct =pd.getAll(page,search);
		model.addAttribute("search", search);
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("pageindex", page);
		return "products";
	}
	@Transactional
	@RequestMapping("/productdetail")
	public String showDetail(@RequestParam(value="id",defaultValue="1") int id,@RequestParam(value="songname",defaultValue="none") String songName, Model model){
		Session session=factory.getCurrentSession();
		ProductDAO pd=new ProductDAO(session);
		Product p=pd.getProductById(id);
		List<SongExample> listSong= p.getListSong();
		SongExample songPlay=null;
		SongExample songCanPlay=null;
		System.out.println(songName);
		for (SongExample songExample : listSong) {
			if(songExample.getIsExample()==1) {
				songCanPlay=songExample;
				if(songExample.getExampleSongName().equals(songName)) {
				songPlay=songExample;
				
				break;
				}
			}
		}
		if(songPlay==null) songPlay=songCanPlay;
		System.out.println(songPlay.getExampleSongName());
		model.addAttribute("productId", id);
		model.addAttribute("product", p);
		model.addAttribute("songPlay", songPlay);
		model.addAttribute("listSong", listSong);
		return "productdetail";
	}
	@Transactional
	@RequestMapping("/hiber")
	public String TestHibernate(ModelMap model){
		Session session=factory.getCurrentSession();
		
//		ProductDAO pd=new ProductDAO(session);
//		ArrayList<Product> listProduct =pd.getAll(1);
//		for (Product product : listProduct) {
//			System.out.println(product);
//		}
		return "index";
	}
	

	
	
}
