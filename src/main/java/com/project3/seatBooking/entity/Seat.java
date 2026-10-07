package com.project3.seatBooking.entity;

import java.time.LocalDateTime;

import com.project3.seatBooking.constant.BookingStatus;
import com.project3.seatBooking.constant.SeatStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Data
public class Seat {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long showId;
	
	private String seatNumber;
	
	@Enumerated(EnumType.STRING)
	private SeatStatus seatStatus;

	//Optimistic Lock
//	@Version
//	private Long version;
	
	
}
