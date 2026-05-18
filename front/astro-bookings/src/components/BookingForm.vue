<template>
  <div class="booking-form glass-panel">
    <h3>Book a Flight</h3>
    <form @submit.prevent="submit" class="cosmic-form">
      <div class="form-group">
        <label for="launch-select" class="label-md">Select Launch</label>
        <select id="launch-select" v-model="form.launchId" required class="input-field">
          <option value="" disabled>Select a launch mission</option>
          <option v-for="launch in launches" :key="launch.id" :value="launch.id">
            Launch to {{ getRocketName(launch.rocketId) }} at {{ formatDate(launch.launchTime) }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label for="name-input" class="label-md">Passenger Name</label>
        <input id="name-input" type="text" v-model="form.passengerName" required class="input-field" placeholder="Full Name" />
      </div>
      <div class="form-group">
        <label for="email-input" class="label-md">Email Address</label>
        <input id="email-input" type="email" v-model="form.passengerEmail" required class="input-field" placeholder="email@example.com" />
      </div>
      <div class="form-group">
        <label for="phone-input" class="label-md">Phone Number</label>
        <input id="phone-input" type="tel" v-model="form.passengerPhone" required class="input-field" placeholder="+1 234 567 890" />
      </div>
      <div class="form-actions">
        <button type="submit" class="btn-primary">Book Now</button>
        <button type="button" @click="$emit('cancel')" class="btn-secondary">Cancel</button>
      </div>
    </form>
  </div>
</template>

<script>
import { createBooking } from '../services/bookingService';

export default {
  props: ['launches', 'rockets'],
  data() {
    return {
      form: {
        launchId: '',
        passengerName: '',
        passengerEmail: '',
        passengerPhone: '',
        status: 'created'
      }
    };
  },
  methods: {
    getRocketName(rocketId) {
      const rocket = this.rockets.find(r => r.id === rocketId);
      return rocket ? rocket.name : 'Unknown Rocket';
    },
    formatDate(dateStr) {
      if (!dateStr) return '';
      return new Date(dateStr).toLocaleString();
    },
    async submit() {
      try {
        await createBooking(this.form);
        this.$emit('success');
        this.resetForm();
      } catch (error) {
        alert('Error creating booking: ' + error.message);
      }
    },
    resetForm() {
      this.form = {
        launchId: '',
        passengerName: '',
        passengerEmail: '',
        passengerPhone: '',
        status: 'created'
      };
    }
  }
}
</script>

<style scoped>
.booking-form {
  margin-bottom: 40px;
  text-align: left;
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
}

h3 {
  margin-top: 0;
  margin-bottom: 24px;
  font-size: 24px;
}

.cosmic-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.label-md {
  color: var(--color-on-surface-variant);
  text-transform: uppercase;
  letter-spacing: 0.1em;
}

.form-actions {
  display: flex;
  gap: 16px;
  margin-top: 12px;
}
</style>
