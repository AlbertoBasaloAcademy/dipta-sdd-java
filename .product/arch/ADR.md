# Architecture Decision Records (ADR) — AstroBookings

## ADR-1: Backend Framework Choice
- **Status**: Inferred
- **Decision**: Use Spring Boot 3.5.0 with Java 21 for the Backend.
- **Rationale**: Industry standard for robust, scalable REST APIs. Java 21 provides modern language features (Virtual Threads, etc.).
- **Consequences**: Fast development of REST endpoints; access to the vast Spring ecosystem.

## ADR-2: Frontend Framework Choice
- **Status**: Inferred
- **Decision**: Use Vue 3 as a Single Page Application (SPA).
- **Rationale**: Lightweight, reactive, and efficient for building interactive web interfaces.
- **Consequences**: Client-side rendering; separate build pipeline (npm/Vue CLI) from the backend.

## ADR-3: Storage Strategy for MVP
- **Status**: Inferred
- **Decision**: Use In-Memory storage (ConcurrentHashMap) instead of a physical database.
- **Rationale**: Simplifies initial development and deployment for the MVP phase.
- **Consequences**: Data is lost on application restart; scalability is limited; must be replaced with a real DB (e.g., PostgreSQL) for production.

## ADR-4: Backend Architecture Pattern
- **Status**: Inferred
- **Decision**: Traditional layered architecture (Controller -> Service -> Repository -> Model).
- **Rationale**: Clear separation of concerns; easy to understand and maintain for smaller teams.
- **Consequences**: Standardized flow of data; logic is centralized in Services.

## ADR-5: API Communication Style
- **Status**: Inferred
- **Decision**: RESTful API using JSON over HTTP.
- **Rationale**: Simple, widely supported, and easy to consume from the Vue 3 frontend.
- **Consequences**: Use of standard HTTP verbs (GET, POST, PUT, DELETE); status codes for error handling.

## ADR-6: Monorepo Organization
- **Status**: Inferred
- **Decision**: Store both Backend (`back/`) and Frontend (`front/`) in a single repository.
- **Rationale**: Simplifies versioning and coordination between API changes and UI updates.
- **Consequences**: Single point of truth; potential for larger repo size; requires careful CI/CD configuration to avoid full rebuilds.

## ADR-7: Native Fetch for Frontend API Calls
- **Status**: Inferred
- **Decision**: Use the native Browser `fetch` API instead of external libraries like Axios.
- **Rationale**: Reduces bundle size and dependency overhead; sufficient for simple REST interactions.
- **Consequences**: Manual handling of some HTTP nuances (e.g., response.ok check); less boilerplate for simple calls.
