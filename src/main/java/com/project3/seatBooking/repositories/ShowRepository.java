package com.project3.seatBooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project3.seatBooking.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {

}
