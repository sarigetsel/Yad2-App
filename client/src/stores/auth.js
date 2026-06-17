import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { authApi } from '../api/auth'

const STORAGE_KEY = 'hand2_auth'

export const useAuthStore = defineStore('auth', () => {
  const saved = JSON.parse(localStorage.getItem(STORAGE_KEY) || 'null')

  const token = ref(saved?.token || null)
  const email = ref(saved?.email || null)
  const nameSeller = ref(saved?.nameSeller || null)
  const sellerId = ref(saved?.sellerId || null)

  const isLoggedIn = computed(() => !!token.value)

  function persist() {
    localStorage.setItem(
      STORAGE_KEY,
      JSON.stringify({
        token: token.value,
        email: email.value,
        nameSeller: nameSeller.value,
        sellerId: sellerId.value,
      })
    )
  }

  async function login(emailInput, password) {
    const { data } = await authApi.login(emailInput, password)
    token.value = data.token
    email.value = data.email
    nameSeller.value = data.nameSeller
    sellerId.value = data.sellerId
    persist()
  }

  async function register(form) {
    const { data } = await authApi.register(form)
    token.value = data.token
    email.value = data.email
    nameSeller.value = data.nameSeller
    sellerId.value = data.sellerId
    persist()
  }

  function logout() {
    token.value = null
    email.value = null
    nameSeller.value = null
    sellerId.value = null
    localStorage.removeItem(STORAGE_KEY)
  }

  return { token, email, nameSeller, sellerId, isLoggedIn, login, register, logout }
})
