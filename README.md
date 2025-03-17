# Airline Management System

This project consists of two microservices that manage flight information and ticket bookings.

## Architecture Overview

The system consists of two primary microservices:
1. **Flights Microservice** - Manages flight information and schedules
2. **Tickets Microservice** - Handles ticket bookings and passenger information

## Flights Microservice

### Service Details
- **Port**: 9055
- **Context Path**: /flights
- **Base URL**: http://localhost:9055/flights


### FlightInfo
| Field         | Type          | Description                    |
|---------------|---------------|--------------------------------|
| id            | String        | Unique identifier for the flight |
| airline       | String        | Name of the airline            |
| origin        | String        | Departure airport/city         |
| destination   | String        | Arrival airport/city           |
| departureTime | LocalDateTime | Scheduled departure time       |
| arrivalTime   | LocalDateTime | Scheduled arrival time         |
| price         | double        | Base price of the flight       |

### API Endpoints

| Method | Endpoint            | Description                           | Parameters                |
|--------|--------------------|---------------------------------------|---------------------------|
| GET    | /                  | Get all flights                       | sort (optional): asc/desc to sort by departure time |
| GET    | /{id}              | Get flight details by ID              | id: flight ID             |
| POST   | /                  | Add a new flight                      | FlightInfo object in body |

## Tickets Microservice

### Service Details
- **Port**: 9056
- **Context Path**: /tickets
- **Base URL**: http://localhost:9056/tickets


### TicketInfo
| Field          | Type   | Description                      |
|----------------|--------|----------------------------------|
| ticketId       | String | Unique identifier for the ticket |
| flightId       | String | Reference to the flight          |
| passengerName  | String | Name of the passenger            |
| passengerPhone | String | Contact number of the passenger  |
| seatNumber     | String | Assigned seat number             |
| price          | double | Price of the ticket              |

### API Endpoints

| Method | Endpoint        | Description                      | Parameters                 |
|--------|----------------|----------------------------------|----------------------------|
| GET    | /              | Get all tickets                  | None                       |
| GET    | /{id}          | Get ticket details by ID         | id: ticket ID              |
| POST   | /              | Create a new ticket booking      | TicketInfo object in body  |
| DELETE | /{id}          | Cancel/delete a ticket           | id: ticket ID              |

## Service Interactions

- **Ticket Creation**: When creating a ticket, the Tickets Microservice validates that the referenced flight exists by querying the Flights Microservice
- **Integration Point**: Tickets Microservice uses RestTemplate to communicate with the Flights Microservice
- **Configuration**: The Flights Microservice URL is configured in the Tickets Microservice's properties: `flights.service.url=http://localhost:9055/flights`

## Running the Application

1. Start the Flights Microservice first
2. Start the Tickets Microservice second
3. Use any REST client (like Postman, cURL, etc.) to interact with the endpoints
