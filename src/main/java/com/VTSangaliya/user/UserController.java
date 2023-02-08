package com.VTSangaliya.user;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.VTSangaliya.common.Utilities;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private VisitorRepo visitorRepo;
	@Autowired
	private TotalVisitorRepo totalVisitorRepo;
	@PostMapping("/userAuthentication")
	public String userAuthentication(@RequestBody UserEntity userEntity,HttpSession session)
	{
		UserEntity user = new UserEntity();
		user=userRepo.findByUserIdAndUserPassword(userEntity.getUserId(),userEntity.getUserPassword());
		if(user!=null)
		{
			session.setAttribute("adminSession", user);
			return "success";
		}
		
		else
			return "fail";
	}
	/*
	 * @RequestMapping("/logout") public String adminLogout(HttpServletRequest
	 * request, HttpSession session,Model model) {
	 * 
	 * if(session.getAttribute("adminSession")!=null) {
	 * session.removeAttribute("adminSession"); } request.getSession().invalidate();
	 * 
	 * return "success"; }
	 */
	
	@RequestMapping("/adminDetails")
	public UserEntity adminDetails(HttpServletRequest request, HttpSession session)
	{
		UserEntity user = new UserEntity();
		if(session.getAttribute("adminSession")!=null)
		{
			user = (UserEntity)session.getAttribute("adminSession");
		}
		
		
		return user;
	}
	
	
	@RequestMapping("/saveIpAddress")
	public String saveIpAddress(@RequestBody VisitorEntity visitorEntity)
	{
		//VisitorEntity visitor = new VisitorEntity();
		List<VisitorEntity> visitor = visitorRepo.findByIpAddress(visitorEntity.getIpAddress());
		
		if(visitor!=null)
		{
			
		}
		else
		{
			visitorRepo.save(visitorEntity);
		}
		visitor=null;
		return "success";
	}
	
	@RequestMapping("/totalUniqueVisitors")
	public long totalVisitors(HttpServletRequest req) throws UnknownHostException
	{
	//System.out.println("ip address is"+ Utilities.getClientIp(req));
		List<VisitorEntity> findByIpAddress = visitorRepo.findByIpAddress(Utilities.getClientIp(req));
		
		if(findByIpAddress!=null)
		{
			
		}
		else
		{
			VisitorEntity visitorObj = new VisitorEntity();
			visitorObj.setIpAddress(Utilities.getClientIp(req));
			visitorRepo.save(visitorObj);
			visitorObj=null;
		}
		
	
		
	 long count = visitorRepo.count();
		
				
		return count;
	}
	
	@RequestMapping("/totalVisitor")
	public int totalVisitor() 
	{
		
		
		//TotalVisitorsEntity visitorObj = new TotalVisitorsEntity();
		//visitorObj.setIpAddress(Utilities.getClientIp(req));
		//System.out.println("ip address is"+ Utilities.getClientIp(req));
		TotalVisitorsEntity total = totalVisitorRepo.findAll().get(0);
		total.setTotalVisitors(total.getTotalVisitors()+1);
		TotalVisitorsEntity save = totalVisitorRepo.save(total);
		
		total=null;
				
		return save.getTotalVisitors();
	}
	
	
	
	

}
