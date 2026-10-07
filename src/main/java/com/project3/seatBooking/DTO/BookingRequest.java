package com.project3.seatBooking.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookingRequest {
	
	@NotNull
	@Positive
	private Long userId;
	@NotNull
	@Positive
	private Long showId;
	@NotNull
	@Positive
	private Long seatId;

}
