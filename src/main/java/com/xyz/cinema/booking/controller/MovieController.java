package com.xyz.cinema.booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.cinema.booking.model.Movie;
import com.xyz.cinema.booking.service.MovieService;

@RestController
@RequestMapping
public class MovieController {

	private MovieService movieService;

	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	// Add movie
	@PostMapping("/movie")
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
//		log.info("Movie Saved with movie name: {}", movie);
		return movieService.saveMovie(movie);
	}

	// get all movies
	@GetMapping("/movies")
	public List<Movie> getAllMoviesNames() {
		return movieService.getAllMoviesNames();
	}

	// edit movie
	@PutMapping("/movie/{movieId}")
	public ResponseEntity<?> updateMovie(@PathVariable String movieId, @RequestBody Movie movie) {

		return movieService.updateMovie(movie, movieId);

	}

	// delete Movie
	@DeleteMapping("/movie/{movieId}")
	public ResponseEntity<?> deleteMovie(@PathVariable String movieId) {

		return movieService.deleteMovie(movieId);

	}

	// get movie by id

	@GetMapping("/movie/{id}")
	public Movie getEmployeeById(@PathVariable String id) {

		return movieService.getMovieById(id);

	}

	@GetMapping("/movie")
    public List<Movie> getMovies(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String movieTitle,
            @RequestParam(required = false) String location) {
        
        if (genre != null) {
            return movieService.getMoviesByGenre(genre);
        } else if (date != null) {
            return movieService.getMoviesByDate(date);
        } else if (movieTitle != null) {
        	return movieService.getMoviesByTitle(movieTitle);
        } else if (location != null) {
            return movieService.getMoviesByLocation(location);
        } else {
            return List.of();  // Return empty list if no filter is provided
        }
    }
}
