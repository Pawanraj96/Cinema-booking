package com.xyz.cinema.booking.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.xyz.cinema.booking.model.Booking;

public interface BookingService {
	
	ResponseEntity<Map<String, Object>> bookMovie(Booking booking);
	List<Booking> getBookingHistory();

}
