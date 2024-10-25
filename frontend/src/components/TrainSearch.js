import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import './TrainSearch.css';
import Sidebar from './Sidebar'; // Import Sidebar

const TrainSearchResults = () => {
  const navigate = useNavigate(); // Initialize useNavigate
  const [trains, setTrains] = useState([]);

  // Function to mock train data for the demo
  const mockTrainData = () => {
    return [
      {
        id: 1,
        name: "SONAR BANGLA EXPRESS (788)",
        fromPlace: "Dhaka",
        toPlace: "Chattogram",
        departureTime: "03 NOV, 07:00 AM",
        arrivalTime: "03 NOV, 11:55 AM",
        noOfSeats: 100,
        seats: [
          { type: "SNIGDHA", fare: 855, available: 50 },
          { type: "S_CHAIR", fare: 450, available: 80 },
          { type: "AC_S", fare: 1025, available: 30 },
          { type: "F_SEAT", fare: 685, available: 20 }
        ]
      },
      {
        id: 2,
        name: "MAHANAGAR PROVATI (704)",
        fromPlace: "Dhaka",
        toPlace: "Chattogram",
        departureTime: "03 NOV, 07:45 AM",
        arrivalTime: "03 NOV, 01:35 PM",
        noOfSeats: 150,
        seats: [
          { type: "SNIGDHA", fare: 777, available: 60 },
          { type: "S_CHAIR", fare: 405, available: 100 },
          { type: "AC_S", fare: 932, available: 40 },
          { type: "F_SEAT", fare: 621, available: 25 }
        ]
      }
    ];
  };

  // Function to simulate fetching train data (can be replaced by actual API call)
  const fetchTrains = async () => {
    try {
      // Replace this later with an actual API call
      // const response = await fetch('/trains');
      // const data = await response.json();
      const data = mockTrainData(); // Using mock data for now
      setTrains(data);
    } catch (error) {
      console.error('Error fetching trains:', error);
    }
  };

  useEffect(() => {
    fetchTrains(); // Fetch train data when component mounts
  }, []);

  return (
    <div className="train-search-container">
      <Sidebar isLoggedIn={true} /> {/* Add Sidebar */}

      <div className="train-results-page">
        <div className="header">
          <h2>Available Trains</h2>
          <button className="modify-btn" onClick={() => navigate('/dashboard')}>Modify</button>
        </div>

        {trains.length === 0 ? (
          <p>Loading trains...</p>
        ) : (
          trains.map((train, index) => (
            <div className="train-card" key={index}>
              <h3>{train.name}</h3>
              <div className="train-details">
                <p>Departure: {train.departureTime} from {train.fromPlace}</p>
                <p>Arrival: {train.arrivalTime} at {train.toPlace}</p>
                <p>Seats Available: {train.noOfSeats}</p>
              </div>

              <div className="seat-options">
                {train.seats.map((seat, idx) => (
                  <div className="seat-card" key={idx}>
                    <h4>{seat.type}</h4>
                    <p>৳{seat.fare} (Including VAT)</p>
                    <p>Available Tickets: {seat.available}</p>
                    <button
                      className="book-btn"
                      onClick={() => navigate('/seat-selection', { state: { selectedTrain: train } })} // Navigate with selected train info
                    >
                      Book Now
                    </button>
                  </div>
                ))}
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default TrainSearchResults;
