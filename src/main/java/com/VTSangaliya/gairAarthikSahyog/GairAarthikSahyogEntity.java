package com.VTSangaliya.gairAarthikSahyog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_gair_aarthik_sahyog")
public class GairAarthikSahyogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Transient
	private int srNo;
	private String name;
	private String address;
	private String mobile;
	private String sahyogDetail;
	private int approxCost;
	private char isActive;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSahyogDetail() {
		return sahyogDetail;
	}
	public void setSahyogDetail(String sahyogDetail) {
		this.sahyogDetail = sahyogDetail;
	}
	
	public int getApproxCost() {
		return approxCost;
	}
	public void setApproxCost(int approxCost) {
		this.approxCost = approxCost;
	}
	public char getIsActive() {
		return isActive;
	}
	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
	
	
	
}
