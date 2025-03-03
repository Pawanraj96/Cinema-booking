package com.xyz.cinema.booking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xyz.cinema.booking.model.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {

}
