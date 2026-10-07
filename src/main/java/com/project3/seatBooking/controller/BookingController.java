package com.project3.seatBooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project3.seatBooking.DTO.BookingRequest;
import com.project3.seatBooking.DTO.BookingResponse;
import com.project3.seatBooking.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	private BookingService bookingService;
	
	
	
	public BookingController(BookingService bookingService) {
		super();
		this.bookingService = bookingService;
	}


    @PostMapping
	public ResponseEntity<BookingResponse> bookSeat(@Valid @RequestBody BookingRequest request){
		return bookingService.seatBook(request);
	}
	

}
