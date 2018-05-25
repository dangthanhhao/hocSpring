package com.dangthanhhao.entity;


import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dangthanhhao.entity.User;
@Entity
public class Invoice implements Serializable {
    
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceID;
    
    
    @ManyToOne
    @JoinColumn(name="UserID")
    private User user;

    @ManyToOne
    @JoinColumn(name="ProductID")
    private Product product;

	public Long getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(Long invoiceID) {
		this.invoiceID = invoiceID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

   
}