// src/components/TicketPage.js
import React, { useState, useEffect } from 'react';
import './TicketPage.css';
import Sidebar from './Sidebar'; // Import Sidebar component

const TicketPage = () => {
  const [tickets, setTickets] = useState([]);

  useEffect(() => {
    // Simulate an API call to fetch tickets data
    const fetchedTickets = [
      {
        ticketId: 101,
        userId: 1001,
        passengerList: ['John Doe', 'Jane Doe'],
        seatId: [1, 2],
        amount: 5000,
        bookingId: 201,
        paymentId: 301,
      },
      {
        ticketId: 102,
        userId: 1002,
        passengerList: ['Alice', 'Bob'],
        seatId: [5, 6],
        amount: 4000,
        bookingId: 202,
        paymentId: 302,
      },
    ];

    setTickets(fetchedTickets);
  }, []);

  return (
    <div className="ticket-page-container">
      <Sidebar isLoggedIn={true} /> {/* Include the Sidebar */}

      <div className="ticket-content">
        <h1>My Tickets</h1>
        <div className="ticket-list">
          {tickets.map((ticket) => (
            <div key={ticket.ticketId} className="ticket-card">
              <h3>Ticket ID: {ticket.ticketId}</h3>
              <p><strong>Passengers:</strong> {ticket.passengerList.join(', ')}</p>
              <p><strong>Seat IDs:</strong> {ticket.seatId.join(', ')}</p>
              <p><strong>Amount:</strong> ৳ {ticket.amount}</p>
              <p><strong>Booking ID:</strong> {ticket.bookingId}</p>
              <p><strong>Payment ID:</strong> {ticket.paymentId}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default TicketPage;
