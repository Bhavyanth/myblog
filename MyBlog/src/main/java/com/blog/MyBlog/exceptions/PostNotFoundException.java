package com.blog.MyBlog.exceptions;

public class PostNotFoundException extends RuntimeException {
   
	private static final long serialVersionUID = 1L;

	public PostNotFoundException(String message) {
    	System.out.println(message.trim().toString());
    	
    }
}
