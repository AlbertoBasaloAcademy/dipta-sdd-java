<template>
  <div class="health-status glass-panel small">
    <div class="status-indicator" :class="statusClass"></div>
    <div class="status-content">
      <span class="label-md">System Status</span>
      <div class="status-info">
        <span class="status-text">{{ health.status }}</span>
        <span class="uptime" v-if="!loading">• {{ health.uptime }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import { getHealthStatus } from '../services/healthService';

export default {
  name: 'HealthStatus',
  data() {
    return {
      health: {
        status: 'UNKNOWN',
        uptime: 'N/A'
      },
      loading: true
    };
  },
  async created() {
    try {
      this.health = await getHealthStatus();
    } catch (e) {
      this.health = { status: 'DOWN', uptime: 'N/A' };
    } finally {
      this.loading = false;
    }
  },
  computed: {
    statusClass() {
      return {
        'up': this.health.status === 'UP',
        'down': this.health.status === 'DOWN',
        'unknown': this.health.status === 'UNKNOWN'
      };
    }
  }
};
</script>

<style scoped>
.health-status {
  padding: 8px 16px;
  display: flex;
  align-items: center;
  gap: 12px;
  max-width: fit-content;
  margin: 0;
  border-radius: var(--rounded-lg);
}

.status-indicator {
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background-color: var(--color-on-surface-variant);
}

.status-indicator.up {
  background-color: #00e3fd;
  box-shadow: 0 0 8px #00e3fd;
}

.status-indicator.down {
  background-color: var(--color-error);
  box-shadow: 0 0 8px var(--color-error);
}

.status-content {
  display: flex;
  flex-direction: column;
  text-align: left;
}

.label-md {
  font-size: 10px;
  color: var(--color-on-surface-variant);
  line-height: 1;
  margin-bottom: 2px;
}

.status-info {
  display: flex;
  align-items: center;
  gap: 6px;
  font-size: 12px;
  font-weight: 600;
}

.status-text {
  color: var(--color-on-surface);
}

.uptime {
  color: var(--color-on-surface-variant);
  font-weight: 400;
}

.glass-panel.small {
  background: rgba(255, 255, 255, 0.03);
  padding: 8px 16px;
}
</style>
