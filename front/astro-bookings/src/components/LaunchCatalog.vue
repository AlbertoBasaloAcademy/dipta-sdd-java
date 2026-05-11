<template>
  <div class="launch-catalog">
    <h3>Launch Catalog</h3>
    <table>
      <thead>
        <tr>
          <th>Time</th>
          <th>Rocket</th>
          <th>Price</th>
          <th>Min. Occupancy</th>
          <th>Status</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="launch in launches" :key="launch.id">
          <td>{{ formatDate(launch.launchTime) }}</td>
          <td>{{ getRocketName(launch.rocketId) }}</td>
          <td>${{ launch.pricePerTicket }}</td>
          <td>{{ launch.minimumOccupancy }}</td>
          <td>
            <LaunchStatusBadge :status="launch.status" />
          </td>
          <td>
            <button @click="$emit('edit', launch)">Edit</button>
          </td>
        </tr>
      </tbody>
    </table>
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
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}
th {
  background-color: #f4f4f4;
}
</style>
