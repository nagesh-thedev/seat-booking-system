package com.project3.seatBooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project3.seatBooking.DTO.BookingResponse;
import com.project3.seatBooking.constant.BookingStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SeatNotFoundException.class)
	public ResponseEntity<BookingResponse> seatNotFoundException(Exception e){
		 BookingResponse res = new BookingResponse();
		 res.setMessage(e.getMessage());
		 res.setStatus(BookingStatus.FAILED);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}
	
	@ExceptionHandler(ShowNotFoundException.class)
	public ResponseEntity<BookingResponse> showNotFoundException(Exception e){
		 BookingResponse res = new BookingResponse();
		 res.setMessage(e.getMessage());
		 res.setStatus(BookingStatus.FAILED);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BookingResponse> handlemethodArgumentNotValidException(Exception e){
		 BookingResponse res = new BookingResponse();
		 res.setMessage(e.getMessage());
		 res.setStatus(BookingStatus.FAILED);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	}
	
	
	
}
