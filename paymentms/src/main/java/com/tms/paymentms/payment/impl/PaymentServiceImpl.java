package com.tms.paymentms.payment.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tms.paymentms.payment.Payment;
import com.tms.paymentms.payment.PaymentRepository;
import com.tms.paymentms.payment.PaymentService;
import com.tms.paymentms.payment.clients.BookingClient;
import com.tms.paymentms.payment.clients.UserClient;
import com.tms.paymentms.payment.dto.TicketDTO;
import com.tms.paymentms.payment.external.Booking;
import com.tms.paymentms.payment.messaging.TicketMessageProducer;
import com.tms.paymentms.payment.ticket.Ticket;
import com.tms.paymentms.payment.ticket.TicketRepository;

import feign.FeignException;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final TicketRepository ticketRepository;
    private final UserClient userClient;
    private final BookingClient bookingClient;
    private final TicketMessageProducer ticketMessageProducer;

    public PaymentServiceImpl(
        PaymentRepository paymentRepository, 
        UserClient userClient, 
        BookingClient bookingClient, 
        TicketRepository ticketRepository,
        TicketMessageProducer ticketMessageProducer) {
        this.paymentRepository = paymentRepository;
        this.userClient = userClient;
        this.bookingClient = bookingClient;
        this.ticketRepository = ticketRepository;
        this.ticketMessageProducer = ticketMessageProducer;
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

    @Override
    public List<TicketDTO> getTicketsByUser(Long userId) 
    {
        List<Ticket> tickets = ticketRepository.findByUserId(userId).orElse(null);
        if(tickets == null)
        {
            return null;
        }
        return tickets.stream().map(this::convertTicketToDTO).toList();
    }

    private TicketDTO convertTicketToDTO(Ticket ticket)
    {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(ticket.getId());
        ticketDTO.setUserId(ticket.getUserId());
        ticketDTO.setBookingId(ticket.getBookingId());
        ticketDTO.setPaymentId(ticket.getPaymentId());

        Payment payment = paymentRepository.findById(ticket.getPaymentId()).orElse(null);
        ticketDTO.setPassengerList(payment.getPassengerList());
        ticketDTO.setAmount(payment.getAmount());
        
        Booking booking = bookingClient.getBookingById(ticket.getBookingId()).getBody();
        if(booking == null)
        {
            return null;
        }
        ticketDTO.setSeatId(booking.getSeatIds());
        return ticketDTO;
    }

    @Override
    public TicketDTO getTicketByUserById(Long userId, Long ticketId) 
    {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if(ticket == null || !ticket.getUserId().equals(userId))
        {
            return null;
        }
        return convertTicketToDTO(ticket);
    }

    @Override
    public boolean deleteTicket(Long userId, Long ticketId) 
    {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if(ticket == null || !ticket.getUserId().equals(userId))
        {
            return false;
        }
        Payment payment = paymentRepository.findById(ticket.getPaymentId()).orElse(null);
        TicketDTO ticketDTO = convertTicketToDTO(ticket);
        paymentRepository.delete(payment);
        ticketRepository.delete(ticket);
        ticketMessageProducer.sendBookingMessage(ticketDTO.getBookingId());
        ticketMessageProducer.sendSeatingMessage(ticketDTO.getSeatId());
        return true;
    }
    
}
