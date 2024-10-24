package com.tms.bookingms.booking.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url="${train-service.url}")
public interface TrainClient {
    
}
