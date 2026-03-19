<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const roomCode = ref('');
const router = useRouter();

const joinRoom = () => {
  if (roomCode.value.length === 6) {
    console.log("Join Room:", roomCode.value);
    // Hier später Routing zur Room-Ansicht
  }
};
</script>

<template>
  <main class="home-hero">
    <div class="hero-content">
      <div class="status-badge">
        <img src="@/assets/icons/bolt.svg" class="icon-xs" alt="icon">
        Systeme online
      </div>
      <h1>Einfache Umfragen für moderne Teams.</h1>
      <p>Interaktion in Echtzeit. Ohne Installation, ohne Stress.</p>

      <div class="card">
        <div class="input-wrapper">
          <img src="@/assets/icons/hash.svg" class="input-icon" alt="#">
          <input
              v-model="roomCode"
              type="text"
              placeholder="Room Code"
              maxlength="6"
          />
        </div>

        <button @click="joinRoom" class="btn-main">
          Beitreten
          <img src="@/assets/icons/arrow-right.svg" class="icon-sm icon-white" alt=">">
        </button>

        <div class="divider">
          <span>oder</span>
        </div>

        <router-link to="/register" class="btn-sub">
          <img src="@/assets/icons/plus-circle.svg" class="icon-sm" alt="+">
          Eigene Umfrage starten
        </router-link>
      </div>
    </div>
  </main>
</template>

<style scoped>
.home-hero {
  display: flex;
  justify-content: center;
  padding: 100px 24px;
}

.hero-content {
  text-align: center;
  max-width: 640px;
  width: 100%;
}

.status-badge {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  background: #e0e7ff;
  color: var(--primary);
  padding: 6px 14px;
  border-radius: 100px;
  font-size: 0.85rem;
  font-weight: 700;
  margin-bottom: 32px;
}

h1 {
  font-size: 3.5rem;
  font-weight: 800;
  line-height: 1.1;
  letter-spacing: -0.05em;
  margin-bottom: 20px;
}

p {
  font-size: 1.25rem;
  color: var(--text-muted);
  margin-bottom: 48px;
}

.card {
  background: var(--secondary);
  padding: 40px;
  border-radius: 24px;
  border: 1px solid var(--border);
  box-shadow: var(--shadow);
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.input-wrapper {
  position: relative;
}

.input-icon {
  position: absolute;
  left: 18px;
  top: 50%;
  transform: translateY(-50%);
  width: 22px;
  opacity: 0.4;
}

input {
  width: 100%;
  padding: 18px 18px 18px 52px;
  font-size: 1.25rem;
  font-weight: 700;
  border: 2px solid var(--border);
  border-radius: var(--radius);
  background: #f8fafc;
}

input:focus {
  border-color: var(--primary);
  background: white;
}

.btn-main {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 12px;
  background: var(--primary);
  color: white;
  padding: 18px;
  font-size: 1.1rem;
  font-weight: 700;
  border-radius: var(--radius);
  border: none;
  cursor: pointer;
}

.btn-main:hover {
  background: var(--primary-dark);
  transform: translateY(-2px);
}

.divider {
  margin: 12px 0;
  display: flex;
  align-items: center;
  color: var(--text-muted);
  font-size: 0.9rem;
}

.divider::before, .divider::after {
  content: "";
  flex: 1;
  border-bottom: 1px solid var(--border);
}

.divider span { padding: 0 15px; }

.btn-sub {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  padding: 16px;
  border: 2px solid var(--border);
  border-radius: var(--radius);
  color: var(--text-main);
  font-weight: 700;
  background: white;
}

.btn-sub:hover {
  background: #f8fafc;
  border-color: var(--text-main);
}
</style>