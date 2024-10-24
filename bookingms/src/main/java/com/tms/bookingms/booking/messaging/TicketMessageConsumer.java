package com.tms.bookingms.booking.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.tms.bookingms.booking.BookingService;

@Service
public class TicketMessageConsumer 
{
    private final BookingService bookingService;

    public TicketMessageConsumer(BookingService bookingService) 
    {
        this.bookingService = bookingService;
    }

    @RabbitListener(queues = "bookingQueue")
    public void receiveMessage(Long bookingId) 
    {
        bookingService.deleteBookingById(bookingId);
    }
}
