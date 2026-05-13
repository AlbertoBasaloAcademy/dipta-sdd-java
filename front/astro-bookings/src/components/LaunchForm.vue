<template>
  <div class="launch-form glass-panel">
    <h3>{{ isEditing ? 'Edit Launch' : 'Schedule Launch' }}</h3>
    <form @submit.prevent="submit" class="cosmic-form">
      <div class="form-group">
        <label for="rocket-select" class="label-md">Rocket</label>
        <select id="rocket-select" v-model="form.rocketId" required :disabled="isEditing" class="input-field">
          <option v-for="rocket in rockets" :key="rocket.id" :value="rocket.id">
            {{ rocket.name }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label for="launch-time-input" class="label-md">Launch Time</label>
        <input id="launch-time-input" type="datetime-local" v-model="form.launchTime" required class="input-field" />
      </div>
      <div class="form-group">
        <label for="price-input" class="label-md">Price per Ticket</label>
        <input id="price-input" type="number" step="0.01" v-model.number="form.pricePerTicket" required class="input-field" />
      </div>
      <div class="form-group">
        <label for="occupancy-input" class="label-md">Min. Occupancy</label>
        <input id="occupancy-input" type="number" v-model.number="form.minimumOccupancy" required class="input-field" />
      </div>
      <div v-if="isEditing" class="form-group">
        <label for="status-select" class="label-md">Status</label>
        <select id="status-select" v-model="form.status" class="input-field">
          <option value="created">Created</option>
          <option value="confirmed">Confirmed</option>
          <option value="completed">Completed</option>
          <option value="cancelled">Cancelled</option>
        </select>
      </div>
      <div class="form-actions">
        <button type="submit" class="btn-primary">{{ isEditing ? 'Update' : 'Schedule' }}</button>
        <button type="button" @click="$emit('cancel')" class="btn-secondary">Cancel</button>
      </div>
    </form>
  </div>
</template>

<script>
import { createLaunch, updateLaunch } from '../services/launchService';

export default {
  props: ['launch', 'rockets'],
  data() {
    return {
      form: {
        rocketId: '',
        launchTime: '',
        pricePerTicket: 0,
        minimumOccupancy: 0,
        status: 'created'
      }
    };
  },
  computed: {
    isEditing() {
      return !!this.launch;
    }
  },
  watch: {
    launch: {
      immediate: true,
      handler(val) {
        if (val) {
          // Format date for datetime-local input (YYYY-MM-DDThh:mm)
          const date = new Date(val.launchTime);
          const formattedDate = date.toISOString().slice(0, 16);
          this.form = { ...val, launchTime: formattedDate };
        } else {
          this.form = {
            rocketId: this.rockets.length > 0 ? this.rockets[0].id : '',
            launchTime: '',
            pricePerTicket: 1000,
            minimumOccupancy: 1,
            status: 'created'
          };
        }
      }
    }
  },
  methods: {
    async submit() {
      try {
        if (this.isEditing) {
          await updateLaunch(this.launch.id, this.form);
        } else {
          await createLaunch(this.form);
        }
        this.$emit('success');
      } catch (error) {
        alert('Error saving launch');
      }
    }
  }
}
</script>

<style scoped>
.launch-form {
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
