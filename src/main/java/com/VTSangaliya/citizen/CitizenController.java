package com.VTSangaliya.citizen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementEntity;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementRepo;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogEntity;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogRepo;
import com.VTSangaliya.expenditure.ExpenditureCatEntity;
import com.VTSangaliya.expenditure.ExpenditureCatRepo;
import com.VTSangaliya.expenditure.ExpenditureEntity;
import com.VTSangaliya.expenditure.ExpenditureRepo;
import com.VTSangaliya.messages.MessageService;
import com.VTSangaliya.samitiMember.SamitiMemberRepo;

@Controller
//@RequestMapping("citizen")
public class CitizenController {
	@Autowired
	private AarthikSahyogAnnouncementRepo arthikSahyogAnnouncementRepo;
	@Autowired
	private AarthikSahyogRepo aarthikSahyogRepo;
	@Autowired
	private MessageService messageService;

	Double totalLastMonthReceived = 0.0;
	int pendingAmount = 0;
	Double totalReceived = 0.0;
	Double totalExpndLastMonth = 0.0;
	Double totalAnnounce = 0.0;
	Double totalExpd = 0.0;
	int totalTopTen;
	int year = 0;
	@Autowired
	private ExpenditureCatRepo expenditureCatRepo;
	@Autowired
	private ExpenditureRepo expenditureRepo;
	@Autowired
	private com.VTSangaliya.common.DownloadFile downloadFile;

	@Autowired
	private SamitiMemberRepo samitiMemberRepo;
	
	@Autowired
	private SessionServices sessionServices;
	
	@RequestMapping("/")
	public ModelAndView home(HttpSession session)
	{
		//return "citizen/home";
		//System.out.println("total announcement"+session.getAttribute("totalAnnounce"));
		if(session.getAttribute("topTen")==null)
		{
			sessionServices.setSession();
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("citizen/pages/home");
		//String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		
		List<AarthikSahyogAnnouncementEntity> topTen=(List<AarthikSahyogAnnouncementEntity>) session.getAttribute("topTen");
		topTen.forEach(e->{
			if(e.getPhoto()!=null)
			{
			e.setEncodedString(Base64.getEncoder().encodeToString(e.getPhoto()));
			
			}
		});
		mv.addObject("topTen",topTen );
		mv.addObject("totalReceived",session.getAttribute("totalReceived") );
		mv.addObject("totalLastMonthReceived",session.getAttribute("totalLastMonthReceived") );
		mv.addObject("totalExpndLastMonth", session.getAttribute("totalExpndLastMonth"));
		
		mv.addObject("totalAnnounce", session.getAttribute("totalAnnounce"));
		mv.addObject("totalExpd", session.getAttribute("totalExpd"));
		mv.addObject("visitors", session.getAttribute("visitors"));
		return mv;
	}
	
	@GetMapping("/showAarthikSahyog")
	public ModelAndView showAarthikSahyog(HttpSession session) {
		if(session.getAttribute("allAnnouncement")==null)
		{
			sessionServices.setAarthikSahyogAnnouncementSession();
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("citizen/pages/aarthikSahyog");
		mv.addObject("totalReceived",session.getAttribute("totalReceived") );
		mv.addObject("totalAnnounce", session.getAttribute("totalAnnounce"));
		mv.addObject("totalExpd", session.getAttribute("totalExpd"));
		mv.addObject("allAnnouncement",session.getAttribute("allAnnouncement") );
		mv.addObject("totalReceipt",session.getAttribute("totalReceipt") );
		return mv;
	}
	@RequestMapping("/getAllArthikSahyogAnnounceByMobileNo")
	public @ResponseBody List<AarthikSahyogAnnouncementEntity> getAllArthikSahyogAnnounceByMobile(
			@RequestBody String mobile) {
		// AarthikSahyogAnnouncementEntity arthikSahyogAnnouncementEntity = new
		// AarthikSahyogAnnouncementEntity();
		// arthikSahyogAnnouncementEntity(json.get("catId"));
		 System.out.println("mobile no"+mobile);
		List<AarthikSahyogAnnouncementEntity> findAllByMobile = arthikSahyogAnnouncementRepo
				.findByMobile(mobile);
		return findAllByMobile;
	}
	
	@GetMapping("/showGairAarthikSahyog")
	public ModelAndView showGairAarthikSahyog(HttpSession session) {
		
		if(session.getAttribute("allGairAarthik")==null)
		{
			sessionServices.setGairAarthikSahyogSession();
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("citizen/pages/gair-aarthikSahyog");
		
		mv.addObject("allGairAarthik",session.getAttribute("allGairAarthik") );
		
		return mv;
		
		
	}
	
	@GetMapping("/showSamitiMember")
	public ModelAndView showSamitiMember(HttpSession session) {
		
		if(session.getAttribute("samitiMember")==null)
		{
			sessionServices.setsamitiMemberSession();
		}
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("citizen/pages/samiti-member-page");
		
		mv.addObject("samitiMember",session.getAttribute("samitiMember") );
		
		return mv;
	}
	
	
	
	@GetMapping("/showUdeshy")
	public ModelAndView showUdeshy() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("citizen/pages/samiti-udeshy");
		return mv;
	}
	
	@GetMapping("/showKharcha")
	public ModelAndView showKharcha(HttpSession session) {
		
		if(session.getAttribute("kharcha")==null)
		{
			sessionServices.setKharchaSession();
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("citizen/pages/kharcha-page");
		List<ExpenditureCatEntity> allcat = expenditureCatRepo.findAll();
		mv.addObject("allCat", allcat) ;
		session.setAttribute("allCat", allcat);
		mv.addObject("allKharcha",session.getAttribute("allKharcha") );
		
		return mv;
		
	}
	
	@RequestMapping("/getExpenditureByCategory")
	public ModelAndView getExpenditureByCatId(@RequestParam(value = "catId")Integer catId,HttpSession session) {
		ExpenditureCatEntity expenditureCatEntity = new ExpenditureCatEntity();
		expenditureCatEntity.setCatId(catId);
		ModelAndView mv = new ModelAndView();
		if(catId==0)
		{
			mv.setViewName("redirect:/showKharcha");
		}
		
		else
		{
		List<ExpenditureEntity> findAllByExpenditureCatEntity = expenditureRepo
				.findAllByExpenditureCatEntity(expenditureCatEntity);
		int i = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		for (ExpenditureEntity expenditureEntity : findAllByExpenditureCatEntity) {
			i++;
			expenditureEntity.setExpdStringDate(expenditureEntity.getExpdDate().format(formatter));
			expenditureEntity.setSrNo(i);
		}
		mv.setViewName("citizen/pages/kharcha-page");
		mv.addObject("allCat", session.getAttribute("allCat")) ;
		
		mv.addObject("allKharcha",findAllByExpenditureCatEntity );
		}
		return mv;
		
	}
	
}
