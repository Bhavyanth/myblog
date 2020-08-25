package com.blog.MyBlog.exceptions;

public class SubPostNotFoundException extends RuntimeException {
  
	private static final long serialVersionUID = 1L;

	public SubPostNotFoundException(String message) {
        super(message);
    }
}
