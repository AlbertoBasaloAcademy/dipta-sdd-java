# System Architecture — AstroBookings

## Overview

AstroBookings is a space tourism management system that allows customers to book orbital and lunar launches, and enables company staff to manage the rocket fleet and launch schedules. It is a brownfield project being evolved into a robust REST API with a modern web frontend.

## C4 Diagram — System Context

```mermaid
C4Context
  title AstroBookings System Context

  Person(customer, "Customer", "Wants to book a space flight")
  Person(staff, "Staff", "Manages rockets and launches")

  System(astrobookings, "AstroBookings", "Manages rocket fleet, launches, and passenger bookings")

  System_Ext(payment_gateway, "Payment Gateway", "Handles fictional financial transactions")

  Rel(customer, astrobookings, "Books flights and views status", "HTTPS/Web")
  Rel(staff, astrobookings, "Manages fleet and schedule", "HTTPS/Web")
  Rel(astrobookings, payment_gateway, "Processes payments", "HTTPS/JSON")
```

## C4 Diagram — Containers

```mermaid
C4Container
  title AstroBookings Containers

  Person(customer, "Customer")
  Person(staff, "Staff")

  Container_Boundary(astrobookings_boundary, "AstroBookings") {
    Container(web_app, "Web Application", "Vue 3, Vitest", "Provides the user interface for customers and staff")
    Container(api, "REST API", "Spring Boot 3.5.0, Java 21", "Handles business logic and data persistence")
  }

  System_Ext(payment_gateway, "Payment Gateway")

  Rel(customer, web_app, "Uses", "HTTPS")
  Rel(staff, web_app, "Uses", "HTTPS")
  Rel(web_app, api, "API calls", "REST/JSON")
  Rel(api, payment_gateway, "Processes payments", "REST/JSON")
```

## Containers — Detail

### Web Application (`front/astro-bookings/`)

- **Responsibility**: Provides the user interface for both customers (booking launches) and staff (managing the rocket fleet).
- **Technology**: Vue 3, Vitest for testing.
- **Constraints**: Must be responsive and communicate exclusively with the Backend API.

### REST API (`back/`)

- **Responsibility**: Implements the core business logic for fleet management, launch planning, and booking.
- **Technology**: Spring Boot 3.5.0, Java 21.
- **Constraints**: Currently uses in-memory storage (ConcurrentHashMap) as part of the MVP phase.

## Inter-container communication

| Source | Target | Protocol | Contract |
|--------|--------|----------|----------|
| Web Application | REST API | REST/JSON | Domain-specific endpoints (e.g., /api/rockets, /api/launches) |
| REST API | Payment Gateway | REST/JSON | Fictional payment processing contract |
