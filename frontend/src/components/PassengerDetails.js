import React, { useState, useEffect } from 'react';
import './PassengerDetails.css';

const PassengerDetails = ({ numberOfPassengers = 1, loggedInUser = "Alema Sultana" }) => {
  // Create an initial array of passengers with default values
  const initialPassengers = Array.from({ length: numberOfPassengers }, (_, i) => ({
    name: i === 0 ? loggedInUser : "", // Automatically set the first passenger's name
    type: "Adult", // Default to Adult for all passengers
  }));

  const [passengers, setPassengers] = useState(initialPassengers);

  // Handle passenger name change
  const handleNameChange = (index, value) => {
    const updatedPassengers = [...passengers];
    updatedPassengers[index].name = value;
    setPassengers(updatedPassengers);
  };

  // Handle passenger type (Adult/Child) change
  const handleTypeChange = (index, value) => {
    const updatedPassengers = [...passengers];
    updatedPassengers[index].type = value;
    setPassengers(updatedPassengers);
  };

  // Handle form submission
  const handleProceed = () => {
    console.log("Passenger Details:", passengers);
    alert("Proceeding to the next step");
  };

  return (
    <div className="passenger-details-page">
      <h2>Passenger Details</h2>

      <div className="warnings">
        <p><strong>Please Note:</strong> Co-passengers' names (as given on their NID / photo ID) are mandatory to complete the ticket purchase process.</p>
        <p><strong>Note:</strong> Please complete the passenger details, review, and continue within 5 minutes, or the selected seat(s) will be released.</p>
      </div>

      <div className="timer">
        <h3>04:40</h3>
        <p>Remaining, to initiate your payment process</p>
      </div>

      {passengers.map((passenger, index) => (
        <div key={index} className="passenger-card">
          <h4>Passenger {index + 1}</h4>
          <div className="passenger-input">
            <label htmlFor={`name-${index}`}>Full Name</label>
            <input
              id={`name-${index}`}
              type="text"
              value={passenger.name}
              onChange={(e) => handleNameChange(index, e.target.value)}
              placeholder={index === 0 ? loggedInUser : "Full Name"}
              disabled={index === 0} // Disable editing for the first passenger (logged in user)
              required
            />
          </div>
          <div className="passenger-input">
            <label htmlFor={`type-${index}`}>Type</label>
            <select
              id={`type-${index}`}
              value={passenger.type}
              onChange={(e) => handleTypeChange(index, e.target.value)}
            >
              <option value="Adult">Adult</option>
              <option value="Child">Child</option>
            </select>
          </div>
        </div>
      ))}

      <div className="contact-info">
        <h4>Contact Information</h4>
        <input type="email" placeholder="Email" value="2105046@ugrad.cse.buet.ac.bd" readOnly />
        <input type="tel" placeholder="Phone" value="01746695655" readOnly />
      </div>

      <button className="proceed-btn" onClick={handleProceed}>PROCEED</button>
    </div>
  );
};

export default PassengerDetails;
