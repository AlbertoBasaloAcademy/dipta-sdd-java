<template>
  <div class="booking-catalog">
    <h3 class="catalog-title">Passenger Bookings</h3>
    <div v-if="bookings.length === 0" class="empty-state glass-panel">
      No bookings found.
    </div>
    <div v-else class="booking-grid">
      <div v-for="booking in bookings" :key="booking.id" class="booking-card glass-panel">
        <div class="card-header">
          <div class="passenger-info">
            <h4 class="passenger-name">{{ booking.passengerName }}</h4>
            <div class="contact-details">
              <span>{{ booking.passengerEmail }}</span>
              <span>{{ booking.passengerPhone }}</span>
            </div>
          </div>
          <BookingStatusBadge :status="booking.status" />
        </div>
        
        <div class="card-body">
          <div class="launch-info">
            <span class="label-md">Launch Mission</span>
            <span class="value">{{ getLaunchInfo(booking.launchId) }}</span>
          </div>
        </div>

        <div v-if="booking.status === 'created'" class="card-actions">
          <button @click="onCancel(booking.id)" class="btn-error small">Cancel Booking</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BookingStatusBadge from './BookingStatusBadge.vue';
import { cancelBooking } from '../services/bookingService';

export default {
  components: { BookingStatusBadge },
  props: ['bookings', 'launches', 'rockets'],
  methods: {
    getLaunchInfo(launchId) {
      const launch = this.launches.find(l => l.id === launchId);
      if (!launch) return 'Unknown Launch';
      const rocket = this.rockets.find(r => r.id === launch.rocketId);
      const rocketName = rocket ? rocket.name : 'Unknown Rocket';
      const date = new Date(launch.launchTime).toLocaleString();
      return `${rocketName} - ${date}`;
    },
    async onCancel(id) {
      if (confirm('Are you sure you want to cancel this booking?')) {
        try {
          await cancelBooking(id);
          this.$emit('refresh');
        } catch (error) {
          alert('Error cancelling booking: ' + error.message);
        }
      }
    }
  }
}
</script>

<style scoped>
.booking-catalog {
  margin-top: 40px;
}

.catalog-title {
  text-align: left;
  margin-bottom: 24px;
  font-size: 28px;
}

.booking-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 24px;
}

.booking-card {
  text-align: left;
  transition: transform 0.3s ease;
}

.booking-card:hover {
  transform: translateY(-5px);
  background: rgba(56, 57, 63, 0.4);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 24px;
}

.passenger-name {
  margin: 0;
  font-size: 20px;
  color: var(--color-primary);
}

.contact-details {
  display: flex;
  flex-direction: column;
  font-size: 14px;
  color: var(--color-on-surface-variant);
  margin-top: 4px;
}

.card-body {
  margin-bottom: 24px;
}

.launch-info {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.label-md {
  font-size: 11px;
  color: var(--color-on-surface-variant);
  text-transform: uppercase;
  letter-spacing: 0.1em;
}

.value {
  font-family: var(--font-body);
  font-size: 16px;
}

.card-actions {
  display: flex;
  gap: 12px;
}

.btn-error {
  background-color: transparent;
  color: var(--color-error);
  border: 1px solid var(--color-error);
  padding: 8px 16px;
  border-radius: var(--rounded-md);
  cursor: pointer;
  font-family: var(--font-display);
  font-weight: 600;
  transition: all 0.3s ease;
}

.btn-error:hover {
  background-color: rgba(255, 180, 171, 0.1);
}

.small {
  font-size: 14px;
}

.empty-state {
  padding: 40px;
  text-align: center;
  color: var(--color-on-surface-variant);
  font-style: italic;
}
</style>
