package com.tms.trainms.Train.DTO;

import com.tms.trainms.Train.Seat;
import java.time.LocalDateTime;
import java.util.List;

public class TrainWithSeatsDTO {
    private Long trainId;
    private String name;
    private String fromPlace;
    private String toPlace;
    private LocalDateTime departureTime;
    private List<Seat> seats;

    public TrainWithSeatsDTO(Long trainId, String name, String fromPlace, String toPlace, LocalDateTime departureTime, List<Seat> seats) {
        this.trainId = trainId;
        this.name = name;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.departureTime = departureTime;
        this.seats = seats;
    }

    // Getters and Setters
    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getName() {
        return name;
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}

