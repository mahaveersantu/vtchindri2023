package com.VTSangaliya.expenditure;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tbl_expenditure_category")
public class ExpenditureCatEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int catId;
	private String catName;
	//@JsonIgnore
	@OneToMany(mappedBy = "expenditureCatEntity")
	private List<ExpenditureEntity> expenditureEntity;

//	public List<ExpenditureEntity> getExpenditureEntity() {
//		return expenditureEntity;
//	}

	public void setExpenditureEntity(List<ExpenditureEntity> expenditureEntity) {
		this.expenditureEntity = expenditureEntity;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

}
