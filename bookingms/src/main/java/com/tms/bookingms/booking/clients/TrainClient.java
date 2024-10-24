package com.tms.bookingms.booking.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tms.bookingms.booking.dto.SeatStatusDTO;

@FeignClient(name="trainms", url="${train-service.url}")
public interface TrainClient {
    @PutMapping("/train/setStatus")
    public ResponseEntity<Long> setSeatStatus(@RequestBody SeatStatusDTO seatStatusDTO);
}




