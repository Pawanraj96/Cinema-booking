package com.xyz.cinema.booking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.xyz.cinema.booking.model.Movie;

public interface MovieService {

	ResponseEntity<?> saveMovie(Movie movie);

	List<Movie> getAllMoviesNames();

	ResponseEntity<?> updateMovie(Movie movie, String id);

	ResponseEntity<?> deleteMovie(String id);

	Movie getMovieById(String id);

	List<Movie> getMovieByParams(String movieTitle, String genre, String date, String location);
}
