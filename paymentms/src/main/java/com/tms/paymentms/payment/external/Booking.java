package com.tms.paymentms.payment.external;

import java.util.List;

public class Booking {
    private Long id;
    private Long userId;
    private List<Long> seatIds;
    private Long otp;
    private boolean verified;
    private Long amount;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public List<Long> getSeatIds() {
        return seatIds;
    }
    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }
    public Long getOtp() {
        return otp;
    }
    public void setOtp(Long otp) {
        this.otp = otp;
    }
    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
