---
spec-slug: booking
---
# Specification for Passenger Booking

## Problem definition

AstroBookings needs a way to record and manage passenger bookings for rocket launches. 
Currently, the system lacks the ability to book tickets, track passenger information, or manage booking statuses. 
This functionality is critical for revenue generation and passenger management.
No uder identity system is in place, anyone can book a ticket.

### User Stories

- As a passenger, I want to **book a ticket for a specific rocket launch** so that I can secure my seat for the flight.
- As an administrator, I want to **view booking details** (name, email, phone) to manage passenger communications and logistics.
- As a user, I want to **cancel my booking** if my plans change, allowing the seat to be available for others.

## Solution overview

### Data Model

- **Booking**: Represents a passenger's seat reservation for a specific launch.
    - id: uuid#
    - launch_id: uuid
    - passenger_name: string
    - passenger_email: string
    - passenger_phone: string
    - status: booking_status [created, cancelled]
    - launch: Launch [1:n]

### Backend API

- `POST /api/bookings`: Create a new booking for a launch. Validates launch existence and capacity.
- `GET /api/bookings`: Retrieve all bookings.
- `GET /api/bookings/{id}`: Retrieve details for a specific booking.
- `PATCH /api/bookings/{id}`: Update booking status (e.g., to `cancelled`).

### Frontend Application

- **Booking Form**: A component to collect passenger name, email, and phone number for a selected launch.
- **My Bookings View**: A page displaying the user's current and past bookings.
- **Booking Status Badge**: A component to visualize the current status of a booking (Created or Cancelled).

### Database Schema

- **bookings** table:
    - id (UUID, Primary Key)
    - launch_id (UUID, Foreign Key to launches)
    - passenger_name (Varchar)
    - passenger_email (Varchar)
    - passenger_phone (Varchar)
    - status (Varchar)

## Acceptance and Release

- [ ] WHEN a user submits a booking for a launch THEN THE [AstroBookings] SHALL record the passenger details and set the status to `created`.
- [ ] IF a user requests the list of bookings THEN THE [AstroBookings] SHALL return all recorded bookings.
- [ ] WHEN a booking status is updated to `cancelled` THEN THE [AstroBookings] SHALL persist the new status.
- [ ] THE [AstroBookings] SHALL require a valid `launch_id`, `passenger_name`, and `passenger_email` for every booking.
- [ ] IF the number of active bookings (status `created`) for a launch reaches the rocket's capacity THEN THE [AstroBookings] SHALL reject further bookings for that launch.
- [ ] THE [AstroBookings] SHALL validate that the `passenger_email` format is correct.
