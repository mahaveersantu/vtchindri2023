package com.VTSangaliya.Admin;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementEntity;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementRepo;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogRepo;
import com.VTSangaliya.citizen.SessionServices;
import com.VTSangaliya.expenditure.ExpenditureCatEntity;
import com.VTSangaliya.expenditure.ExpenditureCatRepo;
import com.VTSangaliya.messages.MessageService;
import com.VTSangaliya.user.UserEntity;
import com.VTSangaliya.user.UserRepo;

@Controller
//@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AarthikSahyogAnnouncementRepo arthikSahyogAnnouncementRepo;
	@Autowired
	private AarthikSahyogRepo aarthikSahyogRepo;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ExpenditureCatRepo expenditureCatRepo;
	int total = 0;
	@Autowired
	private AdminSessionServices sessionServices;
	@RequestMapping("/adminLogin")
	public ModelAndView home(HttpSession session)
	{
		//return "citizen/home";
		//System.out.println("total announcement"+session.getAttribute("totalAnnounce"));
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/admin-pages/login");
	
		return mv;
	}
	@RequestMapping("/adminHome")
	public ModelAndView adminHome(HttpSession session)
	{
		//return "citizen/home";
		//System.out.println("total announcement"+session.getAttribute("totalAnnounce"));
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/admin-pages/admin-home");
	
		return mv;
	}
	
	@PostMapping("/adminAuthentication")
	public ModelAndView userAuthentication(UserEntity userEntity,HttpSession session)
	{
		UserEntity user = new UserEntity();
		user=userRepo.findByUserIdAndUserPassword(userEntity.getUserId(),userEntity.getUserPassword());
		ModelAndView mv = new ModelAndView();
		if(user!=null)
		{
			session.setAttribute("adminSession", user);
			
			mv.setViewName("admin/admin-pages/admin-home");
		}
		
		else
		{
			mv.setViewName("admin/admin-pages/login");
			mv.addObject("login", "fail");
	}
		return mv;
	}
	
	@RequestMapping("/adminShowAarthikSahyog")
	public ModelAndView adminShowAarthikSahyog(HttpSession session)
	{
		if(session.getAttribute("allAnnouncement")==null)
		{
			sessionServices.setAdminAarthikSahyogAnnouncementSession();
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/admin-pages/adminAarthikSahyog");
		mv.addObject("allAnnouncement",session.getAttribute("allAnnouncement"));
		return mv;
		
	}
	
	@RequestMapping("/adminAllReceipt")
	public ModelAndView adminAllReceipt(HttpSession session)
	{
		if(session.getAttribute("allReceipt")==null)
		{
			sessionServices.setAdminAarthikSahyogAllReceiptSession();
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/admin-pages/adminAllReceipt");
		mv.addObject("allReceipt",session.getAttribute("allReceipt"));
		return mv;
		
	}
	
	@PostMapping("/saveAndUpdateArthikSahyog")
	public ModelAndView saveAndUpdateArthikSahyog(@RequestParam(required = false, value="sahyogkrta_photo") MultipartFile sahyogkrta_photo,AarthikSahyogAnnouncementEntity arthikSahyogAnnouncementEntity,
			 HttpSession session) throws IOException {
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
				System.out.println("photo size ");
				System.out.println("photo size "+sahyogkrta_photo);
				if(sahyogkrta_photo!=null)
				{
					if (sahyogkrta_photo.getContentType().contains("jpg") || sahyogkrta_photo.getContentType().contains("jpeg"))
					  {
					arthikSahyogAnnouncementEntity.setPhoto(sahyogkrta_photo.getBytes());
					  }
					else {
						  status="invalid_photo";  
					  }
				}
				
				
				saved = arthikSahyogAnnouncementRepo.save(arthikSahyogAnnouncementEntity);
				status = "success";
			}

		} else {
			List<AarthikSahyogAnnouncementEntity> getData = arthikSahyogAnnouncementRepo
					.findByMobile(arthikSahyogAnnouncementEntity.getMobile());
			if (getData.size() == 0) {
				arthikSahyogAnnouncementEntity.setAddedOn(LocalDate.now());

				System.out.println("size of photo is "+sahyogkrta_photo.getSize());
				
				  if(sahyogkrta_photo!=null) {
					  if (sahyogkrta_photo.getContentType().contains("jpg") || sahyogkrta_photo.getContentType().contains("jpeg"))
					  {
				  arthikSahyogAnnouncementEntity.setPhoto(sahyogkrta_photo.getBytes());
				  }
					  else {
						  status="invalid_photo";  
					  }
				  }
				 
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
				//messageService.sendMessageToAnnouncement(sahyogkrta, saved.getAnnounceAmount(), saved.getMobile());
				status = "success";
			} else {
				status = "exist";
			}
		}
		if (saved == null) {
			status = "fail";
		}
		if(session.getAttribute("allAnnouncement")==null)
		{
			sessionServices.setAdminAarthikSahyogAnnouncementSession();
		}
		System.out.println("Date Saved");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/admin-pages/adminAarthikSahyog");
		mv.addObject("allAnnouncement",session.getAttribute("allAnnouncement"));
		mv.addObject("status",status);
		return mv;
	}
	
	@RequestMapping("/adminShowAllCat")
	public ModelAndView getAllCategory() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/admin-pages/adminExpenditureCategory");
	    mv.addObject("allCategories", expenditureCatRepo.findAll());
		return mv;
		
	}
	@RequestMapping("/adminSaveAndUpdateCategory")
	public String saveAndUpdateCategory(ExpenditureCatEntity expenditureCatEntity) {
		ExpenditureCatEntity saved = expenditureCatRepo.save(expenditureCatEntity);
		if (saved != null) {
			return "success";
		} else {
			return "fail";
		}

	}
	
	@RequestMapping("/logout")
	public ModelAndView adminLogout(HttpServletRequest request, HttpSession session,Model model)
	{
		
		if(session.getAttribute("adminSession")!=null)
		{
			session.removeAttribute("adminSession");
		}
		request.getSession().invalidate();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/admin-pages/login");
	
		return mv;
		
	}
	}

