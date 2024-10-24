package com.tms.paymentms.payment.ticket;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> 
{
    Optional<List<Ticket>> findByUserId(Long userId);
}
