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

import com.masai.entities.Car;
import com.masai.exceptions.DetailNotFoundException;

@Service
public class CarService {
	
	private final String filePath = "Car.ser";
	
	// Saves the provided car object to the file and returns the saved car object.
	public Car save(Car car) {
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
            outputStream.writeObject(car);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return car;
    }
	
	// Updates the car information with the specified ID and returns the updated car object.
	public Car update(Integer id, Car updatedCar) {
		List<Car> cars = get();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getId().equals(id)) {
                cars.set(i, updatedCar);
                break;
            }
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Car car : cars) {
                outputStream.writeObject(car);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return updatedCar;
    }
	
	// Retrieves a list of all cars from the file.
	public List<Car> get()  {
		List<Car> cars = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    Car car = (Car) inputStream.readObject();
                    cars.add(car);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cars;
    }
	
	// Retrieves the car with the specified ID from the file.
	public Car get(Integer id) {
        List<Car> cars = get();
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        throw new DetailNotFoundException("Car is not found with ID : " + id);
    }
	
	// Deletes the car with the specified ID from the file and returns true upon successful deletion.
	public Boolean delete(Integer id) {
        List<Car> cars = get();
        boolean carExists = cars.stream().anyMatch(car -> Objects.equals(car.getId(), id));
        if (!carExists) {
            throw new DetailNotFoundException("Car not found with ID: " + id);
        }
        cars.removeIf(car -> car.getId().equals(id));
        saveCars(cars);
        return true;
    }
	
	// Helper method to save the list of cars to the file.
	private void saveCars(List<Car> cars) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Car car : cars) {
                outputStream.writeObject(car);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
