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
import com.dangthanhhao.DAO.InvoiceDAO;
import com.dangthanhhao.DAO.ProductDAO;
import com.dangthanhhao.DAO.SongDAO;
import com.dangthanhhao.entity.Account;
import com.dangthanhhao.entity.Invoice;
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
	
	@RequestMapping("/support")
	public String support(ModelMap model){
		model.addAttribute("mess", "Mọi ý kiến đóng góp vui lòng liên hệ admin số đt 0123456789. Email: aszqsc@gmail.com");
		return "noti";	
	}
	@RequestMapping("/registeremail")
	public String regEmail(ModelMap model,@CookieValue(value = "login", defaultValue="none") String loginCookie){
		if(loginCookie.equals("none")) {
			model.addAttribute("mess", "Bạn chưa đăng nhập! Vui lòng đăng nhập để đăng kí nhận email từ chúng tôi!");
			return "noti";
		}
			return "registeremail";
	}
	
	@RequestMapping("/doregemail")
	@Transactional
	public String doregemail(ModelMap model,@CookieValue(value = "login", defaultValue="none") String loginCookie, @RequestParam(value="check",defaultValue="none") String check){
		if(loginCookie.equals("none")) {
			model.addAttribute("mess", "Bạn chưa đăng nhập! Vui lòng đăng nhập để đăng kí nhận email từ chúng tôi!");
			return "noti";
		}
		if(!check.equals("ok")) {
			model.addAttribute("mess", "Cảm ơn bạn đã sử dụng web");
			return "noti";
		}
			AccountDAO ad=new AccountDAO(factory.getCurrentSession());
			User u=ad.getUserByID(new Integer(loginCookie));


			if (u!=null) {
				if(u.getSubscribes()!=null) {
				if(u.getSubscribes()==1) {

					model.addAttribute("mess", "Bạn đã đăng kí nhận email quảng cáo rồi!");
					return "noti";
				}
				}
			}
			
		u.setSubscribes(new Integer(1));
		System.out.println("a");
		Session session=factory.getCurrentSession();
		session.saveOrUpdate(u);
		System.out.println("b");
		model.addAttribute("mess", "Cảm ơn bạn đã đăng kí. Chúng tôi sẽ mang đến cho bạn những sản phẩm chất lượng nhất!");
		return "noti";
			
	}
	
	@Transactional
	@RequestMapping("/addtocart")
	public String addtocart(ModelMap model,@CookieValue(value = "login", defaultValue="none") String loginCookie, @RequestParam(value="id",defaultValue="-1") String productid){
		if(loginCookie.equals("none")) {
			model.addAttribute("mess", "Bạn chưa đăng nhập! Vui lòng đăng nhập để mua hàng!");
			return "noti";
		}
		if(productid.equals("none")) {
			model.addAttribute("mess", "Lỗi sản phẩm");
			return "noti";
		}
		Session session=factory.getCurrentSession();
		AccountDAO ad=new AccountDAO(session);
		User u= ad.getUserByID(new Integer(loginCookie));
		ProductDAO pd=new ProductDAO(session);
		Product p=pd.getProductById(new Integer(productid));
		InvoiceDAO idao=new InvoiceDAO(session);
		if(idao.addinvoice(u, p, session)) {
			model.addAttribute("mess", "Mua album "+p.getProductName() +" với giá "+p.getProductPrice() + "thành công! Bạn có thể download album!");
			return "noti";
		}
		else {
			model.addAttribute("mess", "Album này mua rồi hoặc đã xảy ra lỗi trong quá trình giao dịch");
			return "noti";
		}
		
	}
	
	@Transactional
	@RequestMapping("/download")
	public String Download(ModelMap model,@CookieValue(value = "login", defaultValue="none") String loginCookie, @RequestParam(value="id",defaultValue="-1") String productid){
		Session session=factory.getCurrentSession();
		if(loginCookie.equals("none")) {
			model.addAttribute("mess", "Bạn chưa đăng nhập! Vui lòng đăng nhập để download album!");
			return "noti";
		}
		AccountDAO ad=new AccountDAO(session);
		User u= ad.getUserByID(new Integer(loginCookie));
		ProductDAO pd=new ProductDAO(session);
		Product p=pd.getProductById(new Integer(productid));
		InvoiceDAO idao=new InvoiceDAO(session);
		if (idao.checkInvoice(u, p, session)) {
			model.addAttribute("mess", "Cảm ơn đã mua sản phẩm, bạn có thể tải xuống ở link dưới!");
			model.addAttribute("product", p);
			return "download";
		}
		else {
			model.addAttribute("mess", "Bạn cần mua album để có thể tải xuống!");
			return "noti";
		}
	}
	@Transactional
	@RequestMapping("/mycart")
	public String TestHibernate(ModelMap model,@RequestParam(value="page",defaultValue="1") int page,@CookieValue(value = "login", defaultValue="none") String loginCookie){
		Session session=factory.getCurrentSession();
		AccountDAO ad=new AccountDAO(session);
		User u= ad.getUserByID(new Integer(loginCookie));
		ProductDAO pd=new ProductDAO(session);
		List<Product> listProduct =pd.getAlbumCart(page,u);
		
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("pageindex", page);
		return "products";
		
	}
	

	
	
}
