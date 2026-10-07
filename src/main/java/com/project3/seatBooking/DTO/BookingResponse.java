package com.project3.seatBooking.DTO;

import com.project3.seatBooking.constant.BookingStatus;

import lombok.Data;

@Data
public class BookingResponse {

	private String message;
	private BookingStatus status;
	private Long showId;
	private Long seatId;
	private Long userId;
	
}
