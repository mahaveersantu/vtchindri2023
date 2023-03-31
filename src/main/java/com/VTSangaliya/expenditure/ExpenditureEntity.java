package com.VTSangaliya.expenditure;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_expenditure")
public class ExpenditureEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expdId;
	@Transient
	private int srNo;
	private int expdReceiptNo;
	private int expdAmount;
	private String receiverName;
	private LocalDate expdDate;
	@Transient
	private String expdStringDate;
	private String expdDetail;
	private String expdTitle;
	private char isActive;
	private LocalDate addedOn;
	private LocalDate updatedOn;
	private byte[] receiptPdf;
	@ManyToOne()
    @JoinColumn(name = "cat_id")
	private ExpenditureCatEntity expenditureCatEntity;

	public int getExpdId() {
		return expdId;
	}

	public void setExpdId(int expdId) {
		this.expdId = expdId;
	}




	public byte[] getReceiptPdf() {
		return receiptPdf;
	}

	public void setReceiptPdf(byte[] receiptPdf) {
		this.receiptPdf = receiptPdf;
	}

	public String getExpdStringDate() {
		return expdStringDate;
	}

	public void setExpdStringDate(String expdStringDate) {
		this.expdStringDate = expdStringDate;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public LocalDate getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}

	public LocalDate getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(LocalDate addedOn) {
		this.addedOn = addedOn;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public int getExpdReceiptNo() {
		return expdReceiptNo;
	}

	public void setExpdReceiptNo(int expdReceiptNo) {
		this.expdReceiptNo = expdReceiptNo;
	}

	public int getExpdAmount() {
		return expdAmount;
	}

	public void setExpdAmount(int expdAmount) {
		this.expdAmount = expdAmount;
	}

	public LocalDate getExpdDate() {
		return expdDate;
	}

	public void setExpdDate(LocalDate expdDate) {
		this.expdDate = expdDate;
	}

	public String getExpdDetail() {
		return expdDetail;
	}

	public void setExpdDetail(String expdDetail) {
		this.expdDetail = expdDetail;
	}

	public String getExpdTitle() {
		return expdTitle;
	}

	public void setExpdTitle(String expdTitle) {
		this.expdTitle = expdTitle;
	}

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public ExpenditureCatEntity getExpenditureCatEntity() {
		return expenditureCatEntity;
	}

	public void setExpenditureCatEntity(ExpenditureCatEntity expenditureCatEntity) {
		this.expenditureCatEntity = expenditureCatEntity;
	}

}
