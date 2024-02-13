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

import com.masai.entities.Car;
import com.masai.services.CarService;

//Controller class responsible for handling HTTP requests related to car management in the car rental application.
@Controller
@RequestMapping("/car")
public class CarController {

	@Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
    	Car savedCar = carService.save(car);
        return ResponseEntity.ok(savedCar);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Integer id, @RequestBody Car car) {
    	Car updatedCar = carService.update(id, car);
        return ResponseEntity.ok(updatedCar);
    }
    
    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
    	List<Car> cars = carService.get();
        return ResponseEntity.ok(cars);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Integer id) {
    	Car car = carService.get(id);
        return ResponseEntity.ok(car);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable Integer id) {
    	Boolean deleted = carService.delete(id);
        return ResponseEntity.ok(deleted);
    }
}
