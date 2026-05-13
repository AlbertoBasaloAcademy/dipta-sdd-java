<template>
  <div class="launch-catalog">
    <h3 class="catalog-title">Scheduled Launches</h3>
    <div class="launch-grid">
      <div v-for="launch in launches" :key="launch.id" class="launch-card glass-panel">
        <div class="card-header">
          <div class="time-info">
            <span class="label-md">Launch Window</span>
            <h4 class="launch-time">{{ formatDate(launch.launchTime) }}</h4>
          </div>
          <LaunchStatusBadge :status="launch.status" />
        </div>
        
        <div class="card-body">
          <div class="info-row">
            <div class="info-item">
              <span class="label-md">Rocket</span>
              <span class="value">{{ getRocketName(launch.rocketId) }}</span>
            </div>
            <div class="info-item">
              <span class="label-md">Ticket Price</span>
              <span class="value accent">${{ launch.pricePerTicket }}</span>
            </div>
          </div>
          <div class="info-item">
            <span class="label-md">Min. Occupancy</span>
            <span class="value">{{ launch.minimumOccupancy }} passengers required</span>
          </div>
        </div>

        <div class="card-actions">
          <button @click="$emit('edit', launch)" class="btn-secondary small">Edit Mission</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LaunchStatusBadge from './LaunchStatusBadge.vue';

export default {
  components: { LaunchStatusBadge },
  props: ['launches', 'rockets'],
  methods: {
    getRocketName(rocketId) {
      const rocket = this.rockets.find(r => r.id === rocketId);
      return rocket ? rocket.name : 'Unknown Rocket';
    },
    formatDate(dateString) {
      if (!dateString) return '';
      const date = new Date(dateString);
      return date.toLocaleString();
    }
  }
}
</script>

<style scoped>
.launch-catalog {
  margin-top: 40px;
}

.catalog-title {
  text-align: left;
  margin-bottom: 24px;
  font-size: 28px;
}

.launch-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 24px;
}

.launch-card {
  text-align: left;
  transition: transform 0.3s ease;
}

.launch-card:hover {
  transform: translateY(-5px);
  background: rgba(56, 57, 63, 0.4);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 24px;
}

.launch-time {
  margin: 4px 0 0 0;
  font-size: 18px;
  color: var(--color-primary);
}

.card-body {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-bottom: 24px;
}

.info-row {
  display: flex;
  gap: 40px;
}

.info-item {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.info-item .label-md {
  font-size: 11px;
  color: var(--color-on-surface-variant);
}

.info-item .value {
  font-family: var(--font-body);
  font-size: 16px;
}

.info-item .value.accent {
  color: var(--color-secondary);
  font-weight: 600;
}

.card-actions {
  display: flex;
  gap: 12px;
}

.small {
  padding: 8px 16px;
  font-size: 14px;
}
</style>
