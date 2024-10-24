package com.tms.bookingms.booking.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tms.bookingms.booking.Booking;
import com.tms.bookingms.booking.BookingRepository;
import com.tms.bookingms.booking.BookingService;

import com.tms.bookingms.booking.clients.MailTrapService;
import com.tms.bookingms.booking.clients.TrainClient;
import com.tms.bookingms.booking.clients.UserClient;
import com.tms.bookingms.booking.dto.SeatStatusDTO;

import com.tms.bookingms.booking.external.Seat;
import com.tms.bookingms.booking.external.User;

@Service
public class BookingServiceImpl implements BookingService
{
    private final BookingRepository bookingRepository;
    private final TrainClient trainClient;
    private final UserClient userClient;
    private final MailTrapService mailtrapService;

    public BookingServiceImpl(BookingRepository bookingRepository, TrainClient TrainClient, UserClient UserClient, MailTrapService mailtrapService) {
        this.bookingRepository = bookingRepository;
        this.trainClient = TrainClient;
        this.userClient = UserClient;
        this.mailtrapService = mailtrapService;
    }

    @Override
    @Transactional
    public Long addBooking(Long userId, List<Long> seatIds) {
        Booking booking = new Booking();
        User user = userClient.getUser(userId).getBody();
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        String email = user.getEmail();

        booking.setUserId(userId);
        booking.setSeatIds(seatIds);
        booking.setVerified(false);

        // Generate OTP
        Long otp = generateOtp();
        try {
            // Send OTP via Mailtrap email service
            mailtrapService.sendOtpEmail(email, otp);
        } catch (IOException e) {
            throw new RuntimeException("Failed to send OTP email", e);
        }
        SeatStatusDTO seatStatusDTO = new SeatStatusDTO();
        seatStatusDTO.setSeatIds(seatIds);
        seatStatusDTO.setStatus("RESERVED");
        Long amount = trainClient.setSeatStatus(seatStatusDTO).getBody();
        booking.setAmount(amount);
        bookingRepository.save(booking);
        booking.setOtp(otp);

        return booking.getId();
    }

    private Long generateOtp() {
        // Generate 4-digit OTP
        return (long) (Math.random() * 9000) + 1000;
    }


    @Override
    public Booking getBookingById(Long bookingId) 
    {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public boolean deleteBooking(Long bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.deleteById(bookingId);
            return true;
        }
        return false;
    }

    @Override
    public boolean confirmBooking(Long userId, Long bookingId, Long otp) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new RuntimeException("Booking not found");
        }
        if (!booking.getUserId().equals(userId)) {
            throw new RuntimeException("Booking does not belong to user");
        }
        if (!booking.getVerified()) {
            if (otp.equals(booking.getOtp())) {
                booking.setVerified(true);
                SeatStatusDTO seatStatusDTO = new SeatStatusDTO();
                seatStatusDTO.setSeatIds(booking.getSeatIds());
                seatStatusDTO.setStatus("BOOKED");
                trainClient.setSeatStatus(seatStatusDTO);
                bookingRepository.save(booking);
                return true;
            }
        }
        return false;
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
    public boolean confirmPayment(Long bookingId) 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirmPayment'");
    }

    @Override
    public void deleteBookingById(Long bookingId) 
    {
        bookingRepository.deleteById(bookingId);
    }
}
