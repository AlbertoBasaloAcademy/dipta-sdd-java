# Quality Report: Bookings Feature

## Structural complexity

| File | Issue | Description | Recommendation | Severity |
|------|-------|-------------|----------------|----------|
| `BookingService.java` | Inefficient capacity check | Fetches all bookings from the repository and filters them in memory to count active bookings for a launch. | Add a `countByLaunchIdAndStatus` method to `BookingRepository` to perform the count at the data source level. | Medium |
| `BookingController.java` | Brittle error handling | Uses string comparison on exception messages to determine the HTTP response status. | Use custom exception types and/or a `@ControllerAdvice` for cleaner, type-safe error handling. | Low |
| `BookingCatalog.vue` | Logic in template | `getLaunchInfo` performs multiple lookups and formatting directly from the template. | Move the logic to a computed property or pre-process the bookings data to include launch/rocket info. | Low |
| `BookingForm.vue` | Logic in template | `getRocketName` and `formatDate` are called within the template for each option. | Pre-process the launches list to include the formatted label. | Low |

## Naming and readability

| File | Issue | Description | Recommendation | Severity |
|------|-------|-------------|----------------|----------|
| `BookingStatus.java` | Non-standard enum naming | Enum constants `created` and `cancelled` are lowercase. | While consistent with the project's `LaunchStatus`, standard Java convention is `UPPER_CASE`. Consider a project-wide refactor if desired. | Very Low |
| `BookingController.java` | Lack of error details | `ResponseEntity.badRequest().build()` returns an empty body, giving no feedback on why the request failed. | Return a structured error response with a message (e.g., "Launch is full"). | Medium |

## Redundancy

| File | Issue | Description | Recommendation | Severity |
|------|-------|-------------|----------------|----------|
| `bookingService.js` | Hardcoded API URL | The backend URL is hardcoded as `http://localhost:8080/api`. | Move the base API URL to an environment variable or a configuration file. | Low |

> Report generated on May 18, 2026.
