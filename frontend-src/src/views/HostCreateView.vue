<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { t } from '../i18n';

const router = useRouter();

const pollTitle = ref('');

const questions = ref([
  {
    id: Date.now(),
    title: '',
    type: 'single',
    options: ['', ''],
    maxAnswers: 1
  }
]);

const addQuestion = () => {
  questions.value.push({
    id: Date.now(),
    title: '',
    type: 'single',
    options: ['', ''],
    maxAnswers: 1
  });
};

const removeQuestion = (index) => {
  if (questions.value.length > 1) {
    questions.value.splice(index, 1);
  } else {
    alert("Du musst mindestens eine Frage haben!");
  }
};

const addOption = (qIndex) => {
  questions.value[qIndex].options.push('');
};

const removeOption = (qIndex, optIndex) => {
  if (questions.value[qIndex].options.length > 2) {
    questions.value[qIndex].options.splice(optIndex, 1);
  }
};

const startPoll = () => {
  console.log("Umfrage wird gespeichert:", { title: pollTitle.value, questions: questions.value });

  const dummyCode = "987654";
  alert("Umfrage gestartet! Code: " + dummyCode);

};
</script>

<template>
  <main class="create-page">
    <div class="content-wrapper">

      <header class="create-header">
        <h1>{{ t.create.title }}</h1>
        <p>{{ t.create.sub }}</p>

        <input
            v-model="pollTitle"
            type="text"
            class="poll-title-input"
            :placeholder="t.create.pollName"
        />
      </header>

      <!-- Die Fragen-Liste -->
      <div class="questions-list">
        <div v-for="(q, qIndex) in questions" :key="q.id" class="q-card">

          <div class="q-card-header">
            <span class="q-number">Frage {{ qIndex + 1 }}</span>
            <button @click="removeQuestion(qIndex)" class="btn-icon-danger" :title="t.create.delete">
              <img src="@/assets/icons/trash.svg" class="icon-sm" alt="x">
            </button>
          </div>

          <!-- Frage Titel -->
          <input
              v-model="q.title"
              type="text"
              class="q-title-input"
              :placeholder="t.create.qTitle"
          />

          <!-- Typ-Auswahl (Tabs) -->
          <div class="type-selector">
            <button :class="{ active: q.type === 'single' }" @click="q.type = 'single'">
              {{ t.create.typeSingle }}
            </button>
            <button :class="{ active: q.type === 'multi' }" @click="q.type = 'multi'">
              {{ t.create.typeMulti }}
            </button>
            <button :class="{ active: q.type === 'text' }" @click="q.type = 'text'">
              {{ t.create.typeText }}
            </button>
          </div>

          <!-- Optionenbereich -->
          <div v-if="q.type === 'single' || q.type === 'multi'" class="options-area">
            <div v-for="(opt, optIndex) in q.options" :key="optIndex" class="option-row">
              <div class="opt-indicator" :class="q.type"></div>
              <input
                  v-model="q.options[optIndex]"
                  type="text"
                  placeholder="Optionstext..."
                  class="opt-input"
              />
              <button v-if="q.options.length > 2" @click="removeOption(qIndex, optIndex)" class="btn-icon">
                <img src="@/assets/icons/trash.svg" class="icon-xs" alt="x">
              </button>
            </div>

            <button @click="addOption(qIndex)" class="btn-text">
              <img src="@/assets/icons/plus-circle.svg" class="icon-xs" alt="+">
              {{ t.create.addOption }}
            </button>
          </div>

          <!-- Optionenbereich für Freitext -->
          <div v-if="q.type === 'text'" class="text-settings-area">
            <label>{{ t.create.maxAnswers }}</label>
            <input
                v-model.number="q.maxAnswers"
                type="number"
                min="1"
                max="10"
                class="number-input"
            />
          </div>

        </div>
      </div>

      <!-- Neue Frage Hinzufügen Button -->
      <button @click="addQuestion" class="btn-add-q">
        <img src="@/assets/icons/plus-circle.svg" class="icon-sm" alt="+">
        {{ t.create.addQuestion }}
      </button>

    </div>

    <!-- Sticky Footer zum Starten -->
    <div class="bottom-bar">
      <div class="bottom-content">
        <span class="q-count">{{ questions.length }} Frage(n)</span>
        <button @click="startPoll" class="btn-start">
          <img src="@/assets/icons/play.svg" class="icon-sm icon-white" alt=">">
          {{ t.create.startPoll }}
        </button>
      </div>
    </div>
  </main>
</template>

<style scoped>
.create-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: calc(100vh - 72px);
  padding: 40px 24px 120px 24px; /* Unten viel Platz für die Sticky Bar */
}

.content-wrapper {
  width: 100%;
  max-width: 760px;
}

.create-header {
  text-align: center;
  margin-bottom: 40px;
}

.create-header h1 {
  font-size: 2.5rem;
  font-weight: 800;
  letter-spacing: -0.03em;
}

.create-header p {
  color: var(--text-muted);
  font-size: 1.1rem;
  margin-bottom: 24px;
}

.poll-title-input {
  width: 100%;
  font-size: 1.8rem;
  font-weight: 800;
  text-align: center;
  border: none;
  background: transparent;
  padding: 10px;
  border-bottom: 2px dashed var(--border);
  color: var(--text-main);
}

.poll-title-input:focus {
  outline: none;
  border-bottom-color: var(--primary);
}

/* Karten System */
.questions-list {
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.q-card {
  background: white;
  border-radius: 20px;
  padding: 32px;
  box-shadow: var(--shadow);
  border: 1px solid var(--border);
  transition: all 0.3s ease;
}

.q-card:focus-within {
  border-color: var(--primary);
  box-shadow: 0 0 0 4px rgba(99, 102, 241, 0.1);
}

.q-card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

.q-number {
  font-weight: 700;
  color: var(--text-muted);
  text-transform: uppercase;
  font-size: 0.85rem;
  letter-spacing: 0.05em;
}

.btn-icon-danger {
  background: none;
  border: none;
  color: #ef4444;
  cursor: pointer;
  padding: 8px;
  border-radius: 8px;
  opacity: 0.5;
  transition: all 0.2s;
}

.btn-icon-danger:hover {
  background: #fef2f2;
  opacity: 1;
}

.q-title-input {
  width: 100%;
  font-size: 1.4rem;
  font-weight: 700;
  border: none;
  background: #f8fafc;
  padding: 16px;
  border-radius: 12px;
  margin-bottom: 20px;
}

.q-title-input:focus {
  outline: none;
  background: #eef2ff;
}

/* Type Selector (Pills) */
.type-selector {
  display: flex;
  background: #f1f5f9;
  padding: 6px;
  border-radius: 12px;
  margin-bottom: 24px;
  gap: 4px;
}

.type-selector button {
  flex: 1;
  background: transparent;
  border: none;
  padding: 10px;
  border-radius: 8px;
  font-weight: 600;
  color: var(--text-muted);
  cursor: pointer;
  transition: all 0.2s;
}

.type-selector button.active {
  background: white;
  color: var(--primary);
  box-shadow: 0 2px 4px rgba(0,0,0,0.05);
}

/* Optionen Area */
.options-area {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.option-row {
  display: flex;
  align-items: center;
  gap: 12px;
}

.opt-indicator {
  width: 20px;
  height: 20px;
  border: 2px solid var(--border);
  flex-shrink: 0;
}

.opt-indicator.single { border-radius: 50%; }
.opt-indicator.multi { border-radius: 6px; }

.opt-input {
  flex: 1;
  padding: 12px 16px;
  border: 2px solid var(--border);
  border-radius: 10px;
  font-size: 1rem;
  transition: all 0.2s;
}

.opt-input:focus {
  border-color: var(--primary);
  outline: none;
}

.btn-icon {
  background: none;
  border: none;
  padding: 10px;
  cursor: pointer;
  opacity: 0.4;
}

.btn-icon:hover { opacity: 1; color: #ef4444; }

.btn-text {
  display: flex;
  align-items: center;
  gap: 8px;
  background: none;
  border: none;
  color: var(--primary);
  font-weight: 700;
  padding: 12px 0;
  cursor: pointer;
  width: max-content;
}

.btn-text:hover { text-decoration: underline; }

/* Text Settings */
.text-settings-area {
  display: flex;
  align-items: center;
  gap: 16px;
  background: #f8fafc;
  padding: 16px;
  border-radius: 12px;
}

.text-settings-area label {
  font-weight: 600;
  color: var(--text-main);
}

.number-input {
  width: 80px;
  padding: 10px;
  border: 2px solid var(--border);
  border-radius: 8px;
  text-align: center;
  font-weight: 700;
  font-size: 1.1rem;
}

/* Big Add Button */
.btn-add-q {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  width: 100%;
  padding: 24px;
  margin-top: 24px;
  background: transparent;
  border: 2px dashed var(--border);
  border-radius: 20px;
  color: var(--text-muted);
  font-size: 1.1rem;
  font-weight: 700;
  cursor: pointer;
  transition: all 0.2s;
}

.btn-add-q:hover {
  border-color: var(--primary);
  color: var(--primary);
  background: rgba(99, 102, 241, 0.05);
}

/* Sticky Bottom Bar */
.bottom-bar {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  background: rgba(255, 255, 255, 0.9);
  backdrop-filter: blur(12px);
  border-top: 1px solid var(--border);
  padding: 16px 24px;
  display: flex;
  justify-content: center;
  z-index: 100;
}

.bottom-content {
  width: 100%;
  max-width: 760px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.q-count {
  font-weight: 700;
  color: var(--text-muted);
}

.btn-start {
  display: flex;
  align-items: center;
  gap: 10px;
  background: var(--primary);
  color: white;
  padding: 14px 28px;
  border-radius: 12px;
  font-weight: 800;
  font-size: 1.1rem;
  border: none;
  cursor: pointer;
  box-shadow: 0 4px 15px rgba(99, 102, 241, 0.3);
  transition: all 0.2s;
}

.btn-start:hover {
  background: var(--primary-dark);
  transform: translateY(-2px);
}
</style>