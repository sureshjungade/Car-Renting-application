package com.masai.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Entity class representing a car in the car application.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable{
	private Integer id;
	private String maker;
	private String model;
	private Integer year;
	private Integer price;
	private Boolean availability;
}
