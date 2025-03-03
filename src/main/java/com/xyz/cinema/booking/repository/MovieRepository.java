package com.xyz.cinema.booking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xyz.cinema.booking.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {

	// Custom query method to find a movie by its name
	Optional<Movie> findByMovieTitle(String movieTitle);

	List<Movie> findByGenre(String genre);

	List<Movie> findByDate(String date);

	List<Movie> findByMovieTitleContainingIgnoreCase(String movieTitle);

	List<Movie> findByLocationContainingIgnoreCase(String location);

}
