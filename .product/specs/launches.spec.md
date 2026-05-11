---
spec-slug: launches
---
# Specification for Rocket Launches Management

## Problem definition

AstroBookings needs a way to schedule and manage rocket launches. Currently, the system lacks the ability to track when launches occur, their pricing, minimum occupancy requirements, or their operational status. This functionality is essential for planning missions and managing passenger bookings.

### User Stories

- As a launch coordinator, I want to **schedule a new rocket launch** so that I can make it available for future bookings.
- As a launch manager, I want to **manage the lifecycle of a launch** by updating its status (from created to confirmed, completed, or cancelled).
- As an operator, I want to **view and update launch details** like price and minimum occupancy to ensure the launch is commercially viable.

## Solution overview

### Data Model

- **Launch**: Represents a scheduled space flight for a specific rocket.
    - id: uuid#
    - rocket_id: uuid
    - launch_time: datetime
    - price_per_ticket: decimal
    - minimum_occupancy: integer [1, 99]
    - status: launch_status [created, confirmed, completed, cancelled]
    - rocket: Rocket [1:n]

### Backend API

- `POST /api/launches`: Create a new rocket launch.
- `GET /api/launches`: Retrieve a list of all scheduled launches.
- `GET /api/launches/{id}`: Retrieve details for a specific launch.
- `PATCH /api/launches/{id}`: Update launch details or change its status.

### Frontend Application

- **Launches View**: A page displaying a list of all rocket launches.
- **Launch Form**: A component to create a new launch or edit an existing one.
- **Launch Status Badge**: A component to visualize the current status of a launch.

### Database Schema

- **launches** table:
    - id (UUID, Primary Key)
    - rocket_id (UUID, Foreign Key to rockets)
    - launch_time (Timestamp)
    - price_per_ticket (Decimal)
    - minimum_occupancy (Integer)
    - status (Varchar/Enum)

## Acceptance and Release

- [ ] WHEN a launch is scheduled with valid data THEN THE [AstroBookings] SHALL set its initial status to `created`.
- [ ] IF a user requests the list of launches THEN THE [AstroBookings] SHALL return all recorded launches.
- [ ] WHEN a launch status is updated THEN THE [AstroBookings] SHALL persist the new status.
- [ ] IF the status is changed to `cancelled` THEN THE [AstroBookings] SHALL prevent further updates to the launch details.
- [ ] THE [AstroBookings] SHALL require a valid `rocket_id` for every scheduled launch.
