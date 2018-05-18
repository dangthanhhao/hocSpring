package com.dangthanhhao.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String name;
    
    private String email;
    private String companyName;
    private String address;
    
    private String city;
    
    private String creditCardType;
    private String creditCardNumber;
    private String creditCardExpirationDate;
    
    
    
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userAccount")
    private Account account;

	public User() {
		super();
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCreditCardExpirationDate() {
		return creditCardExpirationDate;
	}

	public void setCreditCardExpirationDate(String creditCardExpirationDate) {
		this.creditCardExpirationDate = creditCardExpirationDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public User(Account account) {
		super();
		this.account = account;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", name=" + name + ", email=" + email + ", companyName=" + companyName
				+ ", address=" + address + ", city=" + city + ", creditCardType=" + creditCardType
				+ ", creditCardNumber=" + creditCardNumber + ", creditCardExpirationDate=" + creditCardExpirationDate
				+ ", account=" + account + "]";
	}


	
}