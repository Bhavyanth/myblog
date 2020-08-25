package com.blog.MyBlog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blog.MyBlog.dto.RegisterRequest;
import com.blog.MyBlog.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	
	private AuthService authService;
	
	@RequestMapping (value = "/signup", method= RequestMethod.POST)
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
		authService.signUp(registerRequest);
		return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
	}
	
	@RequestMapping(value = "accountVerification/{token}", method = RequestMethod.GET )
	public ResponseEntity<String> verifyAccount(@PathVariable String token){
		 authService.verifyAccount(token);
		 return new ResponseEntity<String>("Account activation Success", HttpStatus.OK);
	}
	
}
