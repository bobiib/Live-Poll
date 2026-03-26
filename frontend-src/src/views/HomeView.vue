<script setup>
import { ref } from 'vue'
import { t } from '../i18n'
const roomCode = ref('')
</script>

<template>
  <main class="home-page">
    <div class="content-wrapper">
      <div class="status-badge">
        <img src="@/assets/icons/bolt.svg" class="icon-xs" alt="icon">
        {{ t.home.badge }}
      </div>
      <h1>{{ t.home.title }}</h1>
      <p class="subtitle">{{ t.home.sub }}</p>
      <div class="card">
        <div class="input-wrapper">
          <img src="@/assets/icons/hash.svg" class="input-icon" alt="#">
          <input v-model="roomCode" type="text" :placeholder="t.home.input" maxlength="6" />
        </div>
        <button class="btn-main">
          {{ t.home.btnJoin }}
          <img src="@/assets/icons/arrow-right.svg" class="icon-sm icon-white" alt=">">
        </button>
        <div class="divider"><span>{{ t.home.or }}</span></div>
        <router-link to="/register" class="btn-sub">
          <img src="@/assets/icons/plus-circle.svg" class="icon-sm" alt="+">
          {{ t.home.btnHost }}
        </router-link>
      </div>
    </div>
  </main>
</template>

<style scoped>
/* 1. Haupt-Container: Erzwingt volle Breite und Zentrierung */
.home-page {
  display: flex;
  flex-direction: column;
  justify-content: center; /* Vertikal zentrieren */
  align-items: center;     /* Horizontal zentrieren */
  min-height: calc(100vh - 72px); /* Bildschirmhöhe minus Navbar */
  width: 100%;             /* Nimmt die volle Breite ein */
  padding: 40px 24px;
  overflow: hidden;
}

/* 2. Innerer Wrapper: Hält den Text und die Karte zusammen */
.content-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center; /* Zentriert den Text und die Karte in der Mitte */
  text-align: center;
  width: 100%;
  max-width: 640px;
}

/* Badge Style */
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
  margin-bottom: 24px;
}

/* Typografie */
h1 {
  font-size: 3.5rem;
  font-weight: 800;
  line-height: 1.1;
  letter-spacing: -0.05em;
  margin-bottom: 20px;
  color: var(--text-main);
}

.subtitle {
  font-size: 1.25rem;
  color: var(--text-muted);
  margin-bottom: 48px;
}

/* Karte (Card) */
.card {
  background: var(--secondary);
  padding: 40px;
  border-radius: 24px;
  border: 1px solid var(--border);
  box-shadow: var(--shadow);
  display: flex;
  flex-direction: column;
  gap: 16px;
  width: 100%;
  max-width: 440px;
  text-align: left; /* Damit der Placeholder im Input links bleibt */
}

/* Input Feld */
.input-wrapper {
  position: relative;
  width: 100%;
}

.input-icon {
  position: absolute;
  left: 18px;
  top: 50%;
  transform: translateY(-50%);
  width: 22px;
  opacity: 0.4;
  pointer-events: none;
}

input {
  width: 100%;
  padding: 18px 18px 18px 52px;
  font-size: 1.25rem;
  font-weight: 700;
  border: 2px solid var(--border);
  border-radius: var(--radius);
  background: #f8fafc;
  letter-spacing: 2px;
  transition: all 0.2s ease;
}

input:focus {
  border-color: var(--primary);
  background: white;
  outline: none;
}

/* Buttons */
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
  transition: all 0.2s ease;
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

.divider span {
  padding: 0 15px;
}

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
  transition: all 0.2s ease;
}

.btn-sub:hover {
  background: #f8fafc;
  border-color: var(--text-main);
}
</style>