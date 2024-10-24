package com.tms.bookingms.booking.dto;

import java.util.List;

public class UserSeatsDTO {
    Long userId;
    List<Long> seatIds;
    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public List<Long> getSeatIds() {
        return seatIds;
    }
    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }
}
