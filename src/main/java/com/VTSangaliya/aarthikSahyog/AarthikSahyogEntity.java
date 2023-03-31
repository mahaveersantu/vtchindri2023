package com.VTSangaliya.aarthikSahyog;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="tbl_aarthik_sahyog")
@Entity
public class AarthikSahyogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	private String receiptNo;
	private LocalDate receiptDate;
	private int amount;
	@Transient
	private int announceId;
	@Transient
	private String smsName;
	private LocalDate addedOn;
	private char isActive;
	@ManyToOne
	@JoinColumn(name = "annId")
	@JsonIgnore
	private AarthikSahyogAnnouncementEntity aarthikSahyogAnnouncementEntity;


	public String getSmsName() {
		return smsName;
	}
	public void setSmsName(String smsName) {
		this.smsName = smsName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getAnnounceId() {
		return announceId;
	}
	public void setAnnounceId(int announceId) {
		this.announceId = announceId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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
	public AarthikSahyogAnnouncementEntity getAarthikSahyogAnnouncementEntity() {
		return aarthikSahyogAnnouncementEntity;
	}
	public void setAarthikSahyogAnnouncementEntity(AarthikSahyogAnnouncementEntity aarthikSahyogAnnouncementEntity) {
		this.aarthikSahyogAnnouncementEntity = aarthikSahyogAnnouncementEntity;
	}

}
