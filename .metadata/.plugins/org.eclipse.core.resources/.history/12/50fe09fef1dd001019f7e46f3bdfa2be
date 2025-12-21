package com.example_Backend.repository;



import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example_Backend.entity.BlogPostEntity;
import com.example_Backend.entity.UserEntity;



@FeignClient(name = "smart-crop-advisory-backend" , url = "http://localhost:8082")

public interface FeignClientconfig {
	//User
	@GetMapping("/User/demo")
	public String demo();
	
	
	@GetMapping("/User/getuserbyusername")
	public UserEntity getuserbyusername(@RequestParam("email") String email);
	
	
	
	//blog
	@PostMapping("/blog/createBlog")
	public BlogPostEntity createBlog(@RequestBody BlogPostEntity blog ,@RequestParam("email") String email);
	
	
	@GetMapping("/blog")
	public List<BlogPostEntity> findallblog();
	
	
}
