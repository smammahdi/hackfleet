// src/components/Dashboard.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import './Dashboard.css';
import Sidebar from './Sidebar'; // Import Sidebar

const Dashboard = () => {
  const [from, setFrom] = useState('Dhaka');
  const [to, setTo] = useState('Chattogram');
  const [trainClass, setTrainClass] = useState('F_SEAT');
  const [date, setDate] = useState('');
  const navigate = useNavigate(); // Initialize useNavigate

  useEffect(() => {
    const today = new Date();
    const formattedDate = today.toISOString().split('T')[0]; 
    setDate(formattedDate);
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    // Navigate to the results page after submission
    navigate('/results');
  };

  return (
    <div className="dashboard-container">
      <Sidebar isLoggedIn={true} /> {/* Sidebar added */}

      <div className="content-area">
      <h1 align="center">Book Your Ticket</h1>
        <div className="search-container">
          <form onSubmit={handleSubmit}>
            <div className="form-row">
              <label htmlFor="from">From</label>
              <input
                type="text"
                id="from"
                name="from"
                value={from}
                onChange={(e) => setFrom(e.target.value)}
              />
            </div>

            <div className="form-row">
              <label htmlFor="to">To</label>
              <input
                type="text"
                id="to"
                name="to"
                value={to}
                onChange={(e) => setTo(e.target.value)}
              />
            </div>

            <div className="form-row">
              <label htmlFor="class">Class</label>
              <input
                type="text"
                id="class"
                name="class"
                value={trainClass}
                onChange={(e) => setTrainClass(e.target.value)}
              />
            </div>

            <div className="form-row">
              <label htmlFor="date">Journey Date</label>
              <input
                type="date"
                id="date"
                name="date"
                value={date}
                onChange={(e) => setDate(e.target.value)} 
              />
            </div>

            <button type="submit" className="search-btn">
              Search Trains
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
