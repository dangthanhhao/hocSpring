package com.dangthanhhao.DAO;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangthanhhao.entity.Account;
import com.dangthanhhao.entity.User;

public class AccountDAO {
	
	Session session;	public AccountDAO(Session session) {
		super();
		this.session = session;
	}
	public User checkLogin(String user, String pass) {
		Query query=session.createQuery("From Account where usersName=:user and password=:pass");
		query.setParameter("user", user);
		query.setParameter("pass", pass);
		Account acc=(Account) query.uniqueResult();
		if(acc==null) return null;
		else return acc.getUser();
	}
}
