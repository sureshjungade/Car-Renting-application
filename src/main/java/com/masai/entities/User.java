package com.masai.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Entity class representing a user in the application.
@Data
public class User implements Serializable{
	
	private Integer id;
	private String username;
	private String password;
	private String email;
}
