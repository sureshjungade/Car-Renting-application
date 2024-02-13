package com.masai.services;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.masai.entities.Booking;
import com.masai.exceptions.DetailNotFoundException;

@Service
public class BookingService {
	
private final String filePath = "Booking.ser";
	
	//Saves the provided booking object to the file and returns the saved booking object.
	public Booking save(Booking booking) {
		
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath, true))) {
            outputStream.writeObject(booking);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return booking;
    }
	
	// Updates the booking information with the specified ID and returns the updated booking object.
	public Booking update(Integer id, Booking updatedBooking) {
		List<Booking> bookings = get();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getId().equals(id)) {
                bookings.set(i, updatedBooking);
                break;
            }
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Booking booking : bookings) {
                outputStream.writeObject(booking);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return updatedBooking;
    }
	
	// Retrieves a list of all bookings from the file.
	public List<Booking> get()  {
		List<Booking> bookings = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    Booking booking = (Booking) inputStream.readObject();
                    bookings.add(booking);
                   
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bookings;
    }
	
	// Retrieves the booking with the specified ID from the file.
	public Booking get(Integer id) {
        List<Booking> bookings = get();
        System.out.println(bookings.size());
        for (Booking booking : bookings) {
            if (booking.getId().equals(id)) {
                return booking;
            }
        }
        throw new DetailNotFoundException("Booking is not found with ID : " + id);
    }
	
	// Deletes the booking with the specified ID from the file and returns true upon successful deletion.
	public Boolean delete(Integer id) {
        List<Booking> bookings = get();
        boolean bookingExists = bookings.stream().anyMatch(booking -> Objects.equals(booking.getId(), id));
        if (!bookingExists) {
            throw new DetailNotFoundException("Booking not found with ID: " + id);
        }
        bookings.removeIf(booking -> booking.getId().equals(id));
        saveBookings(bookings);
        return true;
    }
	
	// Helper method to save the list of bookings to the file.
	private void saveBookings(List<Booking> bookings) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Booking booking : bookings) {
                outputStream.writeObject(booking);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
