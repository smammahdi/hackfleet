package com.tms.trainms.Train.DTO;

import com.tms.trainms.Train.Seat;
import com.tms.trainms.Train.Train;

import java.util.List;

public class TrainResponseDTO {
    private Train trainInfo;
    private List<Seat> seats;

    // Constructor
    public TrainResponseDTO(Train train, List<Seat> seats) {
        this.trainInfo = train;
        this.seats = seats;
    }

    // Getters and Setters
    public Train getTrainInfo() {
        return trainInfo;
    }

    public void setTrainInfo(Train trainInfo) {
        this.trainInfo = trainInfo;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
