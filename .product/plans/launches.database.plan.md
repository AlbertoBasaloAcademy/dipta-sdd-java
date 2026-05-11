---
plan-type: spec-implementation
tier: database
---
## spec-implementation - launches - database

### Step 1: Define launches table schema
Document the table structure and relationships for future implementation.
- Paths:
    - .product/specs/launches.spec.md
- [ ] Define launches table: id (UUID PK), rocket_id (UUID FK), launch_time (TIMESTAMP), price_per_ticket (DECIMAL), minimum_occupancy (INT), status (VARCHAR).
- [ ] Ensure rocket_id has a foreign key constraint to rockets(id).
- [ ] For MVP: Use in-memory ConcurrentHashMap in LaunchRepository.
