package com.VTSangaliya.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepo extends JpaRepository<VisitorEntity, Integer> {

	List<VisitorEntity> findByIpAddress(String ipAddress);

}
