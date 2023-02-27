package com.VTSangaliya.Admin;

import java.util.Collections;
import java.util.Comparator;
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
import com.VTSangaliya.expenditure.ExpenditureCatRepo;
import com.VTSangaliya.expenditure.ExpenditureEntity;
import com.VTSangaliya.expenditure.ExpenditureRepo;
import com.VTSangaliya.gairAarthikSahyog.GairAarthikRepo;
import com.VTSangaliya.gairAarthikSahyog.GairAarthikSahyogEntity;
import com.VTSangaliya.messages.MessageService;
import com.VTSangaliya.samitiMember.SamitiMemberRepo;
import com.VTSangaliya.user.TotalVisitorRepo;
import com.VTSangaliya.user.UserRepo;
import com.VTSangaliya.user.VisitorRepo;

@Service
public class AdminSessionServices {

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
	
	public void setAdminAarthikSahyogAnnouncementSession()
	{
		List<AarthikSahyogAnnouncementEntity> findAll = arthikSahyogAnnouncementRepo.findByOrderByAddedOn();
		int i = 0;
		for (AarthikSahyogAnnouncementEntity aarthikSahyogAnnouncementEntity : findAll) {
			total = 0;
if(aarthikSahyogAnnouncementEntity.getAarthikSahyogEntity()!=null)
{
			aarthikSahyogAnnouncementEntity.getAarthikSahyogEntity().forEach(e -> {
				total += e.getAmount();
			});
}
			i++;

			aarthikSahyogAnnouncementEntity
					.setPendingAmount(aarthikSahyogAnnouncementEntity.getAnnounceAmount() - total);
			aarthikSahyogAnnouncementEntity.setSrNo(i);
			aarthikSahyogAnnouncementEntity.setGrandTotal(total);
		}
		 
		
		
		 HttpSession session =  request.getSession();
			session.setAttribute("allAnnouncement",findAll);
			
	}
	
	public void setAdminAarthikSahyogAllReceiptSession()
	{
		List<AarthikSahyogEntity> findAll = aarthikSahyogRepo.findAllByOrderByReceiptNo();
		
		List<AarthikSahyogEntity> findAllSort = findAll
		        .stream()
		        .sorted(Comparator.comparing(AarthikSahyogEntity ::getReceiptNo))
		        .collect(Collectors.toList());
		 HttpSession session =  request.getSession();
			session.setAttribute("allReceipt",findAllSort);
			
	}

	public void setAdminallGairAarthikSession() {
		List<GairAarthikSahyogEntity> findAll = gairAarthikRepo.findAll();
		
		HttpSession session =  request.getSession();
		session.setAttribute("allGairAarthik",findAll);
		
	}
	public void setAdminallExpenditure() {
		List<ExpenditureEntity> findAll = expenditureRepo.findAllByOrderByExpdReceiptNo();
		
		HttpSession session =  request.getSession();
		session.setAttribute("allExpenditure",findAll);
		
	}
}
