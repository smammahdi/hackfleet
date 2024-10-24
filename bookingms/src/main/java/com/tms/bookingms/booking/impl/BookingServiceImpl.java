package com.tms.bookingms.booking.impl;

import com.tms.bookingms.booking.BookingRepository;
import com.tms.bookingms.booking.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.tms.bookingms.booking.Booking;
import com.tms.bookingms.booking.external.Seat;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public Long addBooking(Long userId, List<Long> seatIds) {
        List<Seat> seats = getSeats(seatIds);
        // check if seats are available
        if (seats.stream().anyMatch(seat -> !seat.getStatus().equals("AVAILABLE"))) {
            throw new RuntimeException("Seat not available");
        }
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setSeatIds(seatIds);
        booking.setVerified(false);
        Long otp = getOTP(String email);
    }

    private List<Seat> getSeats(List<Long> seatIds) {
        // call seat service to get seat details
        return null;
    }

    private Long getOTP(String email) {
        // generate OTP
        // we will generate an otp and send it to user email
    }
    

}
