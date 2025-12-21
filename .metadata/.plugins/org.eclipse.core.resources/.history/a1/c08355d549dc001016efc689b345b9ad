package com.example_Backend.endpoint;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example_Backend.entity.BlogPostEntity;
import com.example_Backend.entity.UserEntity;
import com.example_Backend.repository.FeignClientconfig;

@Controller
public class maincontroller {
	
	@Autowired
	FeignClientconfig backendserver;
	
	@GetMapping("/")
	public String homepage() {
		return "index";
	}
	
	@GetMapping("/blogpage")
	public String blogpage(Principal principal,Model mo ) {
		String email=principal.getName();
		UserEntity user=this.backendserver.getuserbyusername(email);
	   
		mo.addAttribute("userdetail", user);
	  List<BlogPostEntity>allblog=this.backendserver.findallblog();
		mo.addAttribute("allblog",allblog);
		return "blog";
	}
	
	@GetMapping("/kisanchatbot")
	public String kisanchatbot() {
		return "multilingualchatbot";
	}
	
	
	@GetMapping("/newpage")
	public String newpageblog(@ModelAttribute BlogPostEntity blog, Principal principal) {
		BlogPostEntity blogs=this.backendserver.createBlog(blog, principal.getName());
		return "redirect:/kisanchatbot";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return  "signup";
	}
	
	
	
	
	

}
