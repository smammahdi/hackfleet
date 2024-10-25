package com.tms.trainms.Train;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tms.trainms.Train.DTO.SeatStatusDTO;
import com.tms.trainms.Train.DTO.TrainRequest;
import com.tms.trainms.Train.DTO.TrainRequestQuery;
import com.tms.trainms.Train.DTO.TrainWithSeatsDTO;



@RestController
@RequestMapping("/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    // API to get all trains based on From, To, and Date
    @GetMapping
    public ResponseEntity<List<Train>> getTrains(@RequestBody TrainRequestQuery request) {
        List<Train> trains = trainService.getTrains(request.getFromPlace(), request.getToPlace(), request.getDepartureDate());
        return ResponseEntity.ok(trains);
    }

    // API to get seats for a specific train, with an optional seatClass parameter
    @GetMapping("/{trainId}/seats")
    public ResponseEntity<List<Seat>> getSeatsByTrainId(
            @PathVariable Long trainId,
            @RequestParam(required = false) String seatClass // Optional seatClass parameter
    ) {
        System.out.println(seatClass + " " + trainId);
        List<Seat> seats = trainService.getSeatsByTrainId(trainId, seatClass);
        return ResponseEntity.ok(seats);
    }

    // API to add a new train with associated seats
    @PostMapping("/add")
    public ResponseEntity<List<Train>> addTrain(@RequestBody List<TrainRequest> trainRequest) {
        List<Train> addedTrain = trainService.addTrainWithSeats(trainRequest);
        return ResponseEntity.ok(addedTrain);
    }

    @GetMapping("/with-seats")
    public ResponseEntity<List<TrainWithSeatsDTO>> getAllTrainsWithSeats() {
        List<TrainWithSeatsDTO> trainsWithSeats = trainService.getAllTrainsWithSeats();
        return ResponseEntity.ok(trainsWithSeats);
    }

    @PutMapping("/setStatus")
    public ResponseEntity<Long> getTotalCost(@RequestBody SeatStatusDTO seatStatusDTO)
    {
        System.out.println(seatStatusDTO.getSeatIds());
        System.out.println(seatStatusDTO.getStatus());
        try
        {
            Long totalCost = trainService.getTotalCost(seatStatusDTO.getSeatIds(), seatStatusDTO.getStatus());
            return new ResponseEntity<>(totalCost, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
