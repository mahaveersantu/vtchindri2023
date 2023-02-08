package com.VTSangaliya.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementEntity;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementRepo;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogEntity;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogRepo;
import com.VTSangaliya.gairAarthikSahyog.GairAarthikRepo;
import com.VTSangaliya.gairAarthikSahyog.GairAarthikSahyogEntity;
import com.VTSangaliya.samitiMember.SamitiMemberEntity;
import com.VTSangaliya.samitiMember.SamitiMemberRepo;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MessageController {
	@Autowired
	private AarthikSahyogRepo aarthikSahyogRepo;
	@Autowired
	private AarthikSahyogAnnouncementRepo arthikSahyogAnnouncementRepo;
	@Autowired
	private GairAarthikRepo gairAarthikRepo;
	@Autowired
	private SamitiMemberRepo samitiMemberRepo;
	Integer total = 0;
	@Autowired
	private MessageService messageService;

	@RequestMapping("/getDetailForMessage")
	public List<MessageDTO> getDetailForMessage(@RequestBody Map<String, Integer> json) 
	{
		Integer id = json.get("id");
		List<MessageDTO> messageDTOList = new ArrayList<>();
		//messageDTOList=null;
		if(id==0)
		{
		List<AarthikSahyogAnnouncementEntity> findAll = arthikSahyogAnnouncementRepo.findAll();
		for (AarthikSahyogAnnouncementEntity arthikSahyogAnnouncementEntity : findAll) {
			MessageDTO obj = new MessageDTO();
			obj.setId(arthikSahyogAnnouncementEntity.getAnnId());
			obj.setName(arthikSahyogAnnouncementEntity.getName());
			obj.setMobile(arthikSahyogAnnouncementEntity.getMobile());
			obj.setTblId(1);
			messageDTOList.add(obj);
		}
		List<GairAarthikSahyogEntity> findAll1 = gairAarthikRepo.findAll();
		for (GairAarthikSahyogEntity gairAarthikSahyogEntity : findAll1) {
			MessageDTO obj = new MessageDTO();
			obj.setId(gairAarthikSahyogEntity.getId());
			obj.setName(gairAarthikSahyogEntity.getName());
			obj.setMobile(gairAarthikSahyogEntity.getMobile());
			obj.setTblId(2);
			messageDTOList.add(obj);
		}
	}
		//getAarthikSahyogMobile
		else if(id==1)
		{
			List<AarthikSahyogAnnouncementEntity> findAll = arthikSahyogAnnouncementRepo.findAll();
			for (AarthikSahyogAnnouncementEntity arthikSahyogAnnouncementEntity : findAll) {
				if (arthikSahyogAnnouncementEntity.getAarthikSahyogEntity().size() > 0) {
					MessageDTO obj = new MessageDTO();
					obj.setId(arthikSahyogAnnouncementEntity.getAnnId());
					obj.setName(arthikSahyogAnnouncementEntity.getName());
					obj.setMobile(arthikSahyogAnnouncementEntity.getMobile());
					obj.setTblId(1);
					messageDTOList.add(obj);
				}
			}
			
		}
		
		//getAnnouncementMobile
		else if(id==2)
		{
			List<AarthikSahyogAnnouncementEntity> findAll = arthikSahyogAnnouncementRepo.findAll();
			

			for (AarthikSahyogAnnouncementEntity arthikSahyogAnnouncementEntity : findAll) {

				total = 0;
				arthikSahyogAnnouncementEntity.getAarthikSahyogEntity().forEach(e -> {
					total += e.getAmount();
				});
				if (arthikSahyogAnnouncementEntity.getAnnounceAmount() > total) {
					MessageDTO obj = new MessageDTO();
					obj.setId(arthikSahyogAnnouncementEntity.getAnnId());
					obj.setName(arthikSahyogAnnouncementEntity.getName());
					obj.setMobile(arthikSahyogAnnouncementEntity.getMobile());
					obj.setTblId(1);
					messageDTOList.add(obj);
				}

			}
		}
		//getGairAarthikSahyogMobile
				else if(id==3)
				{
					List<GairAarthikSahyogEntity> findAll = gairAarthikRepo.findAll();
					

					for (GairAarthikSahyogEntity gairAarthikSahyogEntity : findAll) {
						MessageDTO obj = new MessageDTO();
						obj.setId(gairAarthikSahyogEntity.getId());
						obj.setName(gairAarthikSahyogEntity.getName());
						obj.setMobile(gairAarthikSahyogEntity.getMobile());
						obj.setTblId(2);
						messageDTOList.add(obj);
					}
				}
		
		//getSamitiMemberMobile
				else if(id==4)
				{
					List<SamitiMemberEntity> findAll = samitiMemberRepo.findAll();
					

					for (SamitiMemberEntity samitiMemberEntity : findAll) {
						MessageDTO obj = new MessageDTO();
						obj.setId(samitiMemberEntity.getId());
						obj.setName(samitiMemberEntity.getMemberName());
						obj.setMobile(samitiMemberEntity.getMemberMobile());
						obj.setTblId(3);
						messageDTOList.add(obj);
					}
				}
				else {
					messageDTOList=null;
				}
		return messageDTOList;	
		
		
	}
	
	@RequestMapping("/sendAnnouncementMsgManually")
	public ResponseEntity<String> sendAnnouncementMsgManually(@RequestBody Map<String, String> json) 
	{
		AarthikSahyogAnnouncementEntity arthikAnn=arthikSahyogAnnouncementRepo.findById(Integer.parseInt(json.get("annId"))).get();
		String name=null;
		String sahyogkrta=null;
		if(json.get("smsName")!=null)
		{
			sahyogkrta=json.get("smsName") + " जी ";
		}
		else
		{
			name=arthikAnn.getName();
			if(name.contains(" "))
			{
				sahyogkrta = name.substring(0, name.indexOf(" ")) + " जी ";
			}
			else {
				sahyogkrta=name + " जी ";
			}
			
		}
		
		//System.out.println("sahyogkrta name"+sahyogkrta);
		Boolean sendMessageToAnnouncement = messageService.sendMessageToAnnouncement(sahyogkrta,arthikAnn.getAnnounceAmount(), arthikAnn.getMobile());
		if(sendMessageToAnnouncement)
		{
		return new ResponseEntity<>("success", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	

}
