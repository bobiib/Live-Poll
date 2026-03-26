<script setup>
import { ref } from 'vue';

const username = ref('');
const email = ref('');
const password = ref('');
const passwordConfirm = ref('');

const handleRegister = () => {
  if (password.value !== passwordConfirm.value) {
    alert("Passwörter stimmen nicht überein!");
    return;
  }
  console.log("Registrierung für:", username.value, email.value);
};
</script>

<template>
  <div class="auth-page">
    <div class="auth-card">
      <h2>Account erstellen</h2>
      <p class="auth-sub">Werde Host und starte deine eigenen Live-Umfragen.</p>

      <form @submit.prevent="handleRegister">
        <div class="group">
          <label>Benutzername</label>
          <div class="in-wrap">
            <img src="@/assets/icons/login.svg" class="in-icon" alt="U">
            <input v-model="username" type="text" placeholder="z.B. Herr Müller" required />
          </div>
        </div>

        <div class="group">
          <label>E-Mail Adresse</label>
          <div class="in-wrap">
            <img src="@/assets/icons/mail.svg" class="in-icon" alt="@">
            <input v-model="email" type="email" placeholder="mail@beispiel.ch" required />
          </div>
        </div>

        <div class="group">
          <label>Passwort</label>
          <div class="in-wrap">
            <img src="@/assets/icons/lock.svg" class="in-icon" alt="*">
            <input v-model="password" type="password" placeholder="••••••••" required />
          </div>
        </div>

        <div class="group">
          <label>Passwort bestätigen</label>
          <div class="in-wrap">
            <img src="@/assets/icons/shield-check.svg" class="in-icon" alt="S">
            <input v-model="passwordConfirm" type="password" placeholder="••••••••" required />
          </div>
        </div>

        <button type="submit" class="btn-auth">
          Account erstellen
          <img src="@/assets/icons/register.svg" class="icon-sm icon-white" alt="+">
        </button>
      </form>

      <div class="auth-footer">
        Bereits ein Konto? <router-link to="/login">Einloggen</router-link>
      </div>
    </div>
  </div>
</template>

<style scoped>
.auth-page {
  display: flex;
  justify-content: center;
  padding: 60px 24px;
}

.auth-card {
  background: white;
  width: 100%;
  max-width: 460px;
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
  font-size: 0.95rem;
}

form {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.group {
  display: flex;
  flex-direction: column;
  gap: 6px;
}

label {
  font-weight: 700;
  font-size: 0.85rem;
  color: var(--text-main);
  margin-left: 4px;
}

.in-wrap {
  position: relative;
}

.in-icon {
  position: absolute;
  left: 14px;
  top: 50%;
  transform: translateY(-50%);
  width: 18px;
  height: 18px;
  opacity: 0.4;
}

input {
  width: 100%;
  padding: 12px 12px 12px 42px;
  border: 2px solid var(--border);
  border-radius: 12px;
  font-size: 1rem;
  background-color: #fcfcfc;
  transition: all 0.2s ease;
}

input:focus {
  border-color: var(--primary);
  background-color: white;
}

.btn-auth {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  background: var(--primary);
  color: white;
  padding: 14px;
  border: none;
  border-radius: 12px;
  font-weight: 700;
  font-size: 1rem;
  margin-top: 12px;
  cursor: pointer;
  transition: background 0.2s ease, transform 0.1s ease;
}

.btn-auth:hover {
  background: var(--primary-dark);
}

.btn-auth:active {
  transform: scale(0.98);
}

.auth-footer {
  margin-top: 24px;
  text-align: center;
  font-size: 0.9rem;
  color: var(--text-muted);
}

.auth-footer a {
  color: var(--primary);
  font-weight: 700;
  text-decoration: none;
}

.auth-footer a:hover {
  text-decoration: underline;
}
</style>