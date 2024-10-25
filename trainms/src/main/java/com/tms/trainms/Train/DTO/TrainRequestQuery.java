package com.tms.trainms.Train.DTO;

import com.tms.trainms.Train.Seat;

import java.time.LocalDate;
import java.util.List;

public class TrainRequestQuery {
    private String fromPlace;
    private String toPlace;
    private LocalDate departureDate;

    // Getters and Setters
   




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

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
}
