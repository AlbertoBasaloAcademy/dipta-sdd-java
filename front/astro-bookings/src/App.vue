<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HealthStatus />
    <hr />

    <nav>
      <button @click="view = 'rockets'" :class="{ active: view === 'rockets' }">Rockets</button>
      <button @click="view = 'launches'" :class="{ active: view === 'launches' }">Launches</button>
    </nav>

    <div v-if="view === 'rockets'">
      <RocketForm :rocket="editingRocket" @success="onSuccess" @cancel="onCancel" />
      <RocketCatalog :rockets="rockets" @edit="onEdit" @refresh="fetchRockets" />
    </div>

    <div v-if="view === 'launches'">
      <LaunchForm :launch="editingLaunch" :rockets="rockets" @success="onLaunchSuccess" @cancel="onLaunchCancel" />
      <LaunchCatalog :launches="launches" :rockets="rockets" @edit="onLaunchEdit" />
    </div>
  </div>
</template>

<script>
import HealthStatus from './components/HealthStatus.vue'
import RocketCatalog from './components/RocketCatalog.vue'
import RocketForm from './components/RocketForm.vue'
import LaunchCatalog from './components/LaunchCatalog.vue'
import LaunchForm from './components/LaunchForm.vue'
import { getAllRockets } from './services/rocketService'
import { getAllLaunches } from './services/launchService'

export default {
  name: 'App',
  components: {
    HealthStatus,
    RocketCatalog,
    RocketForm,
    LaunchCatalog,
    LaunchForm
  },
  data() {
    return {
      view: 'rockets',
      rockets: [],
      launches: [],
      editingRocket: null,
      editingLaunch: null
    }
  },
  async created() {
    await this.fetchRockets();
    await this.fetchLaunches();
  },
  methods: {
    async fetchRockets() {
      try {
        this.rockets = await getAllRockets();
      } catch (error) {
        console.error('Error fetching rockets:', error);
      }
    },
    async fetchLaunches() {
      try {
        this.launches = await getAllLaunches();
      } catch (error) {
        console.error('Error fetching launches:', error);
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
    },
    onLaunchEdit(launch) {
      this.editingLaunch = launch;
    },
    onLaunchSuccess() {
      this.editingLaunch = null;
      this.fetchLaunches();
    },
    onLaunchCancel() {
      this.editingLaunch = null;
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
nav {
  margin-bottom: 20px;
}
nav button {
  padding: 10px 20px;
  margin: 0 5px;
  cursor: pointer;
}
nav button.active {
  background-color: #42b983;
  color: white;
  border: none;
}
</style>
