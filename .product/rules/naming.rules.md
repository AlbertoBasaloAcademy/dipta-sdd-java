# Naming Conventions — AstroBookings

## Domain Language

- **Business terms**: English (e.g. `Rocket`, `Launch`, `Booking`)
- **Technical terms / suffixes**: English (e.g. `Controller`, `Service`, `.vue`, `.js`, `.java`)
- **Code comments**: English
- **Commit messages**: English (Conventional Commits)

## Folders

### back
- **Pattern**: layer-based
- **Casing**: lowercase
- **Examples**: `controller`, `model`, `repository`, `service`

### front
- **Pattern**: layer/role-based
- **Casing**: lowercase
- **Examples**: `components`, `services`, `assets`

## Files

### back
- **Controllers**: `*Controller.java` — `BookingController.java`, `LaunchController.java`
- **Services**: `*Service.java` — `BookingService.java`, `RocketService.java`
- **Repositories**: `*Repository.java` — `BookingRepository.java`, `LaunchRepository.java`
- **Models**: `*.java` — `Booking.java`, `Rocket.java`
- **Tests**: `*Test.java` — `RocketControllerTest.java`, `BookingServiceTest.java`

### front
- **Components**: `*.vue` (PascalCase) — `BookingCatalog.vue`, `LaunchForm.vue`
- **Services**: `*Service.js` (camelCase) — `bookingService.js`, `rocketService.js`
- **Tests**: `*.spec.js` — `bookingService.spec.js`, `launchService.spec.js`

## Language Elements

### back (Java)
- **Classes / Records**: PascalCase — `Booking`, `LaunchService`
- **Methods**: camelCase — `getAllBookings`, `validateBooking`
- **Variables / Fields**: camelCase — `passengerName`, `launchRepository`
- **Constants**: UPPER_SNAKE — `EMAIL_PATTERN`
- **Enums**: PascalCase — `BookingStatus`, `LaunchStatus`
- **Enum members**: lowercase — `created`, `cancelled` (Note: Deviates from standard Java UPPER_SNAKE)

### front (JavaScript / Vue)
- **Components (Export)**: PascalCase — `BookingCatalog`, `BookingStatusBadge`
- **Functions**: camelCase — `getAllBookings`, `getLaunchInfo`
- **Variables**: camelCase — `response`, `rocketName`
- **Constants**: UPPER_SNAKE — `API_URL`
- **CSS Classes**: kebab-case — `booking-catalog`, `card-header`

## Detected Inconsistencies

- **Java Enum Members**: All enum members are lowercase (e.g., `BookingStatus.created`) instead of the standard Java `UPPER_SNAKE` (e.g., `CREATED`).
- **Service Naming**: Backend services use PascalCase file names (`BookingService.java`), while frontend services use camelCase (`bookingService.js`).
