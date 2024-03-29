package com.VTSangaliya.Admin;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementEntity;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementRepo;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogEntity;
import com.VTSangaliya.aarthikSahyog.AarthikSahyogRepo;
import com.VTSangaliya.expenditure.ExpenditureCatEntity;
import com.VTSangaliya.expenditure.ExpenditureCatRepo;
import com.VTSangaliya.expenditure.ExpenditureEntity;
import com.VTSangaliya.expenditure.ExpenditureRepo;
import com.VTSangaliya.gairAarthikSahyog.GairAarthikRepo;
import com.VTSangaliya.gairAarthikSahyog.GairAarthikSahyogEntity;
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
	@Autowired
	private GairAarthikRepo gairAarthikRepo;
	@Autowired
	private ExpenditureRepo expenditureRepo;
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
	public ModelAndView saveAndUpdateArthikSahyog(@RequestParam(required = false, value="sahyogkrta_photo") MultipartFile sahyogkrta_photo,
			AarthikSahyogAnnouncementEntity arthikSahyogAnnouncementEntity,
			 HttpSession session) throws IOException {
		String status;
		System.out.println("saveAndUpdate");
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
				arthikSahyogAnnouncementEntity.setIsActive('Y');
				//System.out.println("photo size ");
				//System.out.println("photo size "+sahyogkrta_photo.getName());
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
				arthikSahyogAnnouncementEntity.setIsActive('Y');

				//System.out.println("size of photo is "+sahyogkrta_photo.getSize());

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
		//System.out.println("Date Saved");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/adminShowAarthikSahyog");
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
	public ModelAndView saveAndUpdateCategory(ExpenditureCatEntity expenditureCatEntity,RedirectAttributes rd) {
		ExpenditureCatEntity saved = expenditureCatRepo.save(expenditureCatEntity);
		String status;
		if (saved != null) {
			status= "success";
		} else {
			status= "fail";
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/adminShowAllCat");
		//mv.addObject("allAnnouncement",session.getAttribute("allAnnouncement"));

		rd.addAttribute("status",status);
		return mv;

	}

	@RequestMapping("/AdminSaveAndUpdateArthikSahyogReceipt")
	public ModelAndView AdminSaveAndUpdateArthikSahyogReceipt( AarthikSahyogEntity arthikSahyogEntity,
			@RequestParam("Date") String date,RedirectAttributes rd) {
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
				//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");


				  //convert String to LocalDate

				arthikSahyogEntity.setReceiptDate(LocalDate.parse(date));
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
				arthikSahyogEntity.setReceiptDate(LocalDate.parse(date));
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
				//System.out.println("message send to length" + sahyogkrta.length());
				//System.out.println("message send to" + sahyogkrta);
				/*
				 * messageService.sendMessageToAarthikSahyog(sahyogkrta,
				 * saved.getAarthikSahyogAnnouncementEntity().getMobile(), saved.getAmount(),
				 * saved.getReceiptNo(), saved.getReceiptDate());
				 */
				status = "success";
			} else {
				status = "exist";
			}
		}
		if (saved == null) {
			status = "fail";
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/adminShowAarthikSahyog");
		//mv.addObject("allAnnouncement",session.getAttribute("allAnnouncement"));

		rd.addAttribute("status",status);
		return mv;
	}
	

	@RequestMapping("/adminRefresh")
	public ModelAndView adminRefresh(HttpSession session)
	{

			sessionServices.setAdminAarthikSahyogAnnouncementSession();
			sessionServices.setAdminAarthikSahyogAllReceiptSession();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/adminShowAarthikSahyog");
		return mv;

	}
	@RequestMapping("/adminShowGairAarthikSahyog")
	public ModelAndView adminShowGairAarthikSahyog(HttpSession session)
	{
		if(session.getAttribute("allGairAarthik")==null)
		{
			sessionServices.setAdminallGairAarthikSession();
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/admin-pages/adminGairAarthikSahyog");
		mv.addObject("allGairAarthik",session.getAttribute("allGairAarthik"));
		return mv;

	}

	@PostMapping("/adminSaveAndUpdateGairAarthikSahyog")
	public ModelAndView adminSaveAndUpdateGairAarthikSahyog(GairAarthikSahyogEntity gairAarthikSahyogEntity,RedirectAttributes rd) {
		GairAarthikSahyogEntity saved = gairAarthikRepo.save(gairAarthikSahyogEntity);
		String status;
		if (saved != null) {
			status = "success";
		} else {
			status = "fail";
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/adminShowGairAarthikSahyog");
		//mv.addObject("allAnnouncement",session.getAttribute("allAnnouncement"));

		rd.addAttribute("status",status);
		return mv;
	}


	@RequestMapping("/adminRefreshGairAarthik")
	public ModelAndView adminRefreshGairAarthik(HttpSession session)
	{

			sessionServices.setAdminallGairAarthikSession();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/adminShowGairAarthikSahyog");
		mv.addObject("allGairAarthik",session.getAttribute("allGairAarthik"));
		return mv;

	}

	@RequestMapping("/adminAllExpenditures")
	public ModelAndView getAllExpenditures(HttpSession session) {
		if(session.getAttribute("allExpenditure")==null)
		{
			sessionServices.setAdminallExpenditure();
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/admin-pages/adminExpenditure");
		//System.out.println("get expd session = "+ session.getAttribute("allExpenditure").size());
		mv.addObject("allExpenditure",session.getAttribute("allExpenditure"));
		mv.addObject("allCategories", expenditureCatRepo.findAll());
		
		return mv;
	}
	
	
	@PostMapping("/adminSaveAndUpdateExpenditure")
	public ModelAndView adminSaveAndUpdateExpenditure(ExpenditureEntity expenditureEntity,RedirectAttributes rd,
			@RequestParam("catId") int catId,
			@RequestParam("Date") String date) {
		
		ExpenditureCatEntity expenditureCatEntity= new ExpenditureCatEntity();
		expenditureCatEntity.setCatId(catId);
		expenditureEntity.setExpenditureCatEntity(expenditureCatEntity);
		expenditureEntity.setExpdDate(LocalDate.parse(date));
		ExpenditureEntity saved = expenditureRepo.save(expenditureEntity);
		
		String status;
		if (saved != null) {
			status = "success";
		} else {
			status = "fail";
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/adminAllExpenditures");
     	rd.addAttribute("status",status);
		return mv;
	}
	@RequestMapping("/adminRefreshExpenditure")
	public ModelAndView adminRefreshExpenditure(HttpSession session)
	{

			sessionServices.setAdminallExpenditure();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/adminAllExpenditures");
		mv.addObject("allExpenditure",session.getAttribute("allExpenditure"));
		mv.addObject("allCategories", expenditureCatRepo.findAll());
		return mv;

	}
	
	@RequestMapping("/AdminDeleteSahyogReceipt/{id}")
	public ModelAndView AdminDeleteSahyogReceipt(@PathVariable int id, RedirectAttributes rd) {
		aarthikSahyogRepo.deleteById(id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/adminShowAarthikSahyog");
		rd.addAttribute("status","success");
		return mv;
	}
	
	@GetMapping("/adminShowSamitiMember")
	public ModelAndView adminShowSamitiMember(HttpSession session) {

		if(session.getAttribute("samitiMember")==null)
		{
			sessionServices.setsamitiMemberSession();
		}


		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/admin-pages/admin-samiti-member-page");

		mv.addObject("samitiMember",session.getAttribute("samitiMember") );

		return mv;
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

