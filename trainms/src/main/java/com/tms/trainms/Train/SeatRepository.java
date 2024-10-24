package com.tms.trainms.Train;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByTrainId(Long trainId);
    List<Seat> findByTrainIdAndSeatClass(Long trainId, String seatClass);
}
