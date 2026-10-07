package com.project3.seatBooking.entity;



import java.time.LocalDateTime;

import com.project3.seatBooking.constant.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	private Long showId;
	
	private Long seatId;
	
	private Long userId;
	
	@Enumerated(EnumType.STRING)
	private BookingStatus status;
	
	private LocalDateTime createdAt;
	
	

}
