package com.masai.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//Custom error details class to encapsulate error information.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyErrorDetails {

	private LocalDateTime dateTime;
	private String messge;
	private String description;

}
