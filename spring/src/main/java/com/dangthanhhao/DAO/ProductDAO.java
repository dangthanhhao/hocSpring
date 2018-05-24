package com.dangthanhhao.DAO;

import java.util.ArrayList;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.dangthanhhao.entity.Product;

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
}
