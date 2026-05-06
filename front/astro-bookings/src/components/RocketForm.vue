<template>
  <div class="rocket-form">
    <h3>{{ isEditing ? 'Update Rocket' : 'Register Rocket' }}</h3>
    <form @submit.prevent="submit">
      <div>
        <label>Name:</label>
        <input v-model="form.name" required />
      </div>
      <div>
        <label>Capacity (1-9):</label>
        <input type="number" v-model.number="form.capacity" min="1" max="9" required />
      </div>
      <div>
        <label>Range:</label>
        <select v-model="form.range" required>
          <option value="Earth">Earth</option>
          <option value="Moon">Moon</option>
          <option value="Mars">Mars</option>
        </select>
      </div>
      <button type="submit">{{ isEditing ? 'Update' : 'Register' }}</button>
      <button type="button" @click="$emit('cancel')">Cancel</button>
    </form>
  </div>
</template>

<script>
import { createRocket, updateRocket } from '../services/rocketService';

export default {
  props: ['rocket'],
  data() {
    return {
      form: {
        name: '',
        capacity: 1,
        range: 'Earth'
      }
    };
  },
  computed: {
    isEditing() {
      return !!this.rocket;
    }
  },
  watch: {
    rocket: {
      immediate: true,
      handler(val) {
        if (val) {
          this.form = { ...val };
        } else {
          this.form = { name: '', capacity: 1, range: 'Earth' };
        }
      }
    }
  },
  methods: {
    async submit() {
      try {
        if (this.isEditing) {
          await updateRocket(this.rocket.id, this.form);
        } else {
          await createRocket(this.form);
        }
        this.$emit('success');
      } catch (error) {
        alert('Error saving rocket');
      }
    }
  }
}
</script>

<style scoped>
.rocket-form {
  margin-bottom: 20px;
  border: 1px solid #ccc;
  padding: 10px;
}
</style>
