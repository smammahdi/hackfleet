package com.tms.trainms.Train.DTO;

import java.util.List;

public class SeatStatusDTO
{
    private List<Long> seatIds;
    private String status;

    public List<Long> getSeatIds() 
    {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) 
    {
        this.seatIds = seatIds;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return this.status;
    }
}
