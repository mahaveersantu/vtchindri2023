package com.VTSangaliya.aarthikSahyog;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="tbl_aarthik_sahyog_announcement")
@Entity
public class AarthikSahyogAnnouncementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int annId;
	
	private String name;
	private String address;
	@Transient
	private int srNo;
	@Transient
	private String smsName;
	@Transient
	private Integer grandTotal;
	@Transient
	private int pendingAmount;
	private String mobile;
	private int announceAmount;
	private LocalDate addedOn;
	private char isActive;
	private byte[] photo;
	
	//@JsonIgnore
	@OneToMany(mappedBy = "aarthikSahyogAnnouncementEntity",cascade = CascadeType.ALL)
	//@JsonIgnore
	private List<AarthikSahyogEntity> aarthikSahyogEntity;
	
	
	
	public String getSmsName() {
		return smsName;
	}
	public void setSmsName(String smsName) {
		this.smsName = smsName;
	}
	public int getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(int pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	public List<AarthikSahyogEntity> getAarthikSahyogEntity() {
		return aarthikSahyogEntity;
	}
	public void setAarthikSahyogEntity(List<AarthikSahyogEntity> aarthikSahyogEntity) {
		this.aarthikSahyogEntity = aarthikSahyogEntity;
	}
	
	
	public Integer getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(Integer grandTotal) {
		this.grandTotal = grandTotal;
	}
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	
	
	public int getAnnId() {
		return annId;
	}
	public void setAnnId(int annId) {
		this.annId = annId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAnnounceAmount() {
		return announceAmount;
	}
	public void setAnnounceAmount(int announceAmount) {
		this.announceAmount = announceAmount;
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
	public char getIsActive() {
		return isActive;
	}
	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
	public LocalDate getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(LocalDate addedOn) {
		this.addedOn = addedOn;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	
}
