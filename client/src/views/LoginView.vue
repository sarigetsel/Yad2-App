<script setup>
import { ref } from 'vue'
import { useRouter, useRoute, RouterLink } from 'vue-router'
import { useAuthStore } from '../stores/auth'

const auth = useAuthStore()
const router = useRouter()
const route = useRoute()

const email = ref('')
const password = ref('')
const error = ref('')
const loading = ref(false)

async function submit() {
  error.value = ''
  loading.value = true
  try {
    await auth.login(email.value, password.value)
    const redirect = route.query.redirect || '/'
    router.push(redirect)
  } catch (e) {
    error.value = e.response?.data?.message || 'שם משתמש או סיסמה שגויים'
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="page container auth-page">
    <div class="auth-card card">
      <div class="auth-header">
        <span class="logo-icon">H2</span>
        <h1>התחברות</h1>
        <p>ברוכים השבים ל-Hand2</p>
      </div>

      <form @submit.prevent="submit">
        <div class="form-group">
          <label for="email">אימייל</label>
          <input id="email" v-model="email" type="email" required autocomplete="email" placeholder="your@email.com" />
        </div>
        <div class="form-group">
          <label for="password">סיסמה</label>
          <input id="password" v-model="password" type="password" required autocomplete="current-password" placeholder="••••••••" />
        </div>
        <p v-if="error" class="error-msg">{{ error }}</p>
        <button type="submit" class="btn btn-primary full" :disabled="loading">
          {{ loading ? 'מתחבר...' : 'התחבר' }}
        </button>
      </form>

      <p class="auth-footer">
        אין לכם חשבון?
        <RouterLink to="/register">הירשמו כאן</RouterLink>
      </p>

      <div class="demo-credentials">
        <strong>חשבונות דמו:</strong>
        <span>dani@hand2.co.il / 1234</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.auth-page {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 60vh;
}

.auth-card {
  width: min(420px, 100%);
  padding: 2.5rem;
}

.auth-header {
  text-align: center;
  margin-bottom: 2rem;
}

.logo-icon {
  display: inline-grid;
  place-items: center;
  width: 48px;
  height: 48px;
  background: linear-gradient(135deg, var(--primary), #7c3aed);
  color: white;
  border-radius: 12px;
  font-weight: 800;
  font-size: 1rem;
  margin-bottom: 1rem;
}

.auth-header h1 {
  font-size: 1.5rem;
  margin-bottom: 0.25rem;
}

.auth-header p {
  color: var(--text-muted);
}

.full {
  width: 100%;
  margin-top: 0.5rem;
}

.auth-footer {
  text-align: center;
  margin-top: 1.5rem;
  font-size: 0.9rem;
  color: var(--text-muted);
}

.auth-footer a {
  color: var(--primary);
  font-weight: 600;
}

.demo-credentials {
  margin-top: 1.5rem;
  padding: 1rem;
  background: var(--bg);
  border-radius: var(--radius);
  font-size: 0.85rem;
  text-align: center;
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
}

.demo-credentials strong {
  color: var(--text-muted);
}
</style>
