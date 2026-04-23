<script setup>
import { ref, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { t } from '../i18n';

const route = useRoute();
const router = useRouter();
const roomCode = route.params.id;


const questions = ref([
  {
    id: 1,
    title: "Wie gefällt dir das Design bisher?",
    type: "choice",
    options: ["Sehr gut", "Geht so", "Verbesserungswürdig"]
  },
  {
    id: 2,
    title: "Welches Feature ist dir am wichtigsten?",
    type: "choice",
    options: ["Live-Stats", "Anonymität", "Design"]
  },
  {
    id: 3,
    title: "Hast du noch Feedback für uns?",
    type: "text",
    options: []
  }
]);

const currentIndex = ref(0);
const hasStarted = ref(true);
const isFinished = ref(false);

const currentQuestion = computed(() => questions.value[currentIndex.value]);
const selectedOption = ref(null);
const textAnswer = ref("");


const submitAnswer = () => {
  console.log(`Antwort für Frage ${currentQuestion.value.id} gesendet.`);

  selectedOption.value = null;
  textAnswer.value = "";

  if (currentIndex.value < questions.value.length - 1) {
    currentIndex.value++;
  } else {
    isFinished.value = true;
  }
};
</script>

<template>
  <main class="room-page">
    <div class="content-wrapper">

      <div v-if="!hasStarted" class="status-box card">
        <div class="loader"></div>
        <h1>{{ t.room.waiting }}</h1>
        <p>{{ t.room.waitingSub }}</p>
        <div class="room-tag">Room: {{ roomCode }}</div>
      </div>

      <div v-else-if="!isFinished" class="poll-box">
        <header class="q-header">
          <div class="progress-bar">
            <div class="progress-fill" :style="{ width: ((currentIndex + 1) / questions.length) * 100 + '%' }"></div>
          </div>
          <span class="q-count">Frage {{ currentIndex + 1 }} von {{ questions.length }}</span>
          <h2>{{ currentQuestion.title }}</h2>
        </header>

        <div v-if="currentQuestion.type === 'choice'" class="options-list">
          <button
              v-for="(opt, index) in currentQuestion.options"
              :key="index"
              class="option-card"
              :class="{ active: selectedOption === index }"
              @click="selectedOption = index"
          >
            <div class="check-dot"></div>
            <span>{{ opt }}</span>
          </button>
        </div>

        <div v-else class="text-input-area">
          <textarea v-model="textAnswer" :placeholder="t.room.textPlaceholder || 'Schreibe etwas...'"></textarea>
        </div>

        <button
            @click="submitAnswer"
            class="btn-primary"
            :disabled="currentQuestion.type === 'choice' ? selectedOption === null : !textAnswer"
        >
          {{ t.room.submit }}
          <img src="@/assets/icons/arrow-right.svg" class="icon-white icon-sm" alt=">">
        </button>
      </div>

      <div v-else class="success-box card">
        <div class="icon-circle">✓</div>
        <h1>{{ t.room.success }}</h1>
        <p>{{ t.room.successSub }}</p>
        <router-link to="/" class="btn-secondary">{{ t.room.back }}</router-link>
      </div>

    </div>
  </main>
</template>

<style scoped>
.room-page {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 72px);
  padding: 24px;
}

.content-wrapper { width: 100%; max-width: 500px; }

.card {
  background: white;
  padding: 40px;
  border-radius: 24px;
  box-shadow: var(--shadow);
  border: 1px solid var(--border);
  text-align: center;
}

/* Header & Progress */
.q-header { text-align: center; margin-bottom: 32px; }

.progress-bar {
  height: 6px;
  background: var(--border);
  border-radius: 10px;
  margin-bottom: 12px;
  overflow: hidden;
}

.progress-fill {
  height: 100%;
  background: var(--primary);
  transition: width 0.4s ease;
}

.q-count { color: var(--text-muted); font-size: 0.85rem; font-weight: 700; text-transform: uppercase; }

h2 { font-size: 2.2rem; font-weight: 800; letter-spacing: -0.04em; margin-top: 8px; line-height: 1.1; }

/* Options */
.options-list { display: flex; flex-direction: column; gap: 12px; margin-bottom: 32px; }

.option-card {
  display: flex;
  align-items: center;
  gap: 16px;
  padding: 20px;
  background: white;
  border: 2px solid var(--border);
  border-radius: 16px;
  cursor: pointer;
  transition: all 0.2s ease;
  font-size: 1.1rem;
  font-weight: 600;
  text-align: left;
}

.option-card:hover { border-color: var(--primary); }
.option-card.active { border-color: var(--primary); background: #eef2ff; color: var(--primary); }

.check-dot {
  width: 22px;
  height: 22px;
  border: 2px solid var(--border);
  border-radius: 50%;
  position: relative;
}

.option-card.active .check-dot { border-color: var(--primary); }
.option-card.active .check-dot::after {
  content: '';
  position: absolute;
  width: 10px;
  height: 10px;
  background: var(--primary);
  border-radius: 50%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

/* Text Input */
textarea {
  width: 100%;
  height: 140px;
  padding: 16px;
  border-radius: 16px;
  border: 2px solid var(--border);
  font-family: inherit;
  font-size: 1.1rem;
  margin-bottom: 32px;
  resize: none;
}

textarea:focus { border-color: var(--primary); outline: none; }

/* Buttons */
.btn-primary {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  background: var(--primary);
  color: white;
  padding: 18px;
  border-radius: 16px;
  font-size: 1.1rem;
  font-weight: 700;
  border: none;
  cursor: pointer;
}

.btn-primary:disabled { opacity: 0.4; cursor: not-allowed; }

.btn-secondary {
  display: inline-block;
  margin-top: 24px;
  text-decoration: none;
  color: var(--primary);
  font-weight: 700;
}

/* Success */
.icon-circle {
  width: 72px;
  height: 72px;
  background: #2ecc71;
  color: white;
  font-size: 32px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  margin: 0 auto 24px;
}

/* Loader */
.loader {
  border: 4px solid var(--border);
  border-top: 4px solid var(--primary);
  border-radius: 50%;
  width: 40px;
  height: 40px;
  animation: spin 1s linear infinite;
  margin: 0 auto 20px;
}
@keyframes spin { 0% { transform: rotate(0deg); } 100% { transform: rotate(360deg); } }
</style>