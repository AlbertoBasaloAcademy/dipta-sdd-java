---
plan-type: spec-implementation
tier: backend
---
## spec-implementation - booking - backend

### Step 1: Define Data Model
Create the Booking entity and its status enumeration.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/model/Booking.java
    - back/src/main/java/academy/aicode/astrobookings/model/BookingStatus.java
- [ ] Create BookingStatus enum with created and cancelled values.
- [ ] Create Booking class with id, launch_id, passenger_name, passenger_email, passenger_phone, and status fields.

### Step 2: Create Repository
Implement the repository interface for Booking persistence.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/repository/BookingRepository.java
- [ ] Create BookingRepository interface.

### Step 3: Implement Service Logic
Create the service to handle booking operations and business rules.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/service/BookingService.java
- [ ] Implement createBooking with validations: mandatory fields, email format, and launch capacity.
- [ ] Implement getAllBookings, getBookingById, and cancelBooking logic.

### Step 4: Expose API Endpoints
Create the REST controller to handle HTTP requests.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/controller/BookingController.java
- [ ] Implement POST /api/bookings.
- [ ] Implement GET /api/bookings.
- [ ] Implement GET /api/bookings/{id}.
- [ ] Implement PATCH /api/bookings/{id} for cancellation.
