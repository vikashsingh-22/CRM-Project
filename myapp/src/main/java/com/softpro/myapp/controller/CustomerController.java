package com.softpro.myapp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softpro.myapp.dao.ResponseDao;
import com.softpro.myapp.model.Customer;
import com.softpro.myapp.model.Product;
import com.softpro.myapp.model.Response;
import com.softpro.myapp.service.CustomerRepository;
import com.softpro.myapp.service.ProductRepository;
import com.softpro.myapp.service.ResponseRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerRepository crepo;

	@Autowired
	ResponseRepository rrepo;
	
	@Autowired
	ProductRepository prepo;

	@GetMapping("/")
	public String ShowControllerHome(HttpSession session, Model model, HttpServletResponse response) {

		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("userid") != null) {
				Customer c = crepo.getById(session.getAttribute("userid").toString());
				model.addAttribute("name", c.getName());
				model.addAttribute("c",c);
				return "customer/customerhome";
			} else {
				return "redirect:/login";
			}
		} catch (Exception ex) {
			return "redirect:/login";
		}
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		session.invalidate();
		response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.addDateHeader("Expires", 0);
		return "redirect:/login";
	}

	@GetMapping("/response")
	public String showResponse(HttpSession session, Model model, HttpServletResponse response) {

		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("userid") != null) {
				Customer c = crepo.getById(session.getAttribute("userid").toString());
				model.addAttribute("name", c.getName());
				return "customer/response";
			} else {
				return "redirect:/login";
			}
		} catch (Exception ex) {
			return "redirect:/login";
		}
	}
	@PostMapping("/response")
	public String createResponse(HttpSession session, Model model, @ModelAttribute ResponseDao responseDao, RedirectAttributes attrib, HttpServletResponse res) {

		try {
			res.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("userid") != null) {
				Customer c = crepo.getById(session.getAttribute("userid").toString());
				model.addAttribute("name", c.getName());
				Response response = new Response();
				response.setName(c.getName());
				response.setContactno(c.getContactno());
				response.setEmailaddress(c.getEmailaddress());
				response.setResponsetype(responseDao.getResponsetype());
				response.setSubject(responseDao.getSubject());
				response.setMessage(responseDao.getMessage());
				response.setPosteddate(new Date()+"");
				rrepo.save(response);
				attrib.addFlashAttribute("message", "Response is submited");
				return "redirect:/customer/response";
			} else {
				return "redirect:/login";
			}
		} catch (Exception ex) {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/changepassword")
	public String ShowChangePassword(HttpSession session, Model model, HttpServletResponse response) {

		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("userid") != null) {
				Customer c = crepo.getById(session.getAttribute("userid").toString());
				model.addAttribute("name", c.getName());
				return "customer/changepassword";
			} else {
				return "redirect:/login";
			}
		} catch (Exception ex) {
			return "redirect:/login";
		}
	}
	
	@PostMapping("/changepassword")
	public String ChangePassword(HttpSession session, Model model,HttpServletRequest request , RedirectAttributes redirectAttributes, HttpServletResponse response) {

		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("userid") != null) {
				Customer c = crepo.getById(session.getAttribute("userid").toString());
				model.addAttribute("name", c.getName());
				String oldpassword = request.getParameter("oldpassword");
				String newPassword = request.getParameter("newpassword");
				String confirmPassword = request.getParameter("confirmpassword");
				
				if(!newPassword.equals(confirmPassword)) {
					redirectAttributes.addFlashAttribute("message","Newpassword and confirmpassword are not matched");
					return "redirect:/customer/changepassword";
				}
				if(!oldpassword.equals(c.getPassword())) {
					redirectAttributes.addFlashAttribute("message", "oldpassword not match");
					return "redirect:/customer/changepassword";
				}
				c.setPassword(newPassword);
				crepo.save(c);
				return "redirect:/customer/logout";
			} else {
				return "redirect:/login";
			}
		} catch (Exception ex) {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/viewproducts")
	public String ShowProducts(HttpSession session, Model model, HttpServletResponse response) {

		try {
			response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("userid") != null) {
				Customer c = crepo.getById(session.getAttribute("userid").toString());
				model.addAttribute("name", c.getName());
				List<Product> clist = prepo.findAll();
				model.addAttribute("clist",clist);
				return "customer/viewproducts";
			} else {
				return "redirect:/login";
			}
		} catch (Exception ex) {
			return "redirect:/login";
		}
	}

}
