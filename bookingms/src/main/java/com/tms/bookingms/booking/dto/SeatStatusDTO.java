package com.tms.bookingms.booking.dto;

import java.util.List;

public class SeatStatusDTO {
    private String status;
    private List<Long> seatIds;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }

}
