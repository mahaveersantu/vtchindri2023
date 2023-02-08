package com.VTSangaliya.aarthikSahyog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.VTSangaliya.messages.MessageDTO;

@Repository
public interface AarthikSahyogAnnouncementRepo extends JpaRepository<AarthikSahyogAnnouncementEntity, Integer> {

	List<AarthikSahyogAnnouncementEntity> findByIsActive(char c);

	List<AarthikSahyogAnnouncementEntity> findByMobile(String mobile);

	

	List<AarthikSahyogAnnouncementEntity> findAllByMobileAndAnnIdNot(String mobile, int id);

	List<AarthikSahyogAnnouncementEntity> findByIsActiveOrderByAnnounceAmountDesc(char c);

	//List<AarthikSahyogAnnouncementEntity> findOrderByAddedOn();

	List<AarthikSahyogAnnouncementEntity> findByOrderByAddedOn();

	List<AarthikSahyogAnnouncementEntity> findByAnnIdIn(List<Integer> list);

}
