---
plan-type: spec-implementation
spec: ../specs/launches.spec.md
status: in-progress
tier: frontend
---
## spec-implementation - launches - frontend

### Step 1: Implement Launch Service
Create a service to handle API calls for launches.
- Paths:
    - front/astro-bookings/src/services/launchService.js
- [ ] Implement functions for getAllLaunches, getLaunchById, createLaunch, and updateLaunch using fetch.

### Step 2: Create Reusable Components
Build components to visualize launch information.
- Paths:
    - front/astro-bookings/src/components/LaunchStatusBadge.vue
- [ ] Create a badge component that colors itself based on the launch status.

### Step 3: Implement Launch Management UI
Create forms and lists to manage launches.
- Paths:
    - front/astro-bookings/src/components/LaunchForm.vue
    - front/astro-bookings/src/components/LaunchCatalog.vue
- [ ] Create a form to schedule and edit launches (requiring rocket_id selection).
- [ ] Create a list view to display all scheduled launches.

### Step 4: Integrate in Main Application
Expose launches functionality in the main UI.
- Paths:
    - front/astro-bookings/src/App.vue
- [ ] Update App.vue to include LaunchCatalog and LaunchForm.
- [ ] Add a way to toggle between Rockets and Launches management.

### Step 5: Verification
Ensure the frontend implementation works as expected.
- Paths:
    - front/astro-bookings/src/services/launchService.spec.js
- [ ] Write unit tests for launchService.
- [ ] Run npm run test to verify logic.
