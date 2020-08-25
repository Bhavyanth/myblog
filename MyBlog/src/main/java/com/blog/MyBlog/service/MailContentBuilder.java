package com.blog.MyBlog.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailContentBuilder {
	private final TemplateEngine engine = null;
	String build(String message){
		Context context = new Context();
		context.setVariable("message", message);
		return engine.process("mailTemplate", context);
	}
}
