package com.xyz.cinema.booking.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xyz.cinema.booking.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {

	// Custom query method to find a movie by its name
	Optional<Movie> findByMovieTitle(String movieTitle);

}
