package com.VTSangaliya.Admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementEntity;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementRepo;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogRepo;
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
	int total = 0;
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
	public ModelAndView adminShowAarthikSahyog()
	{
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
		 ;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/admin-pages/adminAarthikSahyog");
		mv.addObject("allAnnouncement", findAll);
		return mv;
		
	}
	}

