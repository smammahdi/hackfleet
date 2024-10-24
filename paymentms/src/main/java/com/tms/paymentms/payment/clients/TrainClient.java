package com.tms.paymentms.payment.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tms.paymentms.payment.dto.SeatStatusDTO;


@FeignClient(name = "trainms",url="${train-service.url}")
public interface TrainClient {
    @PutMapping("/train/setStatus")
    public ResponseEntity<Long> setSeatStatus(@RequestBody SeatStatusDTO seatStatusDTO);
}