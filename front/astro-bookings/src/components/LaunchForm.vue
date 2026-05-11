<template>
  <div class="launch-form">
    <h3>{{ isEditing ? 'Edit Launch' : 'Schedule Launch' }}</h3>
    <form @submit.prevent="submit">
      <div>
        <label>Rocket:</label>
        <select v-model="form.rocketId" required :disabled="isEditing">
          <option v-for="rocket in rockets" :key="rocket.id" :value="rocket.id">
            {{ rocket.name }}
          </option>
        </select>
      </div>
      <div>
        <label>Launch Time:</label>
        <input type="datetime-local" v-model="form.launchTime" required />
      </div>
      <div>
        <label>Price per Ticket:</label>
        <input type="number" step="0.01" v-model.number="form.pricePerTicket" required />
      </div>
      <div>
        <label>Min. Occupancy:</label>
        <input type="number" v-model.number="form.minimumOccupancy" required />
      </div>
      <div v-if="isEditing">
        <label>Status:</label>
        <select v-model="form.status">
          <option value="created">Created</option>
          <option value="confirmed">Confirmed</option>
          <option value="completed">Completed</option>
          <option value="cancelled">Cancelled</option>
        </select>
      </div>
      <button type="submit">{{ isEditing ? 'Update' : 'Schedule' }}</button>
      <button type="button" @click="$emit('cancel')">Cancel</button>
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
  margin-bottom: 20px;
  border: 1px solid #ccc;
  padding: 10px;
}
.launch-form div {
  margin-bottom: 10px;
}
label {
  display: inline-block;
  width: 120px;
}
</style>
