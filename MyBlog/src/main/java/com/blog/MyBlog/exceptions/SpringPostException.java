package com.blog.MyBlog.exceptions;

public class SpringPostException extends RuntimeException {
  
	private static final long serialVersionUID = 1L;

	public SpringPostException(String exMessage, Exception exception) {
        super(exMessage, exception);
        
    }

    public SpringPostException(String exMessage) {
        super(exMessage);
    }
}
