package com.VTSangaliya.samitiMember;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SamitiMemberController {
	@Autowired
	private SamitiMemberRepo samitiMemberRepo;
	@RequestMapping("/getAllSamitiMembers")
	public List<SamitiMemberEntity> getAllSamitiMembers() {
		 List<SamitiMemberEntity> findAll = samitiMemberRepo.findAll();
		 int i=0;
		 for (SamitiMemberEntity samitiMemberEntity : findAll) {
			i++;
			samitiMemberEntity.setSrNo(i);
		}
		 return findAll;


	}
	@RequestMapping("/getAllActiveSamitiMembers")
	public List<SamitiMemberEntity> getAllActiveSamitiMembers() {
		 return  samitiMemberRepo.findAllByOrderByMemberPriority();



	}
	@PostMapping("/saveAndUpdateSamitiMember")
	public String saveAndUpdateSamitiMember(@RequestBody SamitiMemberEntity samitiMemberEntity) {
		SamitiMemberEntity saved = samitiMemberRepo.save(samitiMemberEntity);
		if (saved != null) {
			return "success";
		} else {
			return "fail";
		}

	}
}
