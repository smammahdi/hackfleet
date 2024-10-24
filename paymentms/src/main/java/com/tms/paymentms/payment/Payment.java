package com.tms.paymentms.payment;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Payment 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookingId;
    private Long amount;
    private List<String> passengerList;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return this.id;
    }

    public void setBookingId(Long bookingid)
    {
        this.bookingId = bookingid;
    }

    public Long getBookingId()
    {
        return this.bookingId;
    }

    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public Long getAmount()
    {
        return this.amount;
    }

    public void setPassengerList(List<String> passengerList)
    {
        this.passengerList = passengerList;
    }

    public List<String> getPassengerList()
    {
        return this.passengerList;
    }
}
