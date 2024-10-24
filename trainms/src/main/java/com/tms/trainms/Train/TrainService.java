package com.tms.trainms.Train;

import java.time.LocalDate;
import java.util.List;

import com.tms.trainms.Train.DTO.TrainRequest;
import com.tms.trainms.Train.DTO.TrainWithSeatsDTO;

public interface TrainService {
    List<Train> getTrains(String fromPlace, String toPlace, LocalDate date);
    List<Seat> getSeatsByTrainId(Long trainId, String seatClass); // Modified to include seatClass
    Train addTrainWithSeats(TrainRequest trainRequest); // New method to add train with seats
    public List<TrainWithSeatsDTO> getAllTrainsWithSeats(); // New method to get all trains
    Long getTotalCost(List<Long> seatIds, String status);
    void setSeatStatus(List<Long> seatIds, String status);
}
