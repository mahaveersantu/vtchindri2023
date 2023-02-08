package com.VTSangaliya.aarthikSahyog;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.VTSangaliya.messages.MessageDTOWrapper;
import com.VTSangaliya.messages.MessageService;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
public class AarthikSahyogController {
	@Autowired
	private AarthikSahyogAnnouncementRepo arthikSahyogAnnouncementRepo;
	@Autowired
	private AarthikSahyogRepo aarthikSahyogRepo;
	@Autowired
	private MessageService messageService;
	int total = 0;
	int pendingAmount = 0;
	Double totalReceived = 0.0;
	Double totalAnnounce = 0.0;
	int year = 0;

	@GetMapping("/getAllArthikSahyogAnnounce")
	public List<AarthikSahyogAnnouncementEntity> getAllArthikSahyogAnnounce() {
		return arthikSahyogAnnouncementRepo.findAll();
	}

	@GetMapping("/getAllActiveArthikSahyogAnnounce")
	public List<AarthikSahyogAnnouncementEntity> getAllActiveArthikSahyog() {
		return arthikSahyogAnnouncementRepo.findByIsActive('Y');
	}

	@RequestMapping("/getAllArthikSahyogAnnounceByMobile")
	public List<AarthikSahyogAnnouncementEntity> getAllArthikSahyogAnnounceByMobile(
			@RequestBody Map<String, String> json) {
		// AarthikSahyogAnnouncementEntity arthikSahyogAnnouncementEntity = new
		// AarthikSahyogAnnouncementEntity();
		// arthikSahyogAnnouncementEntity(json.get("catId"));
		// System.out.println("mobile no"+json.get("mobile"));
		List<AarthikSahyogAnnouncementEntity> findAllByMobile = arthikSahyogAnnouncementRepo
				.findByMobile(json.get("mobile"));

		int i = 0;

		for (AarthikSahyogAnnouncementEntity arthikSahyogAnnouncement : findAllByMobile) {
			i++;
			arthikSahyogAnnouncement.setSrNo(i);
		}
		return findAllByMobile;
	}

	@RequestMapping("/deleteReceipt")
	public ResponseEntity<String> deleteReceipt(@RequestBody Map<String, Integer> json) {
		aarthikSahyogRepo.deleteById(json.get("receiptId"));

		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@RequestMapping("/deleteAnnouncement")
	public ResponseEntity<String> deleteAnnouncement(@RequestBody Map<String, Integer> json) {
		// System.out.println("ann id"+json.get("annId"));
		// boolean flage=false;
		if (arthikSahyogAnnouncementRepo.findById(json.get("annId")).get().getAarthikSahyogEntity().size() == 0) 
		{
			arthikSahyogAnnouncementRepo.deleteById(json.get("annId"));
			return new ResponseEntity<>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping("/saveAndUpdateArthikSahyog")
	public String saveAndUpdateArthikSahyog(
			@RequestBody AarthikSahyogAnnouncementEntity arthikSahyogAnnouncementEntity) {
		String status;
		AarthikSahyogAnnouncementEntity saved = new AarthikSahyogAnnouncementEntity();
		if (arthikSahyogAnnouncementEntity.getAnnId() > 0) {
			// ExpenditureEntity findById =
			// expenditureRepo.findById(expenditureEntity.getExpdId()).get();
			List<AarthikSahyogAnnouncementEntity> findByMobile = arthikSahyogAnnouncementRepo
					.findAllByMobileAndAnnIdNot(arthikSahyogAnnouncementEntity.getMobile(),
							arthikSahyogAnnouncementEntity.getAnnId());
			if (findByMobile.size() > 0) {
				status = "exist";
			} else {
				// arthikSahyogEntity.setAddedOn(LocalDate.now());
				AarthikSahyogAnnouncementEntity aarthikSahyog = arthikSahyogAnnouncementRepo
						.findById(arthikSahyogAnnouncementEntity.getAnnId()).get();
				arthikSahyogAnnouncementEntity.setAddedOn(aarthikSahyog.getAddedOn());
				saved = arthikSahyogAnnouncementRepo.save(arthikSahyogAnnouncementEntity);
				status = "success";
			}

		} else {
			List<AarthikSahyogAnnouncementEntity> getData = arthikSahyogAnnouncementRepo
					.findByMobile(arthikSahyogAnnouncementEntity.getMobile());
			if (getData.size() == 0) {
				arthikSahyogAnnouncementEntity.setAddedOn(LocalDate.now());

				saved = arthikSahyogAnnouncementRepo.save(arthikSahyogAnnouncementEntity);
				// send sms to sahyogkrta
				String name = null;
				String sahyogkrta = null;
				if (arthikSahyogAnnouncementEntity.getSmsName() == null) {
					name = saved.getName();
					if (name.contains(" ")) {
						sahyogkrta = name.substring(0, name.indexOf(" ")) + " जी";
					} else {
						sahyogkrta = name + " जी ";
					}
				} else {
					sahyogkrta = arthikSahyogAnnouncementEntity.getSmsName() + " Ji";
				}
				messageService.sendMessageToAnnouncement(sahyogkrta, saved.getAnnounceAmount(), saved.getMobile());
				status = "success";
			} else {
				status = "exist";
			}
		}
		if (saved == null) {
			status = "fail";
		}
		return status;
	}

	@RequestMapping("/saveAndUpdateArthikSahyogReceipt")
	public String saveAndUpdateArthikSahyogReceipt(@RequestBody AarthikSahyogEntity arthikSahyogEntity) {
		// System.out.println("saved aarthik");
		String status;

		AarthikSahyogAnnouncementEntity arthikSahyogAnnouncementEntity = arthikSahyogAnnouncementRepo
				.findById(arthikSahyogEntity.getAnnounceId()).get();
		AarthikSahyogEntity saved = new AarthikSahyogEntity();
		// for receipt update
		if (arthikSahyogEntity.getId() > 0) {
			// ExpenditureEntity findById =
			// expenditureRepo.findById(expenditureEntity.getExpdId()).get();
			List<AarthikSahyogEntity> findByReceiptNo = aarthikSahyogRepo
					.findAllByReceiptNoAndIdNot(arthikSahyogEntity.getReceiptNo(), arthikSahyogEntity.getId());
			if (findByReceiptNo.size() > 0) {
				status = "exist";
			} else {
				// System.out.println("in else block");
				AarthikSahyogEntity arthikSahyo = aarthikSahyogRepo.findById(arthikSahyogEntity.getId()).get();
				arthikSahyogEntity.setAddedOn(arthikSahyo.getAddedOn());
				arthikSahyogEntity.setAarthikSahyogAnnouncementEntity(arthikSahyo.getAarthikSahyogAnnouncementEntity());
				saved = aarthikSahyogRepo.save(arthikSahyogEntity);

				status = "success";
			}

		}

		// for add new receipt
		else {
			List<AarthikSahyogEntity> getData = aarthikSahyogRepo.findByReceiptNo(arthikSahyogEntity.getReceiptNo());
			if (getData.size() == 0) {
				arthikSahyogEntity.setAddedOn(LocalDate.now());
				arthikSahyogEntity.setAarthikSahyogAnnouncementEntity(arthikSahyogAnnouncementEntity);
				saved = aarthikSahyogRepo.save(arthikSahyogEntity);
				// send msg to sahyogkrta
				String name = null;
				String sahyogkrta = null;
				// System.out.println("smsName Not found2"+arthikSahyogEntity.getSmsName());
				if (arthikSahyogEntity.getSmsName() == null) {
					name = saved.getAarthikSahyogAnnouncementEntity().getName();

					// name = saved.getName();
					if (name.contains(" ")) {
						sahyogkrta = name.substring(0, name.indexOf(" ")) + " जी";
					} else {
						sahyogkrta = name + " जी ";
					}
				} else {
					sahyogkrta = arthikSahyogEntity.getSmsName()+" Ji";
				}
				System.out.println("message send to length" + sahyogkrta.length());
				System.out.println("message send to" + sahyogkrta);
				messageService.sendMessageToAarthikSahyog(sahyogkrta,
						saved.getAarthikSahyogAnnouncementEntity().getMobile(), saved.getAmount(), saved.getReceiptNo(),
						saved.getReceiptDate());
				status = "success";
			} else {
				status = "exist";
			}
		}
		if (saved == null) {
			status = "fail";
		}
		return status;
	}

	@GetMapping("/getAllActiveSahyogAnnouncement")
	public List<AarthikSahyogAnnouncementEntity> getAllSahyogAnnouncement() {

		List<AarthikSahyogAnnouncementEntity> findAll = arthikSahyogAnnouncementRepo
				.findByIsActiveOrderByAnnounceAmountDesc('Y');
		int i = 0;
		for (AarthikSahyogAnnouncementEntity aarthikSahyogAnnouncementEntity : findAll) {
			total = 0;

			aarthikSahyogAnnouncementEntity.getAarthikSahyogEntity().forEach(e -> {
				total += e.getAmount();
			});
			i++;

			aarthikSahyogAnnouncementEntity
					.setPendingAmount(aarthikSahyogAnnouncementEntity.getAnnounceAmount() - total);
			aarthikSahyogAnnouncementEntity.setSrNo(i);
			aarthikSahyogAnnouncementEntity.setGrandTotal(total);
		}
		return findAll;
	}

	@GetMapping("/getAllSahyogAnnouncementForAdmin")
	public List<AarthikSahyogAnnouncementEntity> getAllSahyogAnnouncementForAdmin() {

		List<AarthikSahyogAnnouncementEntity> findAll = arthikSahyogAnnouncementRepo.findByOrderByAddedOn();
		int i = 0;
		for (AarthikSahyogAnnouncementEntity aarthikSahyogAnnouncementEntity : findAll) {
			total = 0;

			aarthikSahyogAnnouncementEntity.getAarthikSahyogEntity().forEach(e -> {
				total += e.getAmount();
			});
			i++;

			aarthikSahyogAnnouncementEntity
					.setPendingAmount(aarthikSahyogAnnouncementEntity.getAnnounceAmount() - total);
			aarthikSahyogAnnouncementEntity.setSrNo(i);
			aarthikSahyogAnnouncementEntity.setGrandTotal(total);
		}
		return findAll;
	}

	@GetMapping("/getTopTenSahyog")
	public List<AarthikSahyogAnnouncementEntity> getTopTenSahyog() {
		List<AarthikSahyogAnnouncementEntity> findAll = arthikSahyogAnnouncementRepo.findByIsActive('Y');
		// int i = 0;
		for (AarthikSahyogAnnouncementEntity aarthikSahyogAnnouncementEntity : findAll) {
			total = 0;
			aarthikSahyogAnnouncementEntity.getAarthikSahyogEntity().forEach(e -> {
				total += e.getAmount();
			});
			// i++;
			// aarthikSahyogAnnouncementEntity.setPendingAmount(aarthikSahyogAnnouncementEntity.getAnnounceAmount()-total);
			// aarthikSahyogAnnouncementEntity.setSrNo(i);
			aarthikSahyogAnnouncementEntity.setGrandTotal(total);
		}

		findAll.sort((AarthikSahyogAnnouncementEntity a1, AarthikSahyogAnnouncementEntity a2) -> a2.getGrandTotal()
				.compareTo(a1.getGrandTotal()));
		List<AarthikSahyogAnnouncementEntity> topTen = new ArrayList<AarthikSahyogAnnouncementEntity>();
		int i;
		for (i = 0; i < 10; i++) {
			AarthikSahyogAnnouncementEntity obj = new AarthikSahyogAnnouncementEntity();
			obj = findAll.get(i);
			topTen.add(obj);

		}

		return topTen;
	}

	@RequestMapping("/totalReceivedAmount")
	public Double totalReceivedAmount() {
		// List<ExpenditureEntity> findAll =
		// expenditureRepo.findByIsActiveOrderByExpdAmountDesc('Y');

		List<AarthikSahyogAnnouncementEntity> findAll = arthikSahyogAnnouncementRepo
				.findByIsActiveOrderByAnnounceAmountDesc('Y');

		totalReceived = 0.0;
		for (AarthikSahyogAnnouncementEntity aarthikSahyogAnnouncementEntity : findAll) {

			aarthikSahyogAnnouncementEntity.getAarthikSahyogEntity().forEach(e -> {
				totalReceived += e.getAmount();
			});

		}
		return totalReceived;
	}

	@RequestMapping("/totalReceivedLastMonth")
	public Double totalReceivedLastMonth() {
		// List<ExpenditureEntity> findAll =
		// expenditureRepo.findByIsActiveOrderByExpdAmountDesc('Y');
		List<AarthikSahyogEntity> findAll = aarthikSahyogRepo.findAll();
		if (LocalDate.now().getMonthValue() == 1) {
			year = LocalDate.now().minusYears(1).getYear();
		} else {
			year = LocalDate.now().getYear();
		}
		List<AarthikSahyogEntity> lastMonthReceived = findAll.stream()
				.filter(p -> p.getReceiptDate().getYear() == year
						&& p.getReceiptDate().getMonthValue() == (LocalDate.now().minusMonths(1).getMonthValue()))
				.collect(Collectors.toList());

		Double total = 0.0;
		for (AarthikSahyogEntity shyogEntity : lastMonthReceived) {
			// System.out.println(expenditureEntity.getExpdDate());
			total += shyogEntity.getAmount();

		}
		return total;
	}

	@RequestMapping("/totalAnnounceAmount")
	public Double totalAnnounceAmount() {
		// List<ExpenditureEntity> findAll =
		// expenditureRepo.findByIsActiveOrderByExpdAmountDesc('Y');

		List<AarthikSahyogAnnouncementEntity> findAll = arthikSahyogAnnouncementRepo
				.findByIsActiveOrderByAnnounceAmountDesc('Y');
		totalAnnounce = 0.0;
		for (AarthikSahyogAnnouncementEntity aarthikSahyogAnnouncementEntity : findAll) {
			totalAnnounce += aarthikSahyogAnnouncementEntity.getAnnounceAmount();
		}
		return totalAnnounce;
	}

	@GetMapping("/getAllSahyogAndAnnouncement")
	public List<AllAnnouncementService> getAllSahyogAndAnnouncement() {
		List<AllAnnouncementService> allRecords = new ArrayList<>();
		List<AarthikSahyogAnnouncementEntity> findAll = arthikSahyogAnnouncementRepo.findAll();
		for (AarthikSahyogAnnouncementEntity aarthikSahyogAnnouncementEntity : findAll) {

			for (AarthikSahyogEntity ar : aarthikSahyogAnnouncementEntity.getAarthikSahyogEntity()) {
				total = 0;
				for (AarthikSahyogEntity ar1 : aarthikSahyogAnnouncementEntity.getAarthikSahyogEntity()) {
					total += ar1.getAmount();
				}

				AllAnnouncementService records = new AllAnnouncementService();
				records.setAnnId(aarthikSahyogAnnouncementEntity.getAnnId());
				records.setName(aarthikSahyogAnnouncementEntity.getName());
				records.setAddress(aarthikSahyogAnnouncementEntity.getAddress());
				records.setMobile(aarthikSahyogAnnouncementEntity.getMobile());
				records.setAnnounceAmount(aarthikSahyogAnnouncementEntity.getAnnounceAmount());
				records.setAddedOn(aarthikSahyogAnnouncementEntity.getAddedOn());
				records.setIsActive(aarthikSahyogAnnouncementEntity.getIsActive());
				records.setId(ar.getId());
				records.setReceiptNo(ar.getReceiptNo());
				records.setReceiptDate(ar.getReceiptDate());
				records.setAmount(ar.getAmount());
				records.setGrandTotal(total);
				allRecords.add(records);
			}
			;
			if (aarthikSahyogAnnouncementEntity.getAarthikSahyogEntity().size() == 0) {
				AllAnnouncementService records = new AllAnnouncementService();
				records.setAnnId(aarthikSahyogAnnouncementEntity.getAnnId());
				records.setName(aarthikSahyogAnnouncementEntity.getName());
				records.setAddress(aarthikSahyogAnnouncementEntity.getAddress());
				records.setMobile(aarthikSahyogAnnouncementEntity.getMobile());
				records.setAnnounceAmount(aarthikSahyogAnnouncementEntity.getAnnounceAmount());
				records.setAddedOn(aarthikSahyogAnnouncementEntity.getAddedOn());
				records.setIsActive(aarthikSahyogAnnouncementEntity.getIsActive());
				allRecords.add(records);
			}
			// aarthikSahyogAnnouncementEntity.setGrandTotal(total);
		}
		return allRecords;
	}
	@RequestMapping("sendMessageByAdmin/{typeId}")
	public ResponseEntity<?> sendMessageByAdmin(@RequestBody MessageDTOWrapper msgDetail,@PathVariable("typeId") Integer typeId) throws IOException
	{
		System.out.println(msgDetail.getMessageDTO().get(0));
		System.out.println("called sendMessage");
		if(typeId==1)
		{
			
			List<Integer> list = new ArrayList<Integer>();
			
			msgDetail.getMessageDTO().forEach(e->{
			
				list.add(e.getId());
			
			});
		List<AarthikSahyogAnnouncementEntity> detailList=arthikSahyogAnnouncementRepo.findByAnnIdIn(list);
	
		for (AarthikSahyogAnnouncementEntity announce : detailList) {
			total = 0;
			announce.getAarthikSahyogEntity().forEach(e -> {
				total += e.getAmount();
			});
			String name=announce.getName().substring(0, announce.getName().indexOf(" "))+" जी";
			System.out.println("msg send to"+name);
			Integer annAmount = announce.getAnnounceAmount();
			//System.out.println("msg ann amount"+annAmount);
			//Integer receivedAmount = total;
			Integer pendingAmount=annAmount-total;
			//System.out.println("msg pending amount"+total);
			String mobile = announce.getMobile();
			//String mobile = "9511548512";	
			System.out.println("msg mobile"+mobile);
			try {
			messageService.sendMessageToPendingAnnoucementByAdmin(name, annAmount, pendingAmount, mobile);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
				
			}
			}
	
		}
		return new ResponseEntity<String>("success",HttpStatus.OK);
		
	}
	

}
