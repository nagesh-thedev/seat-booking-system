package com.project3.seatBooking.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project3.seatBooking.DTO.BookingRequest;
import com.project3.seatBooking.DTO.BookingResponse;
import com.project3.seatBooking.constant.BookingStatus;
import com.project3.seatBooking.constant.SeatStatus;
import com.project3.seatBooking.entity.Booking;
import com.project3.seatBooking.entity.Seat;
import com.project3.seatBooking.entity.Show;
import com.project3.seatBooking.exception.SeatNotFoundException;
import com.project3.seatBooking.exception.ShowNotFoundException;
import com.project3.seatBooking.repositories.BookingRepository;
import com.project3.seatBooking.repositories.SeatRepository;
import com.project3.seatBooking.repositories.ShowRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {
	
	private SeatRepository seatRepo;
	private ShowRepository showRepo;
	private BookingRepository bookingrepo;
	
	

	public BookingServiceImpl(SeatRepository seatRepo, ShowRepository showRepo, BookingRepository bookingrepo) {
		super();
		this.seatRepo = seatRepo;
		this.showRepo = showRepo;
		this.bookingrepo = bookingrepo;
	}



	@Override
	@Transactional
	public ResponseEntity<BookingResponse> seatBook(BookingRequest request) {
		Show show = showRepo.findById(request.getShowId()).
				orElseThrow(() ->new  ShowNotFoundException("SHOW WITH ID : "+request.getShowId()+" NOT EXIST"));
		Seat seat = seatRepo.findByIdAndShowIdAndSeatStatus(request.getSeatId(),request.getShowId(),SeatStatus.AVAILABLE).
				orElseThrow(()->new SeatNotFoundException("SEAT WITH ID : "+request.getSeatId()+" NOT AVAILABLE"));
		Booking booking = new Booking();
		booking.setSeatId(seat.getId());
		booking.setShowId(show.getId());
		booking.setUserId(request.getUserId());
		booking.setStatus(BookingStatus.COMPLETED);
		booking.setCreatedAt(LocalDateTime.now());
		bookingrepo.save(booking);
		seat.setSeatStatus(SeatStatus.BOOKED);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapBookingToBookingResponse(booking));
		
	}



	private BookingResponse mapBookingToBookingResponse(Booking booking) {
		BookingResponse response = new BookingResponse();
		response.setMessage("SEAT BOOKED SUCCESSFULLY !!");
		response.setSeatId(booking.getSeatId());
		response.setShowId(booking.getShowId());
		response.setUserId(booking.getUserId());
		response.setStatus(booking.getStatus());
		return response;
	}

}
