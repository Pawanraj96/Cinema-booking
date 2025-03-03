package com.xyz.cinema.booking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.cinema.booking.model.Booking;
import com.xyz.cinema.booking.service.BookingService;

@RestController
@RequestMapping("/movie")
public class BookingController {
	@Autowired
	private BookingService bookingService;

	@PostMapping("/booking")
	public ResponseEntity<ResponseEntity<Map<String, Object>>> bookMovie(@RequestBody Booking booking) {

		ResponseEntity<Map<String, Object>> response = bookingService.bookMovie(booking);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/booking/history")
	public List<Booking> getMovieHistory() {

		return bookingService.getBookingHistory();

	}

}
