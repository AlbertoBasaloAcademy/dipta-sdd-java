<template>
  <div class="rocket-form glass-panel">
    <h3>{{ isEditing ? 'Update Rocket' : 'Register Rocket' }}</h3>
    <form @submit.prevent="submit" class="cosmic-form">
      <div class="form-group">
        <label for="rocket-name" class="label-md">Name</label>
        <input id="rocket-name" v-model="form.name" required class="input-field" placeholder="Rocket name..." />
      </div>
      <div class="form-group">
        <label for="rocket-capacity" class="label-md">Capacity (1-9)</label>
        <input id="rocket-capacity" type="number" v-model.number="form.capacity" min="1" max="9" required class="input-field" />
      </div>
      <div class="form-group">
        <label for="rocket-range" class="label-md">Range</label>
        <select id="rocket-range" v-model="form.range" required class="input-field">
          <option value="Earth">Earth</option>
          <option value="Moon">Moon</option>
          <option value="Mars">Mars</option>
        </select>
      </div>
      <div class="form-actions">
        <button type="submit" class="btn-primary">{{ isEditing ? 'Update' : 'Register' }}</button>
        <button type="button" @click="$emit('cancel')" class="btn-secondary">Cancel</button>
      </div>
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
