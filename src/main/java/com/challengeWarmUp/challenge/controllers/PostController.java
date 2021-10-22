package com.challengeWarmUp.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.challengeWarmUp.challenge.daoImp.Post;
import com.challengeWarmUp.challenge.validations.Image;

@RestController
public class PostController {
	
	@Autowired
	private Post post;
	@Autowired
	private com.challengeWarmUp.challenge.daoImp.User user;
	
	private Long getSessionUserId() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(user.getUsername().equals("admin"))
			return (long) 0;
		
		return this.user.findByEmail(user.getUsername()).getId();
	}
	
	@GetMapping(value="/posts")
	public List<com.challengeWarmUp.challenge.dto.Post> getAllOrderByDate(){
		return post.getOrderByDate();
	}
	
	@GetMapping(value="/posts", params="title")
	public List<com.challengeWarmUp.challenge.dto.Post> getByTitle(@RequestParam String title){
		return post.getByTitle(title);
	}
	
	@GetMapping(value="/posts", params="category")
	public List<com.challengeWarmUp.challenge.dto.Post> getByCategory(@RequestParam String category){
		return post.getByCategory(category);
	}
	
	@GetMapping(value="/posts", params={"title", "category"})
	public List<com.challengeWarmUp.challenge.dto.Post> getByTitleAndCategory(@RequestParam String title, @RequestParam String category){
		return post.getByTitleAndCategory(title, category);
	}
	
	@GetMapping(value="/posts/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		com.challengeWarmUp.challenge.dto.Post post = this.post.getById(id);

		if(post == null)
			return ResponseEntity.badRequest().body("id not found");
		
		return ResponseEntity.ok(post);
	}
	
	@RequestMapping(path = "/posts", method=RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public @ResponseBody ResponseEntity<?> create(@RequestPart(value="post") com.challengeWarmUp.challenge.models.Post post, @RequestPart(value="image", required=false) MultipartFile image){
		
		if(post == null || post.getTitle() == null || post.getTitle().isEmpty()) 
			return ResponseEntity.badRequest().body("Field title cannot be null or empty");
		
		String imageName = null;
		if(image != null) {
			imageName = image.getOriginalFilename();
			
			if(Image.imageExtension(imageName) == false)
				return ResponseEntity.badRequest().body("Unsupported image extension");
		}
		
		post.setImage(imageName);
		post.setDeleted(false);
		post.setIdUser(getSessionUserId());
		return ResponseEntity.ok(this.post.create(post));
	}
	
	@DeleteMapping(value="/posts/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id){
		if(post.remove(id) == false)
			return ResponseEntity.badRequest().body("Post with id "+ id +" not found");
		
		return ResponseEntity.ok("Post with id "+ id + " removed");
	}
	
	@PatchMapping(value="/posts/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public @ResponseBody ResponseEntity<?> update(@PathVariable Long id, @RequestPart(value="post") com.challengeWarmUp.challenge.models.Post post, @RequestPart(value="image", required=false) MultipartFile image){
		
		if(user.findById(post.getIdUser()) == null)
			return ResponseEntity.badRequest().body("Id User not found");
		
		String imageName = null;
		if(image != null) {
			imageName = image.getOriginalFilename();
			
			if(Image.imageExtension(imageName) == false)
				return ResponseEntity.badRequest().body("Unsupported image extension");
		}
		
		post.setImage(imageName);
		return this.post.update(id, post) == true ? ResponseEntity.ok("Post has been updated") : ResponseEntity.badRequest().body("Post with id "+ id +" not found");
	}
	
}