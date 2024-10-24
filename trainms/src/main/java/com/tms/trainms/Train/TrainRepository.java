package com.tms.trainms.Train;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    List<Train> findByFromPlaceAndToPlaceAndDepartureTimeBetween(String fromPlace, String toPlace, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
