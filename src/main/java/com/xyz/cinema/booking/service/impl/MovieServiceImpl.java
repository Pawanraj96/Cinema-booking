package com.xyz.cinema.booking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.xyz.cinema.booking.exception.ResourceNotFoundException;
import com.xyz.cinema.booking.model.Movie;
import com.xyz.cinema.booking.repository.MovieRepository;
import com.xyz.cinema.booking.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepository;

	public MovieServiceImpl(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}

	@Override
	public ResponseEntity<?> saveMovie(Movie movie) {

		Optional<Movie> existingMovie = movieRepository.findByMovieTitle(movie.getMovieTitle());

		if (existingMovie.isPresent()) {
			return new ResponseEntity<>("Movie with title '" + movie.getMovieTitle() + "' already exists!",
					HttpStatus.CONFLICT);
		}

		movieRepository.save(movie);
		return new ResponseEntity<>("Movie saved successfully!", HttpStatus.CREATED);
	}

	@Override
	public List<Movie> getAllMoviesNames() {

		return movieRepository.findAll();
	}

	@Override
	public ResponseEntity<?> updateMovie(Movie movie, String id) {

		// we need to check whether the movie with given id is exist in DB or not
		Movie existingMovie = movieRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Movie", "Id", id));
		existingMovie.setMovieTitle(movie.getMovieTitle());
		existingMovie.setGenre(movie.getGenre());
		existingMovie.setDate(movie.getDate());
		existingMovie.setLocation(movie.getLocation());
		// save existing employee to DB
		movieRepository.save(existingMovie);
		return new ResponseEntity<>("Movie edited successfully!", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> deleteMovie(String id) {
		// check whether a movie exist in DB or not
		movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "Id", id));
		movieRepository.deleteById(id);
		return new ResponseEntity<>("Movie deleted successfully!", HttpStatus.OK);
	}

	@Override
	public Movie getMovieById(String id) {

		return movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "Id", id));

	}

	@Override
	 public List<Movie> getMoviesByGenre(String genre) {
	        return movieRepository.findByGenre(genre);
	    }
	@Override
	    public List<Movie> getMoviesByDate(String date) {
	        return movieRepository.findByDate(date);
	    }
	@Override
	    public List<Movie> getMoviesByTitle(String movieTitle) {
	        return movieRepository.findByMovieTitleContainingIgnoreCase(movieTitle);
	    }
	@Override
	    public List<Movie> getMoviesByLocation(String location) {
	        return movieRepository.findByLocationContainingIgnoreCase(location);
	    }

}
