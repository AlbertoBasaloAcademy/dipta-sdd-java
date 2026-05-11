---
plan-type: spec-implementation
tier: backend
---
## spec-implementation - launches - backend

### Step 1: Define Launch Data Model
Create the necessary classes to represent a Launch and its status in the system.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/model/Launch.java
    - back/src/main/java/academy/aicode/astrobookings/model/LaunchStatus.java
- [ ] Create LaunchStatus enum with values: created, confirmed, completed, cancelled.
- [ ] Create Launch entity class with fields: id, rocket_id, launch_time, price_per_ticket, minimum_occupancy, status.

### Step 2: Implement Persistence Layer
Create a repository to handle Launch data storage.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/repository/LaunchRepository.java
- [ ] Implement an in-memory LaunchRepository using ConcurrentHashMap, following the RocketRepository pattern.

### Step 3: Implement Business Logic
Create a service to manage launch operations and enforce business rules.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/service/LaunchService.java
- [ ] Implement createLaunch method (initial status must be 'created').
- [ ] Implement getAllLaunches and getLaunchById methods.
- [ ] Implement updateLaunch method with status lifecycle validation (if cancelled, prevent further updates).

### Step 4: Implement REST API
Expose launch management functionality via REST endpoints.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/controller/LaunchController.java
- [ ] Implement POST /api/launches.
- [ ] Implement GET /api/launches and GET /api/launches/{id}.
- [ ] Implement PATCH /api/launches/{id}.

### Step 5: Verification
Ensure the backend implementation works as expected.
- Paths:
    - back/src/test/java/academy/aicode/astrobookings/service/LaunchServiceTest.java
- [ ] Write unit tests for LaunchService covering creation, status updates, and cancellation rules.
- [ ] Run ./mvnw test to verify implementation.
