package com.tms.userms.user.dto;

public class TicketDTO 
{
    private Long ticketId;
    private Long userId;
    private String[] passengerList;
    private Long[] seatId;
    private Long amount;
    private Long bookingId;
    private Long paymentId;

    public Long getTicketId() 
    {
        return ticketId;
    }

    public void setTicketId(Long ticketId) 
    {
        this.ticketId = ticketId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public String[] getPassengerList() 
    {
        return passengerList;
    }

    public void setPassengerList(String[] passengerList) 
    {
        this.passengerList = passengerList;
    }

    public Long[] getSeatId() 
    {
        return seatId;
    }

    public void setSeatId(Long[] seatId) 
    {
        this.seatId = seatId;
    }

    public Long getAmount() 
    {
        return amount;
    }

    public void setAmount(Long amount) 
    {
        this.amount = amount;
    }

    public Long getBookingId() 
    {
        return bookingId;
    }

    public void setBookingId(Long bookingId) 
    {
        this.bookingId = bookingId;
    }

    public Long getPaymentId() 
    {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) 
    {
        this.paymentId = paymentId;
    }

    
}
