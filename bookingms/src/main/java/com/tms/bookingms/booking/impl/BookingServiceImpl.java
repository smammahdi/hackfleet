package com.tms.bookingms.booking.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tms.bookingms.booking.Booking;
import com.tms.bookingms.booking.BookingRepository;
import com.tms.bookingms.booking.BookingService;
import com.tms.bookingms.booking.external.Seat;
import com.tms.bookingms.booking.external.User;

@Service
public class BookingServiceImpl implements BookingService
{
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
        User userDto = getUser(userId);
        String email = userDto.getEmail();
        booking.setUserId(userId);
        booking.setSeatIds(seatIds);
        booking.setVerified(false);
        Long otp = getOTP(email);
        booking.setOtp(otp);
        bookSeats(seats);
        bookingRepository.save(booking);
        return booking.getId();
    }

    private List<Seat> getSeats(List<Long> seatIds) 
    {
        // call seat service to get seat details
        return null;
    }

    private Long getOTP(String email) {
        // generate OTP
        // we will generate an otp and send it to user email
        return 0L;
    }

    private User getUser(Long userId){
        return new User();
    }

    private void bookSeats(List<Seat> seats) {
        // set seat status to booked
    }

    @Override
    public Booking getBookingById(Long bookingId) 
    {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public boolean deleteBooking(Long bookingId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBooking'");
    }

    @Override
    public boolean confirmBooking(Long userId, Long bookingId, Long otp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirmBooking'");
    }

    @Override
    public boolean confirmPayment(Long bookingId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirmPayment'");
    }
}
