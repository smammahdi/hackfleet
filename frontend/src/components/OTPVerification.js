import React, { useState, useEffect } from 'react';
import './OTPVerification.css';
import { useNavigate } from 'react-router-dom';

const OTPVerification = () => {
  const [otp, setOtp] = useState(new Array(4).fill(""));
  const [timer, setTimer] = useState(300); // 5 minutes countdown timer

  // Function to handle OTP input
  const handleOtpChange = (element, index) => {
    if (isNaN(element.value)) return;
    
    const newOtp = [...otp];
    newOtp[index] = element.value;
    setOtp(newOtp);

    // Move to next input
    if (element.nextSibling) {
      element.nextSibling.focus();
    }
  };

  // Countdown timer
  useEffect(() => {
    const interval = setInterval(() => {
      if (timer > 0) {
        setTimer(timer - 1);
      }
    }, 1000);

    return () => clearInterval(interval);
  }, [timer]);

  // Format timer display (MM:SS)
  const formatTime = (time) => {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
  };

  // Simulate OTP verification
  const navigate = useNavigate();
  const handleVerify = () => {
    const numberOfSeats = 4; // Dynamically set the number of seats chosen
    navigate(`/passenger-details?seats=${numberOfSeats}`);
  };

  return (
    <div className="otp-verification-page">
      <div className="otp-header">
        <h2>Verification</h2>
      </div>

      <div className="otp-warning">
        <p>
          <strong>OTP delivery may be delayed</strong> due to congestion at telco or SMS Gateway's end.
        </p>
      </div>

      <div className="otp-timer">
        <h3>{formatTime(timer)}</h3>
        <p>Remaining, to initiate your payment process</p>
      </div>

      <div className="otp-input-section">
        <p>Verify Your Phone Number</p>
        <div className="otp-code-input">
          {otp.map((digit, index) => (
            <input
              key={index}
              type="text"
              maxLength="1"
              value={digit}
              onChange={(e) => handleOtpChange(e.target, index)}
              onFocus={(e) => e.target.select()}
            />
          ))}
        </div>
        <div className="otp-footer">
          <p>Please enter the 4-digit code you received on your mobile.</p>
        </div>
        <button className="verify-btn" onClick={handleVerify}>
          VERIFY
        </button>
        <p className="resend-link">
          <span>01:13</span> <a href="#">Resend OTP</a>
        </p>
      </div>
    </div>
  );
};

export default OTPVerification;
