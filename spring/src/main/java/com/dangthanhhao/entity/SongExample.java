package com.dangthanhhao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SongExample implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="ProductID")
	private Product product;
	@Id
	private String exampleSongName;
	private String exampleSongURL;
	private int isExample;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getExampleSongName() {
		return exampleSongName;
	}
	public void setExampleSongName(String exampleSongName) {
		this.exampleSongName = exampleSongName;
	}
	public String getExampleSongURL() {
		return exampleSongURL;
	}
	public void setExampleSongURL(String exampleSongURL) {
		this.exampleSongURL = exampleSongURL;
	}
	public int getIsExample() {
		return isExample;
	}
	public void setIsExample(int isExample) {
		this.isExample = isExample;
	}
	public SongExample() {
		super();
	}
	public SongExample(Product product, String exampleSongName, String exampleSongURL, int isExample) {
		super();
		this.product = product;
		this.exampleSongName = exampleSongName;
		this.exampleSongURL = exampleSongURL;
		this.isExample = isExample;
	}
	
}
