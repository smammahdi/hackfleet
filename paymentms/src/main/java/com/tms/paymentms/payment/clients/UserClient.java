package com.tms.paymentms.payment.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url="${user-service.url}")
public interface UserClient {
    @PutMapping("users/balance/reduce")
    public ResponseEntity<String> reduceBalance(@RequestParam("userId") Long userId,@RequestParam("amount") Long amount);
}
