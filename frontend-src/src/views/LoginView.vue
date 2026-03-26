<script setup>
import { ref } from 'vue';

const email = ref('');
const password = ref('');

const handleLogin = () => {
  console.log("Login mit:", email.value);
};
</script>

<template>
  <div class="auth-page">
    <div class="auth-card">
      <h2>Willkommen zurück</h2>
      <p class="auth-sub">Logge dich ein, um deine Umfragen zu verwalten.</p>

      <form @submit.prevent="handleLogin">
        <div class="group">
          <label>E-Mail</label>
          <div class="in-wrap">
            <img src="@/assets/icons/mail.svg" class="in-icon" alt="@">
            <input v-model="email" type="email" placeholder="mail@beispiel.ch" />
          </div>
        </div>

        <div class="group">
          <label>Passwort</label>
          <div class="in-wrap">
            <img src="@/assets/icons/lock.svg" class="in-icon" alt="*">
            <input v-model="password" type="password" placeholder="••••••••" />
          </div>
        </div>

        <button type="submit" class="btn-auth">Einloggen</button>
      </form>

      <div class="auth-footer">
        Noch kein Konto? <router-link to="/register">Registrieren</router-link>
      </div>
    </div>
  </div>
</template>

<style scoped>
.auth-page {
  display: flex;
  justify-content: center;
  padding: 80px 24px;
}

.auth-card {
  background: white;
  width: 100%;
  max-width: 440px;
  padding: 40px;
  border-radius: 24px;
  border: 1px solid var(--border);
  box-shadow: var(--shadow);
}

h2 {
  font-size: 2rem;
  font-weight: 800;
  letter-spacing: -1px;
  margin-bottom: 8px;
  text-align: center;
}

.auth-sub {
  text-align: center;
  color: var(--text-muted);
  margin-bottom: 32px;
}

form { display: flex; flex-direction: column; gap: 20px; }

.group { display: flex; flex-direction: column; gap: 8px; }

label { font-weight: 700; font-size: 0.9rem; color: var(--text-main); }

.in-wrap { position: relative; }

.in-icon {
  position: absolute;
  left: 14px;
  top: 50%;
  transform: translateY(-50%);
  width: 18px;
  opacity: 0.4;
}

input {
  width: 100%;
  padding: 12px 12px 12px 42px;
  border: 2px solid var(--border);
  border-radius: 12px;
  font-size: 1rem;
}

input:focus { border-color: var(--primary); }

.btn-auth {
  background: var(--primary);
  color: white;
  padding: 14px;
  border: none;
  border-radius: 12px;
  font-weight: 700;
  font-size: 1rem;
  margin-top: 8px;
  cursor: pointer;
}

.auth-footer {
  margin-top: 24px;
  text-align: center;
  font-size: 0.95rem;
  color: var(--text-muted);
}

.auth-footer a {
  color: var(--primary);
  font-weight: 700;
}
</style>