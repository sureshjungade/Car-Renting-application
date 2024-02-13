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

import com.masai.entities.Booking;
import com.masai.services.BookingService;

//Controller class responsible for handling HTTP requests related to booking management in the car rental application.
@Controller
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping
	public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
		Booking savedBooking = bookingService.save(booking);
		return ResponseEntity.ok(savedBooking);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Booking> updateBooking(@PathVariable Integer id, @RequestBody Booking booking) {
		Booking updatedBooking = bookingService.update(id, booking);
		return ResponseEntity.ok(updatedBooking);
	}

	@GetMapping
	public ResponseEntity<List<Booking>> getBookings() {
		List<Booking> bookings = bookingService.get();
		return ResponseEntity.ok(bookings);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBooking(@PathVariable Integer id) {
		Booking booking = bookingService.get(id);
		return ResponseEntity.ok(booking);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBooking(@PathVariable Integer id) {
		Boolean deleted = bookingService.delete(id);
		return ResponseEntity.ok(deleted);
	}
}
