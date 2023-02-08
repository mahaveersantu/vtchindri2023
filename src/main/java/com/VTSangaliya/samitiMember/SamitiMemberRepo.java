package com.VTSangaliya.samitiMember;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SamitiMemberRepo extends JpaRepository<SamitiMemberEntity, Integer> {

	

	

	List<SamitiMemberEntity> findAllByOrderByMemberPriority();

	//List<SamitiMemberEntity> findByExpdMsgSend(char c);

	//List<SamitiMemberEntity> findByExpdMsgSendIgnoreCase(char c);

	List<SamitiMemberEntity> findByExpdMsgSend(char c);

}
