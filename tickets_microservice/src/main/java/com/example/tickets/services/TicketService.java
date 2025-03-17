package com.example.tickets.services;

import com.example.tickets.dto.TicketInfo;
import com.example.tickets.repository.TicketInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    private TicketInfoRepository ticketInfoRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${flights.service.url}")
    private String flightsServiceUrl;
    
    public List<TicketInfo> getTickets() {
        return ticketInfoRepository.getTickets();
    }
    
    public TicketInfo getTicket(String id) {
        return ticketInfoRepository.getTicketById(id);
    }
    
    public TicketInfo createTicket(TicketInfo ticketInfo) {
        // Validate if flight exists before creating a ticket
        try {
            Object flight = restTemplate.getForObject(flightsServiceUrl + "/" + ticketInfo.getFlightId(), Object.class);
            if (flight == null) {
                throw new RuntimeException("Flight not found with id: " + ticketInfo.getFlightId());
            }
        } catch (RestClientException e) {
            throw new RuntimeException("Flight not found with id: " + ticketInfo.getFlightId());
        }
        
        // Generate ticket ID
        String ticketId = UUID.randomUUID().toString();
        TicketInfo ticketWithId = ticketInfo.toBuilder().ticketId(ticketId).build();
        return ticketInfoRepository.createTicket(ticketWithId);
    }

    public void deleteTicket(String id) {
        ticketInfoRepository.deleteTicket(id);
    }
}
