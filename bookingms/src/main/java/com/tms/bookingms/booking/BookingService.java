package com.tms.bookingms.booking;

import java.util.List;

public interface BookingService {
    Long addBooking (Long userId, List<Long> seatIds);
    boolean deleteBooking (Long bookingId);
    boolean confirmBooking (Long userId, Long bookingId, Long otp);
    boolean confirmPayment (Long bookingId);
    Booking getBookingById (Long bookingId);
}