package com.dangthanhhao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Nationalized;

@Entity
public class HangHoa {
@Id
@Column(name="ID")
private Integer iD;
@Column(name = "ten", columnDefinition = "NVARCHAR")
private String ten;
private int maTheLoai;
public Integer getiD() {
	return iD;
}
public void setiD(Integer iD) {
	this.iD = iD;
}
public String getTen() {
	return ten;
}
public void setTen(String ten) {
	this.ten = ten;
}
public int getMaTheLoai() {
	return maTheLoai;
}
public void setMaTheLoai(int maTheLoai) {
	this.maTheLoai = maTheLoai;
}

}
