package com.example.tickets;

import com.example.tickets.dto.TicketInfo;
import com.example.tickets.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping
public class TicketController {
    
    @Autowired
    TicketService ticketService;
    
    @GetMapping
    public List<TicketInfo> getAllTickets() {
        return ticketService.getTickets();
    }
    
    @GetMapping("/{id}")
    public TicketInfo getTicket(@PathVariable String id) {
        TicketInfo ticket = ticketService.getTicket(id);
        if (ticket == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }
        return ticket;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketInfo createTicket(@RequestBody TicketInfo ticketInfo) {
        try {
            return ticketService.createTicket(ticketInfo);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable String id) {
        TicketInfo ticket = ticketService.getTicket(id);
        if (ticket == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }
        ticketService.deleteTicket(id);
    }
}
