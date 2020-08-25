package com.blog.MyBlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringMainApp {
	public static void main(String[] args) {
		SpringApplication.run(SpringMainApp.class, args);
	}

}
