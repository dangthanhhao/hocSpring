package com.dangthanhhao.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.dangthanhhao.entity.Invoice;
import com.dangthanhhao.entity.Product;
import com.dangthanhhao.entity.User;

public class ProductDAO {
	Session session;

	public ProductDAO(Session session) {
		super();
		this.session = session;
	}
	public ArrayList<Product> getAll(int paginationIndex, String search){
		String hql="From Product";
		if(!search.equals("none")) hql+=" where productName like '%"+search+"%'";
		Query query=session.createQuery(hql);
		query.setMaxResults(3);
		query.setFirstResult((paginationIndex-1)*3);
		return (ArrayList<Product>) query.list();
	}
	public Product getProductById(int productID) {
		String hql="From Product where productId="+productID;
		Query query=session.createQuery(hql);
		return (Product) query.uniqueResult();
	}
	public List<Product> getAlbumCart(int paginationIndex, User u){
		List<Invoice> li=u.getListInvoice();
		ArrayList<Product> kq=new ArrayList<Product>();
		for (Invoice invoice : li) {
			kq.add(invoice.getProduct());
		}
		ArrayList<Product> kq2=new ArrayList<Product>();
		int begin=(paginationIndex-1)*3;
		int end=li.size()-1;
		if (end-2>=begin) end=begin+2;
		for (; begin<=end;begin++) {
			kq2.add(kq.get(begin));
		}
		return kq2;
	}
}
