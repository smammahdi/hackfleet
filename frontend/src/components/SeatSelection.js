// src/components/SeatSelection.js
import React, { useState } from 'react';
import './SeatSelection.css';
import { useNavigate } from 'react-router-dom';

const SeatSelection = () => {
  // Dummy data for coaches and seats
  const coaches = ['KA', 'THA', 'DA', 'DANT', 'TO', 'THO', 'DOA'];
  const seats = Array.from({ length: 100 }, (_, i) => i + 1); // Seat numbers 1 to 100
  const navigate = useNavigate();
  const [selectedCoach, setSelectedCoach] = useState('KA');
  const [selectedSeats, setSelectedSeats] = useState([]);
  const bookedSeats = [3, 6, 9, 12, 33, 45]; // Some dummy booked seats

  const toggleSeatSelection = (seat) => {
    if (selectedSeats.includes(seat)) {
      setSelectedSeats(selectedSeats.filter((s) => s !== seat));
    } else {
      setSelectedSeats([...selectedSeats, seat]);
    }
  };

  return (
    <div className="seat-selection-page">
      <div className="header">
        <h2>SONAR BANGLA EXPRESS (788)</h2>
        <p>Dhaka to Chattogram</p>
        <p>Departure: 03 NOV, 07:00 AM | Arrival: 11:55 AM | Class: S_CHAIR</p>
        <button className="cancel-btn">Cancel</button>
      </div>

      <div className="coach-selection">
        <h3>Select Coach</h3>
        <div className="coaches">
          {coaches.map((coach) => (
            <button
              key={coach}
              className={`coach-btn ${coach === selectedCoach ? 'selected' : ''}`}
              onClick={() => setSelectedCoach(coach)}
            >
              {coach}
            </button>
          ))}
        </div>
      </div>

      <div className="seat-selection">
        <h3>Select Seats</h3>
        <div className="seats-grid">
          {seats.map((seat) => {
            let seatClass = 'seat available';
            if (bookedSeats.includes(seat)) {
              seatClass = 'seat booked';
            } else if (selectedSeats.includes(seat)) {
              seatClass = 'seat selected';
            }

            return (
              <button
                key={seat}
                className={seatClass}
                onClick={() => {
                  if (!bookedSeats.includes(seat)) {
                    toggleSeatSelection(seat);
                  }
                }}
              >
                {selectedCoach}-{seat}
              </button>
            );
          })}
        </div>
      </div>

      <button className="continue-btn" onClick={() => navigate('/otp-verification')}>
        Continue Purchase
        </button>;
    </div>
  );
};

export default SeatSelection;
