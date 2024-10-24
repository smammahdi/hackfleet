package com.tms.trainms.Train.messaging;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.tms.trainms.Train.DTO.SeatStatusDTO;
import com.tms.trainms.Train.TrainService;

@Service
public class TicketMessageConsumer 
{
    private final TrainService trainService;

    public TicketMessageConsumer(TrainService trainService) 
    {
        this.trainService = trainService;
    }

    @RabbitListener(queues = "seatStatusQueue")
    public void receiveMessage(SeatStatusDTO seatStatusDTO) 
    {
        String status = seatStatusDTO.getStatus();
        List<Long> seatIds = seatStatusDTO.getSeatIds();
        trainService.setSeatStatus(seatIds, status);
    }
}
