<template>
  <div class="rocket-catalog">
    <h3>Rocket Catalog</h3>
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Capacity</th>
          <th>Range</th>
          <th>Status</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="rocket in rockets" :key="rocket.id">
          <td>{{ rocket.name }}</td>
          <td>{{ rocket.capacity }}</td>
          <td>{{ rocket.range }}</td>
          <td>{{ rocket.decommissioned ? 'Decommissioned' : 'Operational' }}</td>
          <td>
            <button @click="$emit('edit', rocket)">Update</button>
            <button v-if="!rocket.decommissioned" @click="decommission(rocket.id)">Decommission</button>
          </td>
        </tr>
      </tbody>
    </table>
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
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px;
}
</style>
