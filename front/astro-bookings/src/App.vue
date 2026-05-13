<template>
  <div id="app" class="container">
    <header class="app-header">
      <img alt="AstroBookings logo" src="./assets/logo.png" class="logo">
      <HealthStatus />
    </header>

    <nav class="main-nav">
      <button 
        @click="view = 'rockets'" 
        class="nav-btn"
        :class="{ active: view === 'rockets' }" 
        :aria-pressed="view === 'rockets'"
      >
        Rockets
      </button>
      <button 
        @click="view = 'launches'" 
        class="nav-btn"
        :class="{ active: view === 'launches' }" 
        :aria-pressed="view === 'launches'"
      >
        Launches
      </button>
    </nav>

    <main>
      <div v-if="view === 'rockets'">
        <RocketForm :rocket="editingRocket" @success="onSuccess" @cancel="onCancel" />
        <RocketCatalog :rockets="rockets" @edit="onEdit" @refresh="fetchRockets" />
      </div>

      <div v-if="view === 'launches'">
        <LaunchForm :launch="editingLaunch" :rockets="rockets" @success="onLaunchSuccess" @cancel="onLaunchCancel" />
        <LaunchCatalog :launches="launches" :rockets="rockets" @edit="onLaunchEdit" />
      </div>
    </main>
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
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  padding: 40px 20px;
}

.container {
  max-width: var(--container-max);
  margin: 0 auto;
}

.app-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 40px;
}

.logo {
  height: 40px;
}

.main-nav {
  margin-bottom: 40px;
  display: flex;
  justify-content: center;
  gap: 20px;
}

.nav-btn {
  background: transparent;
  border: none;
  color: var(--color-on-surface-variant);
  font-family: var(--font-display);
  font-size: 18px;
  font-weight: 600;
  cursor: pointer;
  padding: 10px 0;
  position: relative;
  transition: color 0.3s ease;
}

.nav-btn:hover {
  color: var(--color-primary);
}

.nav-btn.active {
  color: var(--color-primary);
}

.nav-btn.active::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 2px;
  background-color: var(--color-primary);
  box-shadow: 0 0 10px var(--color-primary);
}
</style>
