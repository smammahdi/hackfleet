// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Dashboard from './components/Dashboard';
import Login from './components/Login';
import Register from './components/Register';
import OTPVerification from './components/OTPVerification';
import PassengerDetails from './components/PassengerDetails';
import SeatSelection from './components/SeatSelection';
import TrainSearchResults from './components/TrainSearch';
import Balance from './components/Balance';
import TicketPage from './components/TicketPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />   {/* Default to login */}
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/booking" element={<Dashboard />} />
        <Route path="/otp-verification" element={<OTPVerification />} />
        <Route path="/passenger-details" element={<PassengerDetails />} />
        <Route path="/seat-selection" element={<SeatSelection />} />
        <Route path="/results" element={<TrainSearchResults />} />
        <Route path="/tickets" element={<TicketPage />} /> 
        <Route path="/balance" element={<Balance />} />
      </Routes>
    </Router>
  );
}

export default App;
