package com.VTSangaliya.aarthikSahyog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AarthikSahyogRepo extends JpaRepository<AarthikSahyogEntity, Integer> {

	List<AarthikSahyogEntity> findAllByReceiptNoAndIdNot(String receiptNo, int id);

	List<AarthikSahyogEntity> findByReceiptNo(String receiptNo);

}
