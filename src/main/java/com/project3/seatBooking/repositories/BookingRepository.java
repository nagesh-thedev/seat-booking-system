package com.project3.seatBooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project3.seatBooking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
