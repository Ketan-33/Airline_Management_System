package com.example.flights;

import com.example.flights.dto.FlightInfo;
import com.example.flights.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
@RequestMapping
public class FlightController {

    @Autowired
    FlightService flightService;
    @GetMapping
    public List<FlightInfo> getFlights() {
        return flightService.getFlights();
    }
    @GetMapping("/{id}")
    public FlightInfo getFlight(@PathVariable String id) {
        FlightInfo flight = flightService.getFlight(id);
        if (flight == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found");
        }
        return flight;
    }
    @PostMapping
    public FlightInfo addFlight(@RequestBody FlightInfo flightInfo) {
        return flightService.addFlight(flightInfo);
    }

}
