<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { RouterLink, useRouter } from 'vue-router'
import { useAuthStore } from '../stores/auth'

const auth = useAuthStore()
const router = useRouter()
const menuOpen = ref(false)
const searchQuery = ref('')

const navLinks = [
  { to: '/products?cat=רהיטים', label: 'רהיטים' },
  { to: '/products?cat=אלקטרוניקה', label: 'אלקטרוניקה' },
  { to: '/products?cat=כלי_נגינה', label: 'כלי נגינה' },
  { to: '/products', label: 'מוצרים' },
  { to: '/categories', label: 'קטגוריות' },
]

function closeMenu() {
  menuOpen.value = false
}

function onSearch() {
  if (searchQuery.value.trim()) {
    router.push({ path: '/products', query: { q: searchQuery.value.trim() } })
  }
}
</script>

<template>
  <header class="site-header">
    <div class="top-bar">
      <div class="container top-inner">
        <RouterLink to="/" class="logo" @click="closeMenu">
          <span class="logo-yad">Hand</span><span class="logo-num">2</span>
        </RouterLink>

        <nav class="top-nav" :class="{ open: menuOpen }">
          <RouterLink v-for="link in navLinks" :key="link.to" :to="link.to" @click="closeMenu">
            {{ link.label }}
          </RouterLink>
        </nav>

        <div class="top-actions">
          <RouterLink :to="auth.isLoggedIn ? '/sell' : '/login'" class="btn-publish">
            + פרסום מודעה
          </RouterLink>
          <RouterLink to="/login" class="icon-btn" title="התחברות">
            <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/>
            </svg>
            <span v-if="!auth.isLoggedIn">התחברות</span>
            <span v-else>{{ auth.nameSeller?.split(' ')[0] }}</span>
          </RouterLink>
          <button v-if="auth.isLoggedIn" class="icon-btn logout" @click="auth.logout()">יציאה</button>
          <button class="menu-btn" @click="menuOpen = !menuOpen" aria-label="תפריט">
            <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <line x1="3" y1="6" x2="21" y2="6"/><line x1="3" y1="12" x2="21" y2="12"/><line x1="3" y1="18" x2="21" y2="18"/>
            </svg>
          </button>
        </div>
      </div>
    </div>

    <div class="search-hero">
      <div class="hero-bg"></div>
      <div class="container search-wrap">
        <form class="search-form" @submit.prevent="onSearch">
          <button type="submit" class="search-icon" aria-label="חיפוש">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#757575" stroke-width="2.5">
              <circle cx="11" cy="11" r="8"/><line x1="21" y1="21" x2="16.65" y2="16.65"/>
            </svg>
          </button>
          <input
            v-model="searchQuery"
            type="search"
            placeholder="מה תרצו לחפש?"
            autocomplete="off"
          />
        </form>
        <RouterLink to="/categories" class="btn-categories">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="currentColor">
            <rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/>
            <rect x="3" y="14" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/>
          </svg>
          כל הקטגוריות
        </RouterLink>
      </div>
    </div>
  </header>
</template>

<style scoped>
.site-header {
  position: sticky;
  top: 0;
  z-index: 200;
  background: white;
  box-shadow: 0 1px 3px rgba(0,0,0,0.06);
}

.top-bar {
  border-bottom: 1px solid var(--border-light);
}

.top-inner {
  display: flex;
  align-items: center;
  height: var(--header-h);
  gap: 1.5rem;
}

.logo {
  font-size: 1.75rem;
  font-weight: 800;
  letter-spacing: -0.5px;
  flex-shrink: 0;
}

.logo-yad {
  color: var(--yad2-orange);
}

.logo-num {
  color: var(--text-dark);
}

.top-nav {
  display: flex;
  gap: 1.25rem;
  flex: 1;
  justify-content: center;
}

.top-nav a {
  font-size: 0.9rem;
  color: var(--text);
  font-weight: 500;
  padding: 0.25rem 0;
  border-bottom: 2px solid transparent;
}

.top-nav a:hover,
.top-nav a.router-link-active {
  color: var(--yad2-orange);
  border-bottom-color: var(--yad2-orange);
}

.top-actions {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  flex-shrink: 0;
}

.btn-publish {
  background: var(--yad2-orange);
  color: white;
  padding: 0.45rem 1rem;
  border-radius: var(--radius-pill);
  font-size: 0.875rem;
  font-weight: 600;
  border: 2px solid var(--yad2-orange);
  transition: background 0.15s;
}

.btn-publish:hover {
  background: var(--yad2-orange-hover);
  border-color: var(--yad2-orange-hover);
}

.icon-btn {
  display: flex;
  align-items: center;
  gap: 0.35rem;
  color: var(--text);
  font-size: 0.85rem;
  background: none;
  border: none;
  cursor: pointer;
  padding: 0.25rem;
}

.icon-btn:hover {
  color: var(--yad2-orange);
}

.logout {
  font-size: 0.8rem;
  color: var(--text-muted);
}

.menu-btn {
  display: none;
  background: none;
  border: none;
  cursor: pointer;
  color: var(--text);
  padding: 0.25rem;
}

.search-hero {
  position: relative;
  padding: 1.25rem 0;
  overflow: hidden;
}

.hero-bg {
  position: absolute;
  inset: 0;
  background: url('https://images.unsplash.com/photo-1616486338812-3dadae4b4ace?w=1400&h=200&fit=crop&q=80') center/cover no-repeat;
  opacity: 0.35;
}

.search-wrap {
  position: relative;
  display: flex;
  gap: 0.75rem;
  align-items: center;
}

.search-form {
  flex: 1;
  display: flex;
  align-items: center;
  background: white;
  border: 1px solid var(--border);
  border-radius: var(--radius-pill);
  padding: 0.35rem 1rem;
  box-shadow: var(--shadow);
}

.search-icon {
  background: none;
  border: none;
  cursor: pointer;
  display: flex;
  padding: 0.25rem;
  margin-inline-end: 0.5rem;
}

.search-form input {
  flex: 1;
  border: none;
  outline: none;
  font-size: 1rem;
  background: transparent;
  color: var(--text);
}

.search-form input::placeholder {
  color: #aaa;
}

.btn-categories {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  background: #363636;
  color: white;
  padding: 0.65rem 1.25rem;
  border-radius: var(--radius-pill);
  font-size: 0.875rem;
  font-weight: 600;
  white-space: nowrap;
  flex-shrink: 0;
}

.btn-categories:hover {
  background: #1a1a1a;
}

@media (max-width: 900px) {
  .top-nav {
    display: none;
    position: absolute;
    top: var(--header-h);
    inset-inline: 0;
    background: white;
    flex-direction: column;
    padding: 1rem;
    box-shadow: var(--shadow-md);
    border-bottom: 1px solid var(--border);
  }

  .top-nav.open {
    display: flex;
  }

  .menu-btn {
    display: flex;
  }

  .top-bar {
    position: relative;
  }

  .search-wrap {
    flex-direction: column;
  }

  .btn-categories {
    width: 100%;
    justify-content: center;
  }
}
</style>
