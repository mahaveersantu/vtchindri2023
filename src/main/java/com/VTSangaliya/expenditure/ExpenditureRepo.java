package com.VTSangaliya.expenditure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenditureRepo extends JpaRepository<ExpenditureEntity, Integer> {



	List<ExpenditureEntity> findAllByExpenditureCatEntity(ExpenditureCatEntity expenditureCatEntity);
	List<ExpenditureEntity> findByIsActive(char c);
	ExpenditureEntity findByExpdReceiptNo(int expdId);
	List<ExpenditureEntity> findByIsActiveOrderByExpdAmountDesc(char c);


	List<ExpenditureEntity> findAllByOrderByExpdReceiptNo();
	//List<ExpenditureEntity> findByExpdReceiptNoAndIdNot(int expdReceiptNo, int expdId);
	List<ExpenditureEntity> findByExpdReceiptNoAndExpdIdNot(int expdReceiptNo, int expdId);

}
