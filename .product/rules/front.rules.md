# Frontend Conventions — AstroBookings

## Summary

The Frontend tier is a Vue 3 Single Page Application using the Options API. It follows a modular structure where UI logic is encapsulated in components and data fetching is handled by dedicated service modules. Communication between components relies on props for data passing and events for actions. Styling is managed via scoped CSS using a global design system based on CSS variables and glassmorphism.

## Artifact Roles

### Component

**Dominant pattern**: Vue SFC (Single File Component) using the **Options API**. Components use `props` for inputs and `$emit` for outputs.

**Canonical example** (from `BookingForm.vue`):
```vue
<template>
  <div class="booking-form glass-panel">
    <h3>Book a Flight</h3>
    <form @submit.prevent="submit">
      <input v-model="form.passengerName" required placeholder="Full Name" />
      <button type="submit">Book Now</button>
    </form>
  </div>
</template>

<script>
import { createBooking } from '../services/bookingService';

export default {
  props: ['launches'],
  data() {
    return {
      form: { passengerName: '', status: 'created' }
    };
  },
  methods: {
    async submit() {
      try {
        await createBooking(this.form);
        this.$emit('success');
      } catch (error) {
        alert('Error: ' + error.message);
      }
    }
  }
}
</script>
```

**Anti-pattern**:
- Using the Composition API (`setup()` or `<script setup>`) — forbidden for consistency with existing Options API codebase.

### Service

**Dominant pattern**: Stateless JavaScript modules exporting asynchronous functions that use the native `fetch` API.

**Canonical example** (from `launchService.js`):
```javascript
const API_URL = 'http://localhost:8080/api';

export async function getAllLaunches() {
  const response = await fetch(`${API_URL}/launches`);
  if (!response.ok) throw new Error('Failed to fetch launches');
  return await response.json();
}

export async function createLaunch(launch) {
  const response = await fetch(`${API_URL}/launches`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(launch)
  });
  if (!response.ok) throw new Error('Failed to create launch');
  return await response.json();
}
```

**Anti-pattern**:
- Using external libraries like `axios` — keep dependencies minimal as per MVP constraints.

## Wiring and Dependencies

- **Component Communication**: Use **props** for down-flow and **events** (`$emit`) for up-flow.
- **Service Injection**: Services are imported directly into components as needed.

## Error Handling

- **Services**: Throw `Error` objects with descriptive messages when `fetch` response is not `ok`.
- **Components**: Use `try-catch` in methods that call services, displaying errors via `alert()` or UI notifications.

## Known Deviations

- `rocketService.js` — uses `http://localhost:8080` as `API_URL`, while other services use `http://localhost:8080/api`. Expected: consistent use of `/api` prefix.
- `HealthStatus.vue` — uses a polling mechanism (`setInterval`) to fetch health status, which is not used elsewhere.
- `App.vue` — contains global state and orchestration logic that might be better suited for a store if the application grows.
