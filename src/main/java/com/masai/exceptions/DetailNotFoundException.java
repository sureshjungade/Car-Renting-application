package com.masai.exceptions;
//Custom exception class to handle detail not found exceptions.
public class DetailNotFoundException extends RuntimeException{
	public DetailNotFoundException(String msg) {
		super(msg);
	}
}
