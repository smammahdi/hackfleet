package com.tms.trainms.Train.DTO;

import com.tms.trainms.Train.Seat;


import java.time.LocalDateTime;
import java.util.List;

public class TrainRequest {
    private String name;
    private String fromPlace;
    private String toPlace;
    private LocalDateTime departureTime;
    private List<Seat> seats;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
}
