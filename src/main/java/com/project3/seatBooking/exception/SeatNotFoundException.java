package com.project3.seatBooking.exception;

import org.springframework.stereotype.Component;


public class SeatNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SeatNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
