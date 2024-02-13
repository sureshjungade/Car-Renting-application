package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.masai.entities.User;
import com.masai.services.UserService;

//Controller class responsible for handling HTTP requests related to user management in the car rental application.
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
    	String message = userService.save(user);
        return ResponseEntity.ok(message);
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
    	String message = userService.login(user);
        return ResponseEntity.ok(message);
    }
      
    
}
