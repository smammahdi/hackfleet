package com.tms.trainms.Train;

import com.tms.trainms.Train.DTO.TrainRequest;
import com.tms.trainms.Train.DTO.TrainRequestQuery;
import com.tms.trainms.Train.DTO.TrainWithSeatsDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    // API to get all trains based on From, To, and Date
    @PostMapping
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
    public ResponseEntity<Train> addTrain(@RequestBody TrainRequest trainRequest) {
        Train addedTrain = trainService.addTrainWithSeats(trainRequest);
        return ResponseEntity.ok(addedTrain);
    }


    @GetMapping("/with-seats")
    public ResponseEntity<List<TrainWithSeatsDTO>> getAllTrainsWithSeats() {
        List<TrainWithSeatsDTO> trainsWithSeats = trainService.getAllTrainsWithSeats();
        return ResponseEntity.ok(trainsWithSeats);
    }
}
