package com.tms.bookingms.booking;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tms.bookingms.booking.dto.UserBookingOTPDTO;
import com.tms.bookingms.booking.dto.UserSeatsDTO;


@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> addBooking(@RequestBody UserSeatsDTO userSeats) {
        Long userId = userSeats.getUserId();
        List<Long> seatIds = userSeats.getSeatIds();
        try {
            Long bookingId = bookingService.addBooking(userId, seatIds);
            return new ResponseEntity<>(bookingId,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Booking wasn't succesful!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyBooking(@RequestBody UserBookingOTPDTO userBookingOTP) {
        Long userId = userBookingOTP.getUserId();
        Long bookingId = userBookingOTP.getBookingId();
        Long otp = userBookingOTP.getOtp();
        try {
            boolean verified = bookingService.confirmBooking(userId, bookingId, otp);
            return new ResponseEntity<>(verified, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Booking verification wasn't succesful!", HttpStatus.NOT_FOUND);
        }
    }
}
