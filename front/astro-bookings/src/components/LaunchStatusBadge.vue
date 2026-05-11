<template>
  <span :class="['badge', statusClass]">
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
      return `status-${this.status}`;
    }
  }
}
</script>

<style scoped>
.badge {
  padding: 4px 8px;
  border-radius: 4px;
  font-size: 0.8em;
  font-weight: bold;
  text-transform: uppercase;
}
.status-created {
  background-color: #e0e0e0;
  color: #616161;
}
.status-confirmed {
  background-color: #e3f2fd;
  color: #1976d2;
}
.status-completed {
  background-color: #e8f5e9;
  color: #2e7d32;
}
.status-cancelled {
  background-color: #ffebee;
  color: #c62828;
}
</style>
