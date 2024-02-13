package com.masai.services;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.masai.entities.User;
import com.masai.exceptions.DetailNotFoundException;

@Service
public class UserService {
	
	private final String filePath = "User.ser";
	
	public String save(User user) {
		File file = new File(filePath);
        if (!file.exists()) {
            try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Create the file if it doesn't exist
        }
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath, true))) {
            outputStream.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return "user registered successfully";
    }
	
	
	
	public List<User> get()  {
		List<User> users = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    User user = (User) inputStream.readObject();
                    users.add(user);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
	
	public String login(User userModel) {
        List<User> users = get();
        for (User user : users) {
            if (user.getUsername().equals(userModel.getUsername()) && user.getPassword().equals(userModel.getPassword())) {
                return "user loggedin successfully";
            }
        }
        throw new DetailNotFoundException("User is not registered with this username/password");
    }
	
	
}
