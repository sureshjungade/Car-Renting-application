package com.masai.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

//Global exception handler class responsible for handling exceptions thrown within the application.
@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(DetailNotFoundException.class)
	public ResponseEntity<MyErrorDetails> empNotFoundHandler(DetailNotFoundException ex, WebRequest wr){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(),ex.getMessage(),wr.getDescription(false));
		
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerFoundException(NoHandlerFoundException ex, WebRequest wr){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(),ex.getMessage(),wr.getDescription(false));
		
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> exception(Exception ex, WebRequest wr){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(),ex.getMessage(),wr.getDescription(false));
		
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}
}
