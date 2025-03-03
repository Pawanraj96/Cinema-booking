package com.xyz.cinema.booking.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "bookings")
public class Booking {

	@Id
	private String id;

	@Field("movie_id")
	private String movieId;

	@Field("date")
	private String date;

	@Field("time")
	private String time;

	@Field("location")
	private String location;

	@Field("booked_seats")
	private List<String> bookedSeats = new ArrayList<>();

	@Field("total_price")
	private int totalPrice;

	@Field("payment_status")
	private String paymentStatus;

	public List<String> getBookedSeats(){
		return bookedSeats;
	}
	
	public void setBookedSeats(List<String> bookedSeats) {
		if(bookedSeats == null) {
			this.bookedSeats = new ArrayList<>();
		}else {
			this.bookedSeats=bookedSeats;
		}
	}

}
