package com.xyz.cinema.booking.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class ShowModel {

	@Field("time")
	private String time;

	@Field("total_seats")
	private int totalSeats = 5;

	@Field("available_seats")
	private List<SeatModel> availableSeats;

	public ShowModel(String time) {
		super();
		this.time = time;
		this.availableSeats = List.of(new SeatModel("Recliner", 500), new SeatModel("A1", 200), new SeatModel("A2", 150),
				new SeatModel("A3", 150), new SeatModel("A4", 150));
	}

}
