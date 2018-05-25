package com.dangthanhhao.DAO;

import java.util.List;

import org.hibernate.Session;

import com.dangthanhhao.entity.Invoice;
import com.dangthanhhao.entity.Product;
import com.dangthanhhao.entity.User;

public class InvoiceDAO {
	Session session;
	public InvoiceDAO(Session session) {
		super();
		this.session = session;
	}
	public boolean addinvoice(User u, Product p,Session session) {
		List<Invoice> list=u.getListInvoice();
		for (Invoice invoice : list) {
			if(invoice.getProduct().getProductId()==p.getProductId()) {
				return false;
			}
		}
		Invoice i=new Invoice();
		i.setProduct(p);
		i.setUser(u);
		session.save(i);
		return true;
	}
	public boolean checkInvoice(User u, Product p, Session session) {
		List<Invoice> list=u.getListInvoice();
		for (Invoice invoice : list) {
			if(invoice.getProduct().getProductId()==p.getProductId()) {
				return true;
			}
		}
		return false;
	}
}
