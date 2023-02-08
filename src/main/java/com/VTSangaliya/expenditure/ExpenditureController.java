package com.VTSangaliya.expenditure;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.VTSangaliya.aarthikSahyog.AarthikSahyogEntity;
import com.VTSangaliya.messages.MessageService;
import com.VTSangaliya.samitiMember.SamitiMemberEntity;
import com.VTSangaliya.samitiMember.SamitiMemberRepo;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ExpenditureController {

	@Autowired
	private ExpenditureCatRepo expenditureCatRepo;
	@Autowired
	private ExpenditureRepo expenditureRepo;
	@Autowired
	private com.VTSangaliya.common.DownloadFile downloadFile;

	@Autowired
	private SamitiMemberRepo samitiMemberRepo;
	
	@Autowired
	private MessageService messageService;
	int year = 0;

	@RequestMapping("/getAllCategories")
	public List<ExpenditureCatEntity> getAllCategory() {
		return expenditureCatRepo.findAll();
	}

	

	@RequestMapping("/saveAndUpdateCategory")
	public String saveAndUpdateCategory(@RequestBody ExpenditureCatEntity expenditureCatEntity) {
		ExpenditureCatEntity saved = expenditureCatRepo.save(expenditureCatEntity);
		if (saved != null) {
			return "success";
		} else {
			return "fail";
		}

	}

	@RequestMapping("/getAllActiveExpenditures")
	public List<ExpenditureEntity> getAllActiveExpenditures() {
		List<ExpenditureEntity> findAll = expenditureRepo.findByIsActiveOrderByExpdAmountDesc('Y');
		int i = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		for (ExpenditureEntity expenditureEntity : findAll) {
			i++;
			expenditureEntity.setExpdStringDate(expenditureEntity.getExpdDate().format(formatter));
			expenditureEntity.setSrNo(i);
		}
		return findAll;
	}

	@RequestMapping("/getAllExpenditures")
	public List<ExpenditureEntity> getAllExpenditures() {
		List<ExpenditureEntity> findAll = expenditureRepo.findAllByOrderByExpdReceiptNo();
		int i = 0;
		for (ExpenditureEntity expenditureEntity : findAll) {
			i++;
			expenditureEntity.setSrNo(i);
		}
		return findAll;
	}

	@RequestMapping("/getExpenditureByCatId")
	public List<ExpenditureEntity> getExpenditureByCatId(@RequestBody Map<String, Integer> json) {
		ExpenditureCatEntity expenditureCatEntity = new ExpenditureCatEntity();
		expenditureCatEntity.setCatId(json.get("catId"));
		List<ExpenditureEntity> findAllByExpenditureCatEntity = expenditureRepo
				.findAllByExpenditureCatEntity(expenditureCatEntity);
		int i = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		for (ExpenditureEntity expenditureEntity : findAllByExpenditureCatEntity) {
			i++;
			expenditureEntity.setExpdStringDate(expenditureEntity.getExpdDate().format(formatter));
			expenditureEntity.setSrNo(i);
		}
		return findAllByExpenditureCatEntity;
	}

	// , consumes = MediaType.MULTIPART_FORM_DATA_VALUE
	@RequestMapping(value = "/saveAndUpdateExpenditure", method = RequestMethod.POST)
	public String saveExpenditure(@RequestBody ExpenditureEntity expenditureEntity) throws IOException {
		String status;
		ExpenditureEntity saved = new ExpenditureEntity();
//update expenditure
		if (expenditureEntity.getExpdId() > 0) {
			List<ExpenditureEntity> getData = expenditureRepo.findByExpdReceiptNoAndExpdIdNot(
					expenditureEntity.getExpdReceiptNo(), expenditureEntity.getExpdId());
			if (getData.size() > 0) {
				status = "exist";
			} else {
				ExpenditureEntity expenditure = expenditureRepo.findById(expenditureEntity.getExpdId()).get();
				// System.out.println("++++++++++++++++++"+expenditure.getExpdReceiptNo());
				expenditureEntity.setUpdatedOn(LocalDate.now());
				expenditureEntity.setAddedOn(expenditure.getAddedOn());
				saved = expenditureRepo.save(expenditureEntity);
				// System.out.println("++++++++++saved++++++++"+saved.getReceiverName() );
				expenditure = null;
				status = "success";
			}
		}
//save new expenditure
		else {
			ExpenditureEntity getData = expenditureRepo.findByExpdReceiptNo(expenditureEntity.getExpdReceiptNo());
			if (getData == null) {
				expenditureEntity.setAddedOn(LocalDate.now());
				saved = expenditureRepo.save(expenditureEntity);

				// send msg to samiti members
				StringBuilder mobileList = new StringBuilder();
				List<SamitiMemberEntity> list =samitiMemberRepo.findByExpdMsgSend('Y');
				System.out.println("total mobile"+list.size());
				for (SamitiMemberEntity samitiMemberEntity : list) {
					mobileList.append(samitiMemberEntity.getMemberMobile());
					mobileList.append(",");
					//System.out.println("mobile no"+samitiMemberEntity.getMemberMobile());
				}
				//System.out.println("mobile list "+ mobileList);
				
				//System.out.println("mobile list is "+ mobileList);
				if(mobileList.length()>0)
				{
				String substringMobile = mobileList.substring(0,mobileList.length()-1);
				//System.out.println("mobile list is ++"+ substring);
				String fname;
				String sname;
				fname = saved.getReceiverName().substring(0, saved.getReceiverName().indexOf(" "));
				sname= saved.getReceiverName().substring(fname.length(), saved.getReceiverName().length());
				//System.out.println("second name "+sname);
				messageService.sendMessageToSamitiMemberForExpenditures(fname, sname, saved.getExpdAmount(), saved.getExpdReceiptNo(), saved.getExpdDetail(),substringMobile);
				}
				expenditureEntity = null;
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

	@RequestMapping(value = "/saveExpenditureReceipt", method = RequestMethod.POST)

	public void saveImage(@RequestParam("receipt") MultipartFile receipt, @RequestParam("receiptNo") int receiptNo)
			throws IOException {
		// System.out.println("file name "+receipt.getOriginalFilename());
		// System.out.println("receipt no "+receiptNo);
		if (receipt != null && receipt.getSize() <= 2097152
				&& (receipt.getContentType().contains("jpg") || receipt.getContentType().contains("jpeg"))) {
			// System.out.println("receipt "+receipt.getContentType());
			ExpenditureEntity getData = expenditureRepo.findByExpdReceiptNo(receiptNo);
			getData.setReceiptPdf(receipt.getBytes());

			expenditureRepo.save(getData);
			// return "success";

		} else {
			// return "failed";

		}

		// System.out.println("called");
		// System.out.println("receipt"+receipt.getOriginalFilename());

	}

	@RequestMapping(value = "/downloadReceipt") // , produces = "application/pdf" @RequestBody Map<String, Integer> json
	public ResponseEntity<Resource> downloadReceipt(@RequestParam("expdNo") int expdNo, HttpServletRequest request) {
		// System.out.println("expdNo "+ expdNo);
		ExpenditureEntity findByExpdReceiptNo = expenditureRepo.findByExpdReceiptNo(expdNo);

		return downloadFile.DownloadFile(findByExpdReceiptNo.getReceiptPdf(),
				"receipt-" + findByExpdReceiptNo.getExpdReceiptNo(), "jpeg");
	}

	@RequestMapping("/totalExpenditure")
	public Double totalExpenditure() {
		List<ExpenditureEntity> findAll = expenditureRepo.findByIsActiveOrderByExpdAmountDesc('Y');
		Double total = 0.0;
		for (ExpenditureEntity expenditureEntity : findAll) {

			total += expenditureEntity.getExpdAmount();
		}
		return total;
	}

	@RequestMapping("/totalExpenditureLastMonth")
	public Double totalExpenditureLastMonth() {
		List<ExpenditureEntity> findAll = expenditureRepo.findByIsActiveOrderByExpdAmountDesc('Y');

		if (LocalDate.now().minusMonths(1).getMonthValue() == 12) {
			year = LocalDate.now().minusYears(1).getYear();
		} else {
			year = LocalDate.now().getYear();
		}
		List<ExpenditureEntity> lastMonthExpd = findAll.stream()
				.filter(p -> p.getExpdDate().getYear() == year
						&& p.getExpdDate().getMonthValue() == (LocalDate.now().minusMonths(1).getMonthValue()))
				.collect(Collectors.toList());
		Double total = 0.0;
		for (ExpenditureEntity expenditureEntity : lastMonthExpd) {
			// System.out.println(expenditureEntity.getExpdDate());
			total += expenditureEntity.getExpdAmount();

		}
		return total;
	}

	@RequestMapping("/totalExpenditureByCatId")
	public Double totalExpenditureByCatId(@RequestBody Map<String, Integer> json) {
		ExpenditureCatEntity expenditureCatEntity = new ExpenditureCatEntity();
		expenditureCatEntity.setCatId(json.get("catId"));
		List<ExpenditureEntity> findAllByExpenditureCatEntity = expenditureRepo
				.findAllByExpenditureCatEntity(expenditureCatEntity);
		Double total = 0.0;
		for (ExpenditureEntity expenditureEntity : findAllByExpenditureCatEntity) {
			// System.out.println(expenditureEntity.getExpdDate());
			total += expenditureEntity.getExpdAmount();

		}
		return total;
	}
	
	
	

}
