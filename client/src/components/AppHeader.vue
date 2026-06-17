<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { RouterLink } from 'vue-router'
import { useAuthStore } from '../stores/auth'

const auth = useAuthStore()
const menuOpen = ref(false)
const scrolled = ref(false)

function onScroll() {
  scrolled.value = window.scrollY > 20
}

onMounted(() => window.addEventListener('scroll', onScroll))
onUnmounted(() => window.removeEventListener('scroll', onScroll))

function closeMenu() {
  menuOpen.value = false
}
</script>

<template>
  <header class="header" :class="{ scrolled, 'menu-open': menuOpen }">
    <div class="container header-inner">
      <RouterLink to="/" class="logo" @click="closeMenu">
        <span class="logo-icon">H2</span>
        <span class="logo-text">Hand<strong>2</strong></span>
      </RouterLink>

      <nav class="nav" :class="{ open: menuOpen }">
        <RouterLink to="/" @click="closeMenu">בית</RouterLink>
        <RouterLink to="/products" @click="closeMenu">מודעות</RouterLink>
        <RouterLink to="/categories" @click="closeMenu">קטגוריות</RouterLink>
        <RouterLink to="/about" @click="closeMenu">אודות</RouterLink>
      </nav>

      <div class="header-actions">
        <RouterLink v-if="auth.isLoggedIn" to="/sell" class="btn btn-accent btn-sm">
          + פרסם מודעה
        </RouterLink>
        <template v-if="auth.isLoggedIn">
          <span class="user-greeting">שלום, {{ auth.nameSeller }}</span>
          <button class="btn btn-ghost btn-sm" @click="auth.logout()">יציאה</button>
        </template>
        <template v-else>
          <RouterLink to="/login" class="btn btn-outline btn-sm">התחברות</RouterLink>
          <RouterLink to="/register" class="btn btn-primary btn-sm">הרשמה</RouterLink>
        </template>
        <button class="menu-toggle" @click="menuOpen = !menuOpen" aria-label="תפריט">
          <span></span><span></span><span></span>
        </button>
      </div>
    </div>
  </header>
</template>

<style scoped>
.header {
  position: sticky;
  top: 0;
  z-index: 100;
  background: rgba(255, 255, 255, 0.92);
  backdrop-filter: blur(12px);
  border-bottom: 1px solid transparent;
  transition: all 0.3s;
}

.header.scrolled {
  border-bottom-color: var(--border);
  box-shadow: var(--shadow);
}

.header-inner {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: var(--header-h);
  gap: 1rem;
}

.logo {
  display: flex;
  align-items: center;
  gap: 0.6rem;
  font-size: 1.35rem;
  font-weight: 700;
}

.logo-icon {
  background: linear-gradient(135deg, var(--primary), #7c3aed);
  color: white;
  width: 40px;
  height: 40px;
  border-radius: 10px;
  display: grid;
  place-items: center;
  font-size: 0.85rem;
  font-weight: 800;
}

.logo-text strong {
  color: var(--primary);
}

.nav {
  display: flex;
  gap: 2rem;
}

.nav a {
  font-weight: 500;
  color: var(--text-muted);
  transition: color 0.2s;
  position: relative;
}

.nav a:hover,
.nav a.router-link-active {
  color: var(--primary);
}

.nav a.router-link-active::after {
  content: '';
  position: absolute;
  bottom: -4px;
  inset-inline: 0;
  height: 2px;
  background: var(--primary);
  border-radius: 2px;
}

.header-actions {
  display: flex;
  align-items: center;
  gap: 0.75rem;
}

.btn-sm {
  padding: 0.5rem 1rem;
  font-size: 0.875rem;
}

.user-greeting {
  font-size: 0.875rem;
  color: var(--text-muted);
  display: none;
}

.menu-toggle {
  display: none;
  flex-direction: column;
  gap: 5px;
  background: none;
  border: none;
  cursor: pointer;
  padding: 4px;
}

.menu-toggle span {
  width: 24px;
  height: 2px;
  background: var(--text);
  border-radius: 2px;
  transition: 0.3s;
}

@media (min-width: 900px) {
  .user-greeting {
    display: inline;
  }
}

@media (max-width: 899px) {
  .menu-toggle {
    display: flex;
  }

  .nav {
    position: fixed;
    top: var(--header-h);
    inset-inline: 0;
    background: white;
    flex-direction: column;
    padding: 1.5rem;
    gap: 1rem;
    box-shadow: var(--shadow-lg);
    transform: translateY(-120%);
    opacity: 0;
    pointer-events: none;
    transition: 0.3s;
  }

  .nav.open {
    transform: translateY(0);
    opacity: 1;
    pointer-events: all;
  }

  .user-greeting {
    display: none;
  }
}
</style>
