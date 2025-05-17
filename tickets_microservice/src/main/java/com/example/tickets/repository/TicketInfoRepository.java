package com.example.tickets.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.example.tickets.dto.TicketInfo;

import java.util.*;

@Component
public class TicketInfoRepository {
    Map<String, TicketInfo> ticketInfoMap;
    
    @PostConstruct
    public void init() {
        ticketInfoMap = new HashMap<>();
    }

    public List<TicketInfo> getTickets() {
        return new ArrayList<>(ticketInfoMap.values());
    }
    
    public TicketInfo getTicketById(String id) {
        return this.ticketInfoMap.get(id);
    }
    
    public TicketInfo createTicket(TicketInfo ticketInfo) {
        String ticketId = UUID.randomUUID().toString();
        TicketInfo ticketInfoWithId = ticketInfo.toBuilder().ticketId(ticketId).build();
        this.ticketInfoMap.put(ticketId, ticketInfoWithId);
        return ticketInfoWithId;
    }

    public void deleteTicket(String id) {
        ticketInfoMap.remove(id);
    }
}
