// src/components/Balance.js
import React, { useState, useEffect } from 'react';
import './Balance.css';
import Sidebar from './Sidebar';

const Balance = () => {
  const [balance, setBalance] = useState(0);
  const [paymentList, setPaymentList] = useState([]);
  const [addAmount, setAddAmount] = useState('');

  // Simulate an API call to fetch balance and payments (replace with actual API call later)
  useEffect(() => {
    const fetchedBalance = 5000; // Example balance
    const fetchedPayments = [
      {
        id: 'P001',
        bookingId: 'B001',
        passengerList: ['John Doe', 'Jane Doe'],
      },
      {
        id: 'P002',
        bookingId: 'B002',
        passengerList: ['Alice', 'Bob'],
      },
    ];

    setBalance(fetchedBalance);
    setPaymentList(fetchedPayments);
  }, []);

  const handleAddMoney = () => {
    const newBalance = balance + parseInt(addAmount, 10);
    setBalance(newBalance);
    setAddAmount('');
  };

  return (
    <div className="balance-container">
      <Sidebar isLoggedIn={true} /> {/* Sidebar remains fixed on the left */}
      <div className="content-area">
        <h1 align="center">Balance</h1>
        <div className="balance-page">
          <div className="balance-module">
            <h2>Your Balance</h2>
            <div className="balance-amount">৳ {balance}</div>

            <div className="add-money-section">
              <input
                type="number"
                placeholder="Enter amount"
                value={addAmount}
                onChange={(e) => setAddAmount(e.target.value)}
                className="add-money-input"
              />
              <button className="add-money-btn" onClick={handleAddMoney}>
                Add Money
              </button>
            </div>
          </div>

          <div className="payment-history-module">
            <h3>Payment History</h3>
            {paymentList.length === 0 ? (
              <p>No payments found</p>
            ) : (
              <ul className="payment-list">
                {paymentList.map((payment) => (
                  <li key={payment.id} className="payment-item">
                    <p>
                      <strong>Payment ID:</strong> {payment.id}
                    </p>
                    <p>
                      <strong>Booking ID:</strong> {payment.bookingId}
                    </p>
                    <p>
                      <strong>Passengers:</strong> {payment.passengerList.join(', ')}
                    </p>
                  </li>
                ))}
              </ul>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Balance;
