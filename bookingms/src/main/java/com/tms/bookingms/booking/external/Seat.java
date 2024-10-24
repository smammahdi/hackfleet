package com.tms.bookingms.booking.external;

public class Seat {
    private Long id;
    private String seatClass;
    private String status;
    private Long fare;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass){
        this.seatClass = seatClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Long getFare() {
        return fare;
    }

    public void setFare(Long fare){
        this.fare = fare;
    }
}
