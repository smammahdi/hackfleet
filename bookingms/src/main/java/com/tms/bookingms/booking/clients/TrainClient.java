package com.tms.bookingms.booking.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "trainms",url="${train-service.url}")
public interface TrainClient {
    
}
