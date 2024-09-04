package com.softpro.myapp.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import javax.print.DocFlavor.INPUT_STREAM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softpro.myapp.dao.ProductDao;
import com.softpro.myapp.model.Customer;
import com.softpro.myapp.model.Enquiry;
import com.softpro.myapp.model.Product;
import com.softpro.myapp.model.Response;
import com.softpro.myapp.service.CustomerRepository;
import com.softpro.myapp.service.EnquiryRepository;
import com.softpro.myapp.service.ProductRepository;
import com.softpro.myapp.service.ResponseRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	CustomerRepository crepo;
	
	@Autowired
	EnquiryRepository erepo;
	
	@Autowired
	ResponseRepository rrepo;
	
	@Autowired
	ProductRepository prepo;
	
	@GetMapping("/")
	public String showAdminHome(HttpSession session,Model model, HttpServletResponse response) {
		
		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("adminid")!=null) {				
				model.addAttribute("adminid",session.getAttribute("adminid").toString());
				int ccount=(int)crepo.count();
				int ecount=(int)erepo.count();
				int pcount=(int)prepo.count();
				int rcount=(int)rrepo.count();
				
				model.addAttribute("ccount" , ccount);
				model.addAttribute("ecount" , ecount);
				model.addAttribute("pcount" , pcount);
				model.addAttribute("rcount" , rcount);
				
				return "admin/adminhome";
			}
			else {
				return "redirect:/adminlogin";
			}
			
		}
		catch(Exception ex) {
			return "redirect:/adminlogin";
		}
	}
	
	@GetMapping("/viewcustomers")
	public String showCustomers(HttpSession session,Model model, HttpServletResponse response) {
		
		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("adminid")!=null) {
				model.addAttribute("adminid",session.getAttribute("adminid").toString());
				List<Customer> clist = crepo.findAll();
				model.addAttribute("clist",clist);
				return "admin/viewcustomers";
			}
			else {
				return "redirect:/adminlogin";
			}
			
		}
		catch(Exception ex) {
			return "redirect:/adminlogin";
		}
	}

	@GetMapping("/viewcustomers/delete")
	public String deleteCustomer(@RequestParam String email, HttpSession session, Model model, HttpServletResponse response) {
		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("adminid")!=null) {
				Customer c=crepo.findById(email).get();
				crepo.delete(c);
				model.addAttribute("adminid",session.getAttribute("adminid").toString());
				return "redirect:/admin/viewcustomers";
			}
			else {
				return "redirect:/adminlogin";
			}
		}
		catch(Exception ex) {
			return "redirect:/adminlogin";
		}
		
	}
	
	@GetMapping("/viewenquiries")
	public String showEnquiries(HttpSession session,Model model, HttpServletResponse response) {
		
		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("adminid")!=null) {
				model.addAttribute("adminid",session.getAttribute("adminid").toString());
				List<Enquiry> clist = erepo.findAll();
				model.addAttribute("clist",clist);
				return "admin/viewenquiries";
			}
			else {
				return "redirect:/adminlogin";
			}
			
		}
		catch(Exception ex) {
			return "redirect:/adminlogin";
		}
	}
	
	@GetMapping("/viewenquiries/delete")
	public String deleteEnquiry(@RequestParam long id, HttpSession session, Model model, HttpServletResponse response) {
		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("adminid")!=null) {
				Enquiry e=erepo.findById(id).get();
				erepo.delete(e);
				model.addAttribute("adminid",session.getAttribute("adminid").toString());
				return "redirect:/admin/viewcustomers";
			}
			else {
				return "redirect:/adminlogin";
			}
		}
		catch(Exception ex) {
			return "redirect:/adminlogin";
		}
		
	}
	@GetMapping("/viewfeedbacks")
	public String showFeedbacks(HttpSession session,Model model, HttpServletResponse response) {
		
		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("adminid")!=null) {
				model.addAttribute("adminid",session.getAttribute("adminid").toString());
				List<Response> clist=rrepo.findResponseByResponseType("feedback");
				model.addAttribute("clist", clist);
				return "admin/viewfeedbacks";
			}
			else {
				return "redirect:/adminlogin";
			}
			
		}
		catch(Exception ex) {
			return "redirect:/adminlogin";
		}
	}
	@GetMapping("/viewfeedbacks/delete")
	public String deleteFeedback(@RequestParam int id, HttpSession session, Model model, HttpServletResponse response) {
		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("adminid")!=null) {
				Response r=rrepo.findById(id).get();
				rrepo.delete(r);
				model.addAttribute("adminid",session.getAttribute("adminid").toString());
				return "redirect:/admin/viewfeedbacks";
			}
			else {
				return "redirect:/adminlogin";
			}
		}
		catch(Exception ex) {
			return "redirect:/adminlogin";
		}
		
	}
	@GetMapping("/viewcomplaints")
	public String showComplaints(HttpSession session,Model model, HttpServletResponse response) {
		
		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("adminid")!=null) {
				model.addAttribute("adminid",session.getAttribute("adminid").toString());
				List<Response> clist=rrepo.findResponseByResponseType("complaint");
				model.addAttribute("clist", clist);
				return "admin/viewcomplaints";
			}
			else {
				return "redirect:/adminlogin";
			}
			
		}
		catch(Exception ex) {
			return "redirect:/adminlogin";
		}
	}
	
	@GetMapping("/viewcomplaints/delete")
	public String deleteComplaint(@RequestParam int id, HttpSession session, Model model, HttpServletResponse response) {
		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("adminid")!=null) {
				Response r=rrepo.findById(id).get();
				rrepo.delete(r);
				model.addAttribute("adminid",session.getAttribute("adminid").toString());
				return "redirect:/admin/viewcomplaints";
			}
			else {
				return "redirect:/adminlogin";
			}
		}
		catch(Exception ex) {
			return "redirect:/adminlogin";
		}
		
	}
	@GetMapping("/adminlogout")
	public String adminlogout(HttpSession session, HttpServletResponse response) {
		session.invalidate();
		response.setHeader("cache-control", "no-cache, no-store, must-revalidate");		
		return "redirect:/adminlogin";
	}
	
	@GetMapping("/addproduct")
	public String showAddProduct(HttpSession session,Model model, HttpServletResponse response) {
		
		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("adminid")!=null) {
				model.addAttribute("adminid",session.getAttribute("adminid").toString());
				ProductDao productDao =new ProductDao();
				model.addAttribute("productDao", productDao);
				return "admin/addproduct";
			}
			else {
				return "redirect:/adminlogin";
			}
			
		}
		catch(Exception ex) {
			return "redirect:/adminlogin";
		}
	}
	
	@PostMapping("/addproduct")
	public String createProduct(HttpSession session,Model model, @ModelAttribute ProductDao productDao, RedirectAttributes attrib, HttpServletResponse response) {
		
		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("adminid")!=null) {
				model.addAttribute("adminid",session.getAttribute("adminid").toString());
				// we write here whole code
				MultipartFile image = productDao.getImagefile();
				String storagefilename = new Date().getTime()+ "_" + image.getOriginalFilename();
				String uploadDir="public/images/";
				Path uploadPath=Paths.get(uploadDir);
				if(!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}
				try (InputStream inputStream = image.getInputStream()){
					Files.copy(inputStream,Paths.get(uploadDir + storagefilename),StandardCopyOption.REPLACE_EXISTING);
				}
				Product pd =new Product();
				pd.setProductname(productDao.getProductname());
				pd.setPrice(productDao.getPrice());
				pd.setDescription(productDao.getDescription());
				pd.setFilename(storagefilename);
				prepo.save(pd);
				attrib.addFlashAttribute("message", "product is added");
				return "redirect:/admin/addproduct";
			}
			else {
				return "redirect:/adminlogin";
			}
			
		}
		catch(Exception ex) {
			return "redirect:/adminlogin";
		}
	}
	
	@GetMapping("/viewproducts")
	public String showProducts(HttpSession session,Model model, HttpServletResponse response) {
		
		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("adminid")!=null) {
				model.addAttribute("adminid",session.getAttribute("adminid").toString());
				List<Product> clist=prepo.findAll();
				model.addAttribute("clist",clist);
				return "admin/viewproducts";
			}
			else {
				return "redirect:/adminlogin";
			}
			
		}
		catch(Exception ex) {
			return "redirect:/adminlogin";
		}
	}
	
	@GetMapping("/viewproducts/delete")
	public String deleteProduct(@RequestParam long id, HttpSession session, Model model, HttpServletResponse response) {
		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("adminid")!=null) {
				Product p=prepo.findById(id).get();
				prepo.delete(p);
				model.addAttribute("adminid",session.getAttribute("adminid").toString());
				return "redirect:/admin/viewproducts";
			}
			else {
				return "redirect:/adminlogin";
			}
		}
		catch(Exception ex) {
			return "redirect:/adminlogin";
		}
		
	}
}
