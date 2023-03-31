package com.VTSangaliya.gairAarthikSahyog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GairAarthikRepo extends JpaRepository<GairAarthikSahyogEntity, Integer>{

	List<GairAarthikSahyogEntity> findByIsActiveOrderByApproxCostDesc(char c);



}
