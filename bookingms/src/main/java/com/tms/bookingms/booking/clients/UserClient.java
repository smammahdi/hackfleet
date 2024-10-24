package com.tms.bookingms.booking.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tms.bookingms.booking.external.User;

@FeignClient(name="userms", url="${user-service.url}")
public interface UserClient {
    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id);
}