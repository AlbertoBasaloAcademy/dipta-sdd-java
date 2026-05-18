# Testing Conventions — AstroBookings

## Summary

The project uses JUnit 5 for backend testing and Vitest for frontend testing. The backend follows a mirrored folder structure for tests and covers models, services (unit tests with Mockito), and controllers (integration tests with MockMvc). The frontend currently focuses on testing service modules using Vitest and manual fetch mocking. Repositories are not explicitly tested as they are currently simple in-memory implementations.

## back

### Infrastructure

- **Framework**: JUnit 5
- **Runner**: `./mvnw test`
- **Config**: Standard Maven/Spring Boot defaults

### Placement and Naming

- **Placement**: Separate `src/test/java` folder with mirrored package structure.
- **File naming**: `*Test.java` (e.g. `BookingServiceTest.java`).
- **Test naming**: camelCase method names starting with `should` (e.g. `shouldCreateBookingWhenValid`).

### Setup

- **Services**: `@BeforeEach` method to manually instantiate the service with mocks created via Mockito's `mock(Class.class)`.
- **Controllers**: `@WebMvcTest` with `@Autowired MockMvc` and `@MockBean` for service dependencies.

### Mocking

- **What is mocked**: Service dependencies (via `@MockBean`) and Repository dependencies (via Mockito `mock`).
- **What is NOT mocked**: Simple POJO models and Enums.

### Canonical Example

(from `BookingServiceTest.java`):
```java
@Test
void shouldCreateBookingWhenValid() {
    UUID launchId = UUID.randomUUID();
    Launch launch = new Launch(launchId, UUID.randomUUID(), LocalDateTime.now(), 100.0, 5, LaunchStatus.created);
    
    Booking booking = new Booking();
    booking.setLaunchId(launchId);
    booking.setPassengerName("John Doe");
    booking.setPassengerEmail("john@example.com");

    when(launchService.getLaunchById(launchId)).thenReturn(Optional.of(launch));
    when(rocketService.getRocketById(any())).thenReturn(Optional.of(new Rocket()));
    when(bookingRepository.findAll()).thenReturn(Collections.emptyList());
    when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArgument(0));

    Booking created = bookingService.createBooking(booking);

    assertNotNull(created);
    assertEquals(BookingStatus.created, created.getStatus());
    verify(bookingRepository).save(booking);
}
```

### Coverage by Artifact Role

- **Model**: Yes — Basic validation and state logic (e.g. `RocketTest.java`).
- **Service**: Yes — Full business logic coverage including happy paths and expected exceptions.
- **Controller**: Yes — Integration tests verifying HTTP status codes and JSON response structure.
- **Repository**: No — Trivial in-memory implementations are not tested.

## front

### Infrastructure

- **Framework**: Vitest
- **Runner**: `npm run test`
- **Config**: Standard Vitest configuration.

### Placement and Naming

- **Placement**: Colocated with source in the same folder.
- **File naming**: `*.spec.js` (e.g. `bookingService.spec.js`).
- **Test naming**: `describe` block for the artifact, `it` blocks in English for specific behaviors.

### Setup

- **Services**: Global `fetch` is mocked using `vi.fn()` and cleared in `beforeEach`.

### Mocking

- **What is mocked**: Native `fetch` API using `vi.fn()`.
- **What is NOT mocked**: Pure utility functions if they were present.

### Canonical Example

(from `bookingService.spec.js`):
```javascript
describe('bookingService', () => {
  beforeEach(() => {
    fetch.mockClear()
  })

  it('fetches bookings successfully', async () => {
    const bookings = [{ id: '1', passengerName: 'John Doe', status: 'created' }]
    fetch.mockResolvedValue({
      ok: true,
      json: () => Promise.resolve(bookings)
    })

    const result = await getAllBookings()
    expect(result).toEqual(bookings)
    expect(fetch).toHaveBeenCalledWith('http://localhost:8080/api/bookings')
  })
})
```

### Coverage by Artifact Role

- **Service**: Yes — Verifies API calls and data transformation.
- **Component**: No — No component tests (unit or snapshot) are currently implemented.

## What NOT to Test

- **Repositories**: Excluded because they are simple wrappers around `ConcurrentHashMap`.
- **Vue Components**: Currently excluded from the test suite, focusing only on service logic.
