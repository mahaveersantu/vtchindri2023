package com.VTSangaliya.gairAarthikSahyog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GairAarthikSahyogController {
	@Autowired
	private GairAarthikRepo gairAarthikRepo;

	@RequestMapping("/getAllGairAarthikSahyog")
	public List<GairAarthikSahyogEntity> getAllGairAarthikSahyog() {
		List<GairAarthikSahyogEntity> findAll = gairAarthikRepo.findAll();
		int i = 0;
		for (GairAarthikSahyogEntity gairAarthikSahyogEntity : findAll) {
			i++;
			gairAarthikSahyogEntity.setSrNo(i);
		}
		return findAll;
	}

	@RequestMapping("/getAllActiveGairAarthikSahyog")
	public List<GairAarthikSahyogEntity> getAllActiveGairAarthikSahyog() {
		List<GairAarthikSahyogEntity> findAll = gairAarthikRepo.findByIsActiveOrderByApproxCostDesc('Y');
		int i = 0;
		for (GairAarthikSahyogEntity gairAarthikSahyogEntity : findAll) {
			i++;
			gairAarthikSahyogEntity.setSrNo(i);
		}
		return findAll;
	}
	@PostMapping("/saveAndUpdateGairAarthikSahyog")
	public String saveAndUpdateGairAarthikSahyog(@RequestBody GairAarthikSahyogEntity gairAarthikSahyogEntity) {
		GairAarthikSahyogEntity saved = gairAarthikRepo.save(gairAarthikSahyogEntity);
		if (saved != null) {
			return "success";
		} else {
			return "fail";
		}

	}
}
