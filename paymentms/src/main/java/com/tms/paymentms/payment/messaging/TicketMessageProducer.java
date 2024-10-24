package com.tms.paymentms.payment.messaging;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TicketMessageProducer 
{
    private final RabbitTemplate rabbitTemplate;

    public TicketMessageProducer(RabbitTemplate rabbitTemplate) 
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendBookingMessage(Long bookingId) 
    {
        rabbitTemplate.convertAndSend("bookingQueue", bookingId);
    }

    public void sendSeatingMessage(List<Long> seatIds) 
    {
        rabbitTemplate.convertAndSend("seatStatusQueue", seatIds);
    }
}
