package com.dangthanhhao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product implements Serializable {

    @Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", productPrice=" + productPrice + ", productURL=" + productURL + ", imageURL="
				+ imageURL + ", listSong=" + listSong + "]";
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;    
    private String productName;
    private String productDescription;
    private double productPrice;
    private String productURL;
    private String	imageURL;
    @OneToMany(mappedBy="product",fetch=FetchType.EAGER)
    private List<SongExample> listSong;
    
	public List<SongExample> getListSong() {
		return listSong;
	}
	public void setListSong(ArrayList<SongExample> listSong) {
		this.listSong = listSong;
	}
	public Product(Long productId, String productName, String productDescription, double productPrice,
			String productURL, String imageURL) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productURL = productURL;
		this.imageURL = imageURL;
	}
	public Product() {
		super();
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductURL() {
		return productURL;
	}
	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
    
}