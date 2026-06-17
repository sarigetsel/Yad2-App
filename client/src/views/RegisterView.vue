<script setup>
import { ref } from 'vue'
import { useRouter, RouterLink } from 'vue-router'
import { useAuthStore } from '../stores/auth'

const auth = useAuthStore()
const router = useRouter()

const form = ref({
  nameSeller: '',
  email: '',
  phone: '',
  password: '',
  confirmPassword: '',
})
const error = ref('')
const loading = ref(false)

async function submit() {
  error.value = ''
  if (form.value.password !== form.value.confirmPassword) {
    error.value = 'הסיסמאות אינן תואמות'
    return
  }
  if (form.value.password.length < 4) {
    error.value = 'הסיסמה חייבת להכיל לפחות 4 תווים'
    return
  }
  loading.value = true
  try {
    await auth.register({
      nameSeller: form.value.nameSeller,
      email: form.value.email,
      phone: form.value.phone,
      password: form.value.password,
    })
    router.push('/')
  } catch (e) {
    error.value = e.response?.data?.message || 'שגיאה בהרשמה, נסו שוב'
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
        <h1>הרשמה</h1>
        <p>הצטרפו ל-Hand2 ופרסמו מודעות</p>
      </div>

      <form @submit.prevent="submit">
        <div class="form-group">
          <label for="name">שם מלא</label>
          <input id="name" v-model="form.nameSeller" type="text" required placeholder="ישראל ישראלי" />
        </div>
        <div class="form-group">
          <label for="email">אימייל</label>
          <input id="email" v-model="form.email" type="email" required autocomplete="email" />
        </div>
        <div class="form-group">
          <label for="phone">טלפון</label>
          <input id="phone" v-model="form.phone" type="tel" required placeholder="050-1234567" />
        </div>
        <div class="form-group">
          <label for="password">סיסמה</label>
          <input id="password" v-model="form.password" type="password" required autocomplete="new-password" />
        </div>
        <div class="form-group">
          <label for="confirm">אימות סיסמה</label>
          <input id="confirm" v-model="form.confirmPassword" type="password" required autocomplete="new-password" />
        </div>
        <p v-if="error" class="error-msg">{{ error }}</p>
        <button type="submit" class="btn btn-primary full" :disabled="loading">
          {{ loading ? 'נרשם...' : 'הירשם' }}
        </button>
      </form>

      <p class="auth-footer">
        כבר יש לכם חשבון?
        <RouterLink to="/login">התחברו</RouterLink>
      </p>
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
</style>
