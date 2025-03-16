package com.example.flights.services;

import com.example.flights.dto.FlightInfo;
import com.example.flights.repository.FlightInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    FlightInfoRepository flightInfoRepository;

    public List<FlightInfo> getFlights() {
        return flightInfoRepository.getFlights();
    }

    public FlightInfo getFlight(String id) {
        return flightInfoRepository.getFlightById(id);
    }

    public FlightInfo addFlight(FlightInfo flightInfo) {
        return flightInfoRepository.createFlight(flightInfo);
    }
}
