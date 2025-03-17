package com.example.tickets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TicketInfo {
    private String ticketId;
    private String flightId;
    private String passengerName;
    private String passengerPhone;
    private String seatNumber;
    private double price;
}

