package com.tms.trainms.Train.TrainServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.trainms.Train.DTO.TrainRequest;
import com.tms.trainms.Train.DTO.TrainWithSeatsDTO;
import com.tms.trainms.Train.Seat;
import com.tms.trainms.Train.SeatRepository;
import com.tms.trainms.Train.Status;
import com.tms.trainms.Train.Train;
import com.tms.trainms.Train.TrainRepository;
import com.tms.trainms.Train.TrainService;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Train> getTrains(String fromPlace, String toPlace, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return trainRepository.findByFromPlaceAndToPlaceAndDepartureTimeBetween(fromPlace, toPlace, startOfDay, endOfDay);
    }

    @Override
    public List<Seat> getSeatsByTrainId(Long trainId, String seatClass) {
        if (seatClass != null && !seatClass.isEmpty()) {
            return seatRepository.findByTrainIdAndSeatClass(trainId, seatClass);
        } else {
            return seatRepository.findByTrainId(trainId);
        }
    }

    @Override
    public List<Train> addTrainWithSeats(List<TrainRequest> trainRequests) {
        // loop through the list of TrainRequest objects and add each train with seats
        List<Train> savedTrains = trainRequests.stream().map(trainRequest -> {
            // Create the Train object from TrainRequest
            Train train = new Train();
            train.setName(trainRequest.getName());
            train.setFromPlace(trainRequest.getFromPlace());
            train.setToPlace(trainRequest.getToPlace());

            // Convert LocalDate from TrainRequest into LocalDateTime with start of the day or a default time
            train.setDepartureTime(trainRequest.getDepartureTime());

            // Save the train entity first
            Train savedTrain = trainRepository.save(train);

            // Now add the seats to the train
            List<Seat> seats = trainRequest.getSeats();
            for (Seat seat : seats) {
                seat.setTrain(savedTrain); // Associate the train with each seat
                seatRepository.save(seat); // Save each seat in the database
            }
            return savedTrain;
        }).collect(Collectors.toList());

        return savedTrains;
    }

     @Override
    public List<TrainWithSeatsDTO> getAllTrainsWithSeats() {
        List<Train> trains = trainRepository.findAll();
        return trains.stream().map(train -> new TrainWithSeatsDTO(
                train.getId(),
                train.getName(),
                train.getFromPlace(),
                train.getToPlace(),
                train.getDepartureTime(),
                train.getSeats()
        )).collect(Collectors.toList());
    }

    @Override
    public Long getTotalCost(List<Long> seatIds, String newStatus) {
        List<Seat> seats = seatRepository.findAllById(seatIds);
        Long totalCost = 0L;
        
        // Check for valid transitions based on the new status
        Status validCurrentStatus = null;

        switch (newStatus.toUpperCase()) {
            case "RESERVED":
                validCurrentStatus = Status.AVAILABLE;
                break;
            case "BOOKED":
                validCurrentStatus = Status.RESERVED;
                break;
            case "PAID":
                validCurrentStatus = Status.BOOKED;
                break;
            case "AVAILABLE":
                // No need to check the current status; seats can always be set to AVAILABLE
                validCurrentStatus = null;
                break;
            default:
                throw new IllegalArgumentException("Invalid status transition");
        }

        // Ensure all seats can move to the new status, unless transitioning to AVAILABLE
        for (Seat seat : seats) {
            if (validCurrentStatus == null || seat.getStatus() == validCurrentStatus) {
                totalCost += seat.getFare();
                System.out.println("Seat ID: " + seat.getId() + " - Fare: " + seat.getFare());
                seat.setStatus(Status.valueOf(newStatus));
            } else {
                throw new RuntimeException("Seat status transition not allowed: seat is not in the correct current status");
            }
        }

        // Save the updated seat statuses
        seatRepository.saveAll(seats);

        return totalCost;
    }


    @Override
    public void setSeatStatus(List<Long> seatIds, String status)
    {
        List<Seat> seats = seatRepository.findAllById(seatIds);
        for (Seat seat : seats) 
        {
            seat.setStatus(Status.valueOf(status));
            seatRepository.save(seat);
        }
    }
}
