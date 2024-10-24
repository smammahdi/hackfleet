package com.tms.paymentms.payment;

import java.util.List;

import com.tms.paymentms.payment.dto.TicketDTO;

public interface PaymentService {
    Payment createPayment(Long userId, Long bookingId, List<String> passengerList);
    List<TicketDTO> getTicketsByUser(Long userId);
    TicketDTO getTicketByUserById(Long userId, Long ticketId);
    boolean deleteTicket(Long userId, Long ticketId);
}
