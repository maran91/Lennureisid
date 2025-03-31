# Flight Booking API

This is a Spring Boot API for managing flight bookings, retrieving available flights, and filtering seats based on preferences.

---

## 🚀 Project Setup

### Requirements

- Java 21  
- Spring Boot 3.4.3  
- MySQL  
- Docker

---
## 🚀 Getting Started

### 🐳 Start the project using Docker

```bash
docker-compose up --build
```
Make sure you have Docker & Docker Compose installed.

🛑 Stop the project
```bash
docker-compose down
```
## 📦 API Endpoints

### 🎟️ Create Customer and Book Seats

**POST** `/api/customer`

Creates a new customer and assigns them selected seats for a flight.

#### 🔸 Request Body

```json
{
  "firstName": "Alice",
  "lastName": "Smith",
  "email": "alice@example.com",
  "flightId": 5,
  "seatIds": [101, 102]
}
```

🔸 Response Example
```json
{
  "id": 1,
  "firstName": "Alice",
  "lastName": "Smith",
  "email": "alice@example.com",
  "flight": {
    "id": 5,
    "departure": "Tartu",
    "arrival": "Paris",
    "departureTime": "2025-04-04T11:20:00",
    "arrivalTime": "2025-04-04T13:21:00",
    "price": 196,
    "flightDuration": "PT2H1M"
  },
  "bookedSeats": [
    {
      "rowName": "A",
      "seatNumber": 1,
      "windowSeat": true,
      "extraLegRoom": true,
      "nearExit": true
    },
    {
      "rowName": "A",
      "seatNumber": 2,
      "windowSeat": false,
      "extraLegRoom": false,
      "nearExit": false
    }
  ]
}
```
✈️ Get Flight by ID
GET /api/flight/{id}

Retrieves a flight by its unique ID.

🔸 Response Example
```json

{
  "id": 5,
  "departure": "Tartu",
  "arrival": "Paris",
  "departureTime": "2025-04-04T11:20:00",
  "arrivalTime": "2025-04-04T13:21:00",
  "price": 196,
  "flightDuration": "PT2H1M",
  "flightSeats": [ ... ]
}
```

🔍 Search Flights
GET /api/flight

Retrieve a list of flights based on filter criteria.

🔸 Query Parameters (all optional)
departure: filter by departure city

departureDate: filter by date (yyyy-MM-dd)

priceMin: minimum price

priceMax: maximum price

durationFrom: minimum flight duration (PT1H, PT3H30M, etc.)

durationTo: maximum flight duration

🔸 Example
GET /api/flight?departure=Tartu&priceMax=300

🔸 Response Example
```json

[
  {
    "id": 5,
    "departure": "Tartu",
    "arrival": "Paris",
    "departureTime": "2025-04-04T11:20:00",
    "arrivalTime": "2025-04-04T13:21:00",
    "price": 196,
    "flightDuration": "PT2H1M"
  }
]
```
💺 Get All Flight Seats for a Flight
GET /api/flight-seats?flightId=5

Returns all seats (booked and available) for a specific flight.

🔸 Response Example
```json

[
  {
    "id": 101,
    "seat": {
      "id": 1,
      "rowName": "A",
      "seatNumber": 1,
      "windowSeat": true,
      "extraLegRoom": true,
      "nearExit": true
    },
    "booked": false
  },
  ...
]
```
🧠 Filter Flight Seats by Preferences
POST /api/flight-seats/filter

Returns a list of unbooked seats that match the provided preferences and flight ID.

🔸 Request Body
```json

{
  "flightId": "5",
  "windowSeat": "true",
  "extraLegRoom": "true",
  "nearExit": "false",
  "amount": "2"
}
🔸 Successful Response
```

```json

{
  "success": true,
  "message": "Seats found",
  "seats": [
    {
      "id": 161,
      "seat": {
        "id": 4,
        "rowName": "D",
        "seatNumber": 4,
        "windowSeat": true,
        "extraLegRoom": true,
        "nearExit": false
      },
      "booked": false
    },
    {
      "id": 162,
      "seat": {
        "id": 5,
        "rowName": "A",
        "seatNumber": 5,
        "windowSeat": true,
        "extraLegRoom": true,
        "nearExit": false
      },
      "booked": false
    }
  ]
}
```
🔸 Not Enough Seats Response
```json

{
  "success": false,
  "message": "Not enough seats available. Requested: 2, Found: 1",
  "seats": [
    {
      "id": 161,
      "seat": {
        "id": 4,
        "rowName": "D",
        "seatNumber": 4,
        "windowSeat": true,
        "extraLegRoom": true,
        "nearExit": false
      },
      "booked": false
    }
  ]
}
```
