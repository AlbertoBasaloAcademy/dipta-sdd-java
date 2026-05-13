<template>
  <div class="rocket-catalog">
    <h3 class="catalog-title">Rocket Fleet</h3>
    <div class="rocket-grid">
      <div v-for="rocket in rockets" :key="rocket.id" class="rocket-card glass-panel" :class="{ decommissioned: rocket.decommissioned }">
        <div class="card-header">
          <h4 class="rocket-name">{{ rocket.name }}</h4>
          <span :class="['status-badge', rocket.decommissioned ? 'badge-decommissioned' : 'badge-operational']">
            {{ rocket.decommissioned ? 'Decommissioned' : 'Operational' }}
          </span>
        </div>
        
        <div class="card-body">
          <div class="info-item">
            <span class="label-md">Capacity</span>
            <span class="value">{{ rocket.capacity }} passengers</span>
          </div>
          <div class="info-item">
            <span class="label-md">Destination</span>
            <span class="value">{{ rocket.range }}</span>
          </div>
        </div>

        <div class="card-actions">
          <button @click="$emit('edit', rocket)" class="btn-secondary small">Update</button>
          <button v-if="!rocket.decommissioned" @click="decommission(rocket.id)" class="btn-outline small">Decommission</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { decommissionRocket } from '../services/rocketService';

export default {
  props: ['rockets'],
  methods: {
    async decommission(id) {
      try {
        await decommissionRocket(id);
        this.$emit('refresh');
      } catch (error) {
        alert('Error decommissioning rocket');
      }
    }
  }
}
</script>

<style scoped>
.rocket-catalog {
  margin-top: 40px;
}

.catalog-title {
  text-align: left;
  margin-bottom: 24px;
  font-size: 28px;
}

.rocket-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 24px;
}

.rocket-card {
  text-align: left;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.rocket-card:hover {
  transform: translateY(-5px);
  background: rgba(56, 57, 63, 0.4);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 20px;
}

.rocket-name {
  margin: 0;
  font-size: 20px;
  color: var(--color-primary);
}

.status-badge {
  font-size: 12px;
  padding: 4px 8px;
  border-radius: var(--rounded-md);
  text-transform: uppercase;
  font-weight: 600;
}

.badge-operational {
  background: rgba(0, 227, 253, 0.1);
  color: var(--color-secondary);
}

.badge-decommissioned {
  background: rgba(255, 255, 255, 0.05);
  color: var(--color-on-surface-variant);
}

.card-body {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 24px;
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

.card-actions {
  display: flex;
  gap: 12px;
}

.btn-outline {
  background: transparent;
  border: 1px solid var(--color-outline-variant);
  color: var(--color-on-surface-variant);
  border-radius: var(--rounded-lg);
  padding: 8px 16px;
  cursor: pointer;
  font-family: var(--font-display);
  font-size: 14px;
  transition: all 0.3s ease;
}

.btn-outline:hover {
  border-color: var(--color-error);
  color: var(--color-error);
  background: rgba(255, 180, 171, 0.05);
}

.small {
  padding: 8px 16px;
  font-size: 14px;
}
</style>
