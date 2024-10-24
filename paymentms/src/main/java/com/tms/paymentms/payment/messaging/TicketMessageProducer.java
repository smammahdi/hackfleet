package com.tms.paymentms.payment.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.tms.paymentms.payment.dto.SeatStatusDTO;

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

    public void sendSeatingMessage(SeatStatusDTO seatStatusDTO) 
    {
        rabbitTemplate.convertAndSend("seatStatusQueue", seatStatusDTO);
    }
}
