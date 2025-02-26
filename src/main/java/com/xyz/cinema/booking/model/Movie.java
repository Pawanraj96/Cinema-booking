package com.xyz.cinema.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "movie")
public class Movie {

	@Id
	private String id;
	
	@Field("movie_title")
	private String movieTitle;

	@Field("genre")
	private String genre;

	@Field("date")
	private String date;

	@Field("location")
	private String location;
}
