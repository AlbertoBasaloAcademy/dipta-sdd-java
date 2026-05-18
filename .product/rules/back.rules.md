# Backend Conventions — AstroBookings

## Summary

The Backend tier is a Spring Boot application using Java 21. It follows a classic layered architecture (Controller -> Service -> Repository) with domain models. Data is stored in-memory using thread-safe collections (`ConcurrentHashMap`). Code style is standard Spring, using constructor injection and explicit error handling in controllers through `ResponseEntity`.

## Artifact Roles

### Model (Entity)

**Dominant pattern**: Plain Old Java Objects (POJOs) with a default constructor, a parameterized constructor, and standard getters/setters.

**Canonical example** (from `Booking.java`):
```java
public class Booking {
    private UUID id;
    private UUID launchId;
    private String passengerName;
    private BookingStatus status;

    public Booking() {}

    public Booking(UUID id, UUID launchId, String passengerName, BookingStatus status) {
        this.id = id;
        this.launchId = launchId;
        this.passengerName = passengerName;
        this.status = status;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    // ... other getters and setters
}
```

**Anti-pattern**:
- Using Lombok annotations (e.g. `@Data`, `@AllArgsConstructor`) — forbidden to keep dependencies minimal in MVP.

### Enum

**Dominant pattern**: PascalCase for enum name, but lowercase for members (unusual pattern).

**Canonical example** (from `LaunchStatus.java`):
```java
public enum LaunchStatus {
    created, confirmed, completed, cancelled
}
```

**Anti-pattern**:
- Standard Java `UPPER_SNAKE` for members (e.g. `CREATED`, `CANCELLED`) — deviates from current project style.

### Repository

**Dominant pattern**: In-memory data store using `ConcurrentHashMap` and `Optional` for finders.

**Canonical example** (from `BookingRepository.java`):
```java
@Repository
public class BookingRepository {
    private final Map<UUID, Booking> bookings = new ConcurrentHashMap<>();

    public List<Booking> findAll() {
        return new ArrayList<>(bookings.values());
    }

    public Optional<Booking> findById(UUID id) {
        return Optional.ofNullable(bookings.get(id));
    }

    public Booking save(Booking booking) {
        if (booking.getId() == null) {
            booking.setId(UUID.randomUUID());
        }
        bookings.put(booking.getId(), booking);
        return booking;
    }
}
```

**Anti-pattern**:
- Using `HashMap` instead of `ConcurrentHashMap` — risk of thread-safety issues in Spring environment.

### Service

**Dominant pattern**: Stateless Spring Service implementing business rules and validation.

**Canonical example** (from `BookingService.java`):
```java
@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Booking booking) {
        validateBooking(booking);
        booking.setStatus(BookingStatus.created);
        return bookingRepository.save(booking);
    }
}
```

**Anti-pattern**:
- Mixing data access logic in service — should delegate to repository.

### Controller

**Dominant pattern**: REST endpoints using `@RestController` and `@RequestMapping("/api/...")`, returning `ResponseEntity` for explicit status control.

**Canonical example** (from `BookingController.java`):
```java
@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable UUID id) {
        return service.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
```

**Anti-pattern**:
- Returning raw domain objects directly from methods without `ResponseEntity` for success/error orchestration.

## Wiring and Dependencies

- **Dependency Injection**: Always use **constructor injection** for required dependencies.
- **Cross-Service**: Services can inject other services if needed (e.g., `BookingService` injects `LaunchService`).

## Error Handling

- **Controllers**: Handle exceptions locally using `try-catch` and map them to appropriate HTTP status codes (e.g. `badRequest()`, `notFound()`).
- **Services**: Throw standard Java exceptions (`IllegalArgumentException`, `IllegalStateException`, `RuntimeException`) with descriptive messages.

## Known Deviations

- `LaunchController.java` — `createLaunch` method does not use `try-catch` for error handling, unlike `createBooking`. Expected: consistent use of `try-catch` for validation errors.
- `HealthController.java` — returns a simple `Map<String, String>` without a dedicated model or `ResponseEntity` wrapping.
