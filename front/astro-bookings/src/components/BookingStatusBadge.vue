<template>
  <span :class="['badge-celestial', statusClass]">
    {{ status }}
  </span>
</template>

<script>
export default {
  props: {
    status: {
      type: String,
      required: true
    }
  },
  computed: {
    statusClass() {
      return `status-${this.status.toLowerCase()}`;
    }
  }
}
</script>

<style scoped>
.badge-celestial {
  padding: 4px 12px;
  border-radius: var(--rounded-full);
  font-family: var(--font-display);
  font-size: 12px;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.05em;
  display: inline-block;
  background-color: var(--color-surface-container-high);
  color: var(--color-on-surface-variant);
}

.status-cancelled {
  background-color: var(--color-error);
  color: var(--color-on-error);
}

.status-created {
  border: 1px solid var(--color-outline-variant);
  background: transparent;
}
</style>
