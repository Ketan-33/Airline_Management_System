package com.example.flights.repository;

import com.example.flights.dto.FlightInfo;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.util.*;

@Component
public class FlightInfoRepository {
    Map<String, FlightInfo> flightInfoMap;

    @PostConstruct
    public void init() {
        flightInfoMap = new HashMap<>();
    }

    public List<FlightInfo> getFlights() {
        return new ArrayList<>(flightInfoMap.values());
    }

    public FlightInfo createFlight(FlightInfo flightInfo) {
        String flightId = UUID.randomUUID().toString();
        FlightInfo flightInfoWithId = flightInfo.toBuilder().id(flightId).build();
        this.flightInfoMap.put(flightId, flightInfoWithId);
        return flightInfoWithId;
    }
    
    public FlightInfo getFlightById(String id) {
        return this.flightInfoMap.get(id);
    }
}
