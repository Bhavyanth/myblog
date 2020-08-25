package com.blog.MyBlog.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.MyBlog.dto.RegisterRequest;
import com.blog.MyBlog.exceptions.SpringPostException;
import com.blog.MyBlog.model.NotificationEmail;
import com.blog.MyBlog.model.User;
import com.blog.MyBlog.model.VerificationToken;
import com.blog.MyBlog.repository.UserRepository;
import com.blog.MyBlog.repository.VerificationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	private VerificationTokenRepository verificationTokenRepo;
	private MailService mailService;
	
	@Transactional
	public void signUp(RegisterRequest registerRequest){
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(false);
		userRepository.save(user);
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(), "Thank you for signing up to Phoenix Blogs, " +
                "Please click on the below link to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token ));
	}
	
	private String generateVerificationToken(User user){
		String verificationToken = UUID.randomUUID().toString();
		VerificationToken token = new VerificationToken();
		token.setToken(verificationToken);
		token.setUser(user);
		verificationTokenRepo.save(token);
		return verificationToken;
	}

	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepo.findByToken(token);
		verificationToken.orElseThrow(() -> new SpringPostException("Invalid Token"));
		fetchUserAndEnable(verificationToken.get());
	}
	
	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		@NotBlank(message = "Username is required")
		String username = verificationToken.getUser().getUsername();
		User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringPostException("User not found: "+username));
		user.setEnabled(true);
		userRepository.save(user);
	}
	
}
