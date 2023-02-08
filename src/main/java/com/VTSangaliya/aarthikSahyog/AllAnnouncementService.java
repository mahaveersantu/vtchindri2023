package com.VTSangaliya.aarthikSahyog;

import java.time.LocalDate;

import javax.persistence.Transient;

import org.springframework.stereotype.Service;

@Service
public class AllAnnouncementService {
private int annId;
	
	private String name;
	private String address;
	
	@Transient
	private int grandTotal;
	private String mobile;
	private int announceAmount;
	private LocalDate addedOn;
	private char isActive;
     private int id;
	
	private String receiptNo;
	private LocalDate receiptDate;
	private int amount;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(int grandTotal) {
		this.grandTotal = grandTotal;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getAnnounceAmount() {
		return announceAmount;
	}
	public void setAnnounceAmount(int announceAmount) {
		this.announceAmount = announceAmount;
	}
	public LocalDate getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(LocalDate addedOn) {
		this.addedOn = addedOn;
	}
	public char getIsActive() {
		return isActive;
	}
	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public LocalDate getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(LocalDate receiptDate) {
		this.receiptDate = receiptDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
