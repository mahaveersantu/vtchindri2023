//package com.VTSangaliya;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementEntity;
//import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementRepo;
//import com.VTSangaliya.aarthikSahyog.AarthikSahyogEntity;
//import com.VTSangaliya.aarthikSahyog.AarthikSahyogRepo;
//import com.VTSangaliya.expenditure.ExpenditureCatRepo;
//import com.VTSangaliya.expenditure.ExpenditureEntity;
//import com.VTSangaliya.expenditure.ExpenditureRepo;
//import com.VTSangaliya.messages.MessageService;
//
//import net.sf.jasperreports.engine.JRException;
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RestController
//public class ReportController {
//	
//	@Autowired
//	private ExpenditureCatRepo expenditureCatRepo;
//	@Autowired
//	private ExpenditureRepo expenditureRepo;
//	@Autowired
//	private ReportService reportService;
//	@Autowired
//	private AarthikSahyogAnnouncementRepo arthikSahyogAnnouncementRepo;
//	@Autowired
//	private AarthikSahyogRepo aarthikSahyogRepo;
//	
//	int total = 0;
//	int pendingAmount = 0;
//	Double totalReceived = 0.0;
//	Double totalAnnounce = 0.0;
//	int year = 0;
//
//	@RequestMapping("/generateReport/{id}")
//	public ResponseEntity<?> generateExcel(@PathVariable("id") Integer id) throws JRException
//	{
//		//System.out.println("id is"+id);
//		if(id==1)//expenditure details excel report
//		{
//			List<ExpenditureEntity> findAll = expenditureRepo.findAllByOrderByExpdReceiptNo();
//			int i = 0;
//			for (ExpenditureEntity expenditureEntity : findAll) {
//				i++;
//				expenditureEntity.setSrNo(i);
//			}
//			reportService.createExpenditureExcelReport(findAll);
//			return new ResponseEntity<String>("success",HttpStatus.OK);
//			
//		}
//		if(id==2)//announcement Details excel report
//		{
//			List<AarthikSahyogAnnouncementEntity> findAll = arthikSahyogAnnouncementRepo
//					.findByIsActiveOrderByAnnounceAmountDesc('Y');
//			int i = 0;
//			for (AarthikSahyogAnnouncementEntity aarthikSahyogAnnouncementEntity : findAll) {
//				total = 0;
//
//				aarthikSahyogAnnouncementEntity.getAarthikSahyogEntity().forEach(e -> {
//					total += e.getAmount();
//				});
//				i++;
//
//				aarthikSahyogAnnouncementEntity
//						.setPendingAmount(aarthikSahyogAnnouncementEntity.getAnnounceAmount() - total);
//				aarthikSahyogAnnouncementEntity.setSrNo(i);
//				aarthikSahyogAnnouncementEntity.setGrandTotal(total);
//			}
//			
//			
//			reportService.createAnnouncementExcelReport(findAll);
//			return new ResponseEntity<String>("success",HttpStatus.OK);
//			
//		}
//		
//		if(id==3)//receipt Details excel report
//		{
//			List<AarthikSahyogEntity> findAll = aarthikSahyogRepo.findAll();
//			return new ResponseEntity<String>("success",HttpStatus.OK);
//
//			}
//			
//			
//		//	reportService.createAnnouncementExcelReport(findAll);
//		return new ResponseEntity<String>("success",HttpStatus.OK);
//		}
//	
//	}
//
//
