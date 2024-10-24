package com.tms.paymentms.payment.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tms.paymentms.payment.Payment;
import com.tms.paymentms.payment.PaymentRepository;
import com.tms.paymentms.payment.PaymentService;
import com.tms.paymentms.payment.clients.BookingClient;
import com.tms.paymentms.payment.clients.UserClient;
import com.tms.paymentms.payment.external.Booking;

import feign.FeignException;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserClient userClient;
    private final BookingClient bookingClient;

    public PaymentServiceImpl(PaymentRepository paymentRepository, UserClient userClient, BookingClient bookingClient) {
        this.paymentRepository = paymentRepository;
        this.userClient = userClient;
        this.bookingClient = bookingClient;
    }

    @Override
    public Payment createPayment(Long userId, Long bookingId, List<String> passengerList) {
        Payment payment = new Payment();
        Booking booking = bookingClient.getBookingById(bookingId).getBody();
        if (booking == null) {
            throw new RuntimeException("Booking not found");
        }
        payment.setBookingId(bookingId);
        Long amount = booking.getAmount();
        payment.setAmount(amount);
        payment.setPassengerList(passengerList);
        try{
            userClient.reduceBalance(userId, amount);
        } catch (FeignException.BadRequest e) {
            throw new RuntimeException("Insufficient balance");
        }
        return paymentRepository.save(payment);
    }

    
}
