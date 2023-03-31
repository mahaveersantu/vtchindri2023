package com.VTSangaliya.citizen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementEntity;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementRepo;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogEntity;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogRepo;
import com.VTSangaliya.common.Utilities;
import com.VTSangaliya.expenditure.ExpenditureCatRepo;
import com.VTSangaliya.expenditure.ExpenditureEntity;
import com.VTSangaliya.expenditure.ExpenditureRepo;
import com.VTSangaliya.gairAarthikSahyog.GairAarthikRepo;
import com.VTSangaliya.gairAarthikSahyog.GairAarthikSahyogEntity;
import com.VTSangaliya.messages.MessageService;
import com.VTSangaliya.samitiMember.SamitiMemberEntity;
import com.VTSangaliya.samitiMember.SamitiMemberRepo;
import com.VTSangaliya.user.TotalVisitorRepo;
import com.VTSangaliya.user.UserRepo;
import com.VTSangaliya.user.VisitorEntity;
import com.VTSangaliya.user.VisitorRepo;

@Service
public class SessionServices {
	@Autowired
	private AarthikSahyogAnnouncementRepo arthikSahyogAnnouncementRepo;
	@Autowired
	private AarthikSahyogRepo aarthikSahyogRepo;
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private VisitorRepo visitorRepo;
	@Autowired
	private TotalVisitorRepo totalVisitorRepo;
	@Autowired
	private GairAarthikRepo gairAarthikRepo;
	Double totalLastMonthReceived = 0.0;
	int pendingAmount = 0;
	Double totalReceived = 0.0;
	Double totalExpndLastMonth = 0.0;
	Double totalAnnounce = 0.0;
	Double totalExpd = 0.0;
	int totalTopTen;
	int year = 0;
	int total;
	@Autowired
	private ExpenditureCatRepo expenditureCatRepo;
	@Autowired
	private ExpenditureRepo expenditureRepo;
	@Autowired
	private com.VTSangaliya.common.DownloadFile downloadFile;

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private SamitiMemberRepo samitiMemberRepo;

	public void setSession()
	{

		List<AarthikSahyogAnnouncementEntity> findAll = arthikSahyogAnnouncementRepo
				.findByIsActiveOrderByAnnounceAmountDesc('Y');

		totalReceived = 0.0;
		for (AarthikSahyogAnnouncementEntity aarthikSahyogAnnouncementEntity : findAll) {

			aarthikSahyogAnnouncementEntity.getAarthikSahyogEntity().forEach(e -> {
				totalReceived += e.getAmount();
			});

		}

		List<AarthikSahyogEntity> findAllRuppee = aarthikSahyogRepo.findAll();
		if (LocalDate.now().getMonthValue() == 1) {
			year = LocalDate.now().minusYears(1).getYear();
		} else {
			year = LocalDate.now().getYear();
		}
		List<AarthikSahyogEntity> lastMonthReceived = findAllRuppee.stream()
				.filter(p -> p.getReceiptDate().getYear() == year
						&& p.getReceiptDate().getMonthValue() == (LocalDate.now().minusMonths(1).getMonthValue()))
				.collect(Collectors.toList());

		Double totalLastMonthReceived = 0.0;
		for (AarthikSahyogEntity shyogEntity : lastMonthReceived) {
			// System.out.println(expenditureEntity.getExpdDate());
			totalLastMonthReceived += shyogEntity.getAmount();

		}



		List<AarthikSahyogAnnouncementEntity> findAllAnn = arthikSahyogAnnouncementRepo
				.findByIsActiveOrderByAnnounceAmountDesc('Y');
		totalAnnounce = 0.0;
		for (AarthikSahyogAnnouncementEntity aarthikSahyogAnnouncementEntity : findAllAnn) {
			totalAnnounce += aarthikSahyogAnnouncementEntity.getAnnounceAmount();
		}


		List<ExpenditureEntity> findAllExpd = expenditureRepo.findByIsActiveOrderByExpdAmountDesc('Y');
		Double totalExpd = 0.0;
		for (ExpenditureEntity expenditureEntity : findAllExpd) {

			totalExpd += expenditureEntity.getExpdAmount();
		}

		List<ExpenditureEntity> findAllExpnd = expenditureRepo.findByIsActiveOrderByExpdAmountDesc('Y');

		if (LocalDate.now().minusMonths(1).getMonthValue() == 12) {
			year = LocalDate.now().minusYears(1).getYear();
		} else {
			year = LocalDate.now().getYear();
		}
		List<ExpenditureEntity> lastMonthExpd = findAllExpnd.stream()
				.filter(p -> p.getExpdDate().getYear() == year
						&& p.getExpdDate().getMonthValue() == (LocalDate.now().minusMonths(1).getMonthValue()))
				.collect(Collectors.toList());
		Double totalExpndLastMonth = 0.0;
		for (ExpenditureEntity expenditureEntity : lastMonthExpd) {
			// System.out.println(expenditureEntity.getExpdDate());
			totalExpndLastMonth += expenditureEntity.getExpdAmount();

		}


		//get top ten

		List<AarthikSahyogAnnouncementEntity> findAllSahyogi = arthikSahyogAnnouncementRepo.findByIsActive('Y');
		// int i = 0;
		for (AarthikSahyogAnnouncementEntity aarthikSahyogAnnouncementEntity : findAllSahyogi) {
			totalTopTen = 0;
			aarthikSahyogAnnouncementEntity.getAarthikSahyogEntity().forEach(e -> {
				totalTopTen += e.getAmount();
			});
			// i++;
			// aarthikSahyogAnnouncementEntity.setPendingAmount(aarthikSahyogAnnouncementEntity.getAnnounceAmount()-total);
			// aarthikSahyogAnnouncementEntity.setSrNo(i);
			aarthikSahyogAnnouncementEntity.setGrandTotal(totalTopTen);
		}

		findAll.sort((AarthikSahyogAnnouncementEntity a1, AarthikSahyogAnnouncementEntity a2) -> a2.getGrandTotal()
				.compareTo(a1.getGrandTotal()));
		List<AarthikSahyogAnnouncementEntity> topTen = new ArrayList<>();
		int i;
		for (i = 0; i < 8; i++) {
			AarthikSahyogAnnouncementEntity obj = new AarthikSahyogAnnouncementEntity();
			obj = findAll.get(i);
			//String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
			topTen.add(obj);

		}

		//get visitors
List<VisitorEntity> findByIpAddress = visitorRepo.findByIpAddress(Utilities.getClientIp(request));

		if(findByIpAddress!=null)
		{

		}
		else
		{
			VisitorEntity visitorObj = new VisitorEntity();
			visitorObj.setIpAddress(Utilities.getClientIp(request));
			visitorRepo.save(visitorObj);
			visitorObj=null;
		}



	 long count = visitorRepo.count();
       HttpSession session =  request.getSession();
		session.setAttribute("topTen",topTen);
		session.setAttribute("totalReceived",totalReceived);
		session.setAttribute("totalLastMonthReceived",totalLastMonthReceived);
		session.setAttribute("totalExpndLastMonth",totalExpndLastMonth);
		session.setAttribute("totalAnnounce",totalAnnounce);
		session.setAttribute("totalExpd",totalExpd);
		session.setAttribute("visitors",count);

	}
	public void setAarthikSahyogAnnouncementSession()
	{
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

		long count = aarthikSahyogRepo.count();


		 HttpSession session =  request.getSession();
			session.setAttribute("allAnnouncement",findAll);
			session.setAttribute("totalReceipt",count);
	}

	public void setGairAarthikSahyogSession()
	{
		List<GairAarthikSahyogEntity> findAll = gairAarthikRepo.findByIsActiveOrderByApproxCostDesc('Y');
		int i = 0;
		for (GairAarthikSahyogEntity gairAarthikSahyogEntity : findAll) {
			i++;
			gairAarthikSahyogEntity.setSrNo(i);
		}


		HttpSession session =  request.getSession();
		session.setAttribute("allGairAarthik",findAll);
	}

	public void setKharchaSession() {
		List<ExpenditureEntity> findAll = expenditureRepo.findByIsActiveOrderByExpdAmountDesc('Y');
		int i = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		for (ExpenditureEntity expenditureEntity : findAll) {
			i++;
			expenditureEntity.setExpdStringDate(expenditureEntity.getExpdDate().format(formatter));
			expenditureEntity.setSrNo(i);
		}
		HttpSession session =  request.getSession();
		session.setAttribute("allKharcha",findAll);
	}
	public void setsamitiMemberSession()
	{

			 List<SamitiMemberEntity> findAllByOrderByMemberPriority = samitiMemberRepo.findAllByOrderByMemberPriority();
			 HttpSession session =  request.getSession();
				session.setAttribute("samitiMember",findAllByOrderByMemberPriority);

	}


}
