package com.project3.seatBooking.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project3.seatBooking.DTO.BookingRequest;
import com.project3.seatBooking.DTO.BookingResponse;

@Service
public interface BookingService {
	
	public ResponseEntity<BookingResponse> seatBook(BookingRequest request);

}
