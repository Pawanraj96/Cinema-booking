package com.xyz.cinema.booking.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.xyz.cinema.booking.model.Booking;
import com.xyz.cinema.booking.model.Movie;
import com.xyz.cinema.booking.model.SeatModel;
import com.xyz.cinema.booking.model.ShowModel;
import com.xyz.cinema.booking.repository.BookingRepository;
import com.xyz.cinema.booking.repository.MovieRepository;
import com.xyz.cinema.booking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public ResponseEntity<Map<String, Object>> bookMovie(Booking booking) {

		Map<String, Object> response = new HashMap<>();

		String movieId = booking.getMovieId();
		List<String> bookedSeats = booking.getBookedSeats();
		String paymentStatus = booking.getPaymentStatus();
//
		Optional<Movie> movieOpt = movieRepository.findById(movieId);
//
		if (bookedSeats == null || bookedSeats.isEmpty()) {

			response.put("Error", "No seats selected for booking");
			return ResponseEntity.badRequest().body(response);
		}

		if (movieOpt.isEmpty()) {
			response.put("Error", "Movie not found");
			return ResponseEntity.badRequest().body(response);
		}
//
		Movie movie = movieOpt.get();

		ShowModel selectedShow = movie.getShows().stream().filter(show -> show.getTime().equals(booking.getTime()))
				.findFirst().orElse(null);

		if (selectedShow == null) {
			response.put("Error", "Show not found");
		}

		for (String seatNumber : booking.getBookedSeats()) {
			SeatModel seat = selectedShow.getAvailableSeats().stream()
					.filter(item -> item.getSeatNumber().equals(seatNumber)).findFirst().orElse(null);

			if (seat == null || seat.isBooked()) {
				response.put("Error", "Seat" + seatNumber + "is already booked");
				return ResponseEntity.badRequest().body(response);
			}
		}

		double totalPrice = 0;

		for (String seatNumber : booking.getBookedSeats()) {

			for (SeatModel seat : selectedShow.getAvailableSeats()) {
				if (seat.getSeatNumber().equals(seatNumber)) {
					seat.setBooked(true);
					totalPrice += seat.getPrice();
				}
			}
		}

		// save the movie
		movieRepository.save(movie);
		// Save the booking
		bookingRepository.save(booking);

		response.put("message", "Booking successful!");
		response.put("movie", movie.getMovieTitle());
		response.put("showTime", selectedShow.getTime());
		response.put("seats", booking.getBookedSeats());
		response.put("totalPrice", totalPrice);

		return ResponseEntity.ok(response);
	}

	@Override
	public List<Booking> getBookingHistory() {
		return bookingRepository.findAll();
	}

}
