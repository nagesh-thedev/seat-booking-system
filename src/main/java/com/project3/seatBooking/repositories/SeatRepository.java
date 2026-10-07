package com.project3.seatBooking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.project3.seatBooking.constant.SeatStatus;
import com.project3.seatBooking.entity.Seat;

import jakarta.persistence.LockModeType;

public interface SeatRepository extends JpaRepository<Seat, Long> {
   
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Seat> findByIdAndShowIdAndSeatStatus(
	        Long id,
	        Long showId,
	        SeatStatus seatStatus
	);
}
