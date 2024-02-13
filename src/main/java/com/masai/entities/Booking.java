package com.masai.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

//Entity class representing a booking in the car rental application.
@Data
public class Booking implements Serializable{

	private Integer id;
	private Integer carID;
	private Integer userID;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private BigDecimal totalCost;
}
