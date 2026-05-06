<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HealthStatus />
    <hr />
    <RocketForm :rocket="editingRocket" @success="onSuccess" @cancel="onCancel" />
    <RocketCatalog :rockets="rockets" @edit="onEdit" @refresh="fetchRockets" />
  </div>
</template>

<script>
import HealthStatus from './components/HealthStatus.vue'
import RocketCatalog from './components/RocketCatalog.vue'
import RocketForm from './components/RocketForm.vue'
import { getAllRockets } from './services/rocketService'

export default {
  name: 'App',
  components: {
    HealthStatus,
    RocketCatalog,
    RocketForm
  },
  data() {
    return {
      rockets: [],
      editingRocket: null
    }
  },
  async created() {
    await this.fetchRockets();
  },
  methods: {
    async fetchRockets() {
      try {
        this.rockets = await getAllRockets();
      } catch (error) {
        console.error('Error fetching rockets:', error);
      }
    },
    onEdit(rocket) {
      this.editingRocket = rocket;
    },
    onSuccess() {
      this.editingRocket = null;
      this.fetchRockets();
    },
    onCancel() {
      this.editingRocket = null;
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
