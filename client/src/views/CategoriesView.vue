<script setup>
import { ref, onMounted } from 'vue'
import { RouterLink } from 'vue-router'
import { categoriesApi } from '../api/catalog'
import { productsApi } from '../api/products'
import { formatCategory } from '../utils/images'

const categories = ref([])
const counts = ref({})

const categoryMeta = {
  רהיטים: { icon: '🛋️', color: '#fef3c7', desc: 'ספות, שולחנות, מיטות, ארונות ועוד' },
  כלי_נגינה: { icon: '🎸', color: '#ede9fe', desc: 'גיטרות, פסנתרים, תופים, מיקrophones' },
  אלקטרוניקה: { icon: '📱', color: '#dbeafe', desc: 'טלפונים, מחשבים, טלוויזיות, קונsoles' },
  בלי_חיים: { icon: '👜', color: '#fce7f3', desc: 'תיקים, שעונים, אביזרים ואופנה' },
  שונות: { icon: '📦', color: '#f0fdf4', desc: 'כל מה שלא נכנס לקטגוריות אחרות' },
}

onMounted(async () => {
  const [catRes, prodRes] = await Promise.all([
    categoriesApi.getAll(),
    productsApi.getAll(),
  ])
  categories.value = catRes.data
  const map = {}
  prodRes.data.forEach((p) => {
    const key = p.categor?.categoryName
    if (key) map[key] = (map[key] || 0) + 1
  })
  counts.value = map
})
</script>

<template>
  <div class="page container">
    <h1 class="section-title">קטגוריות</h1>
    <p class="section-subtitle">גלו מוצרים לפי תחום</p>

    <div class="cat-grid">
      <RouterLink
        v-for="cat in categories"
        :key="cat.id"
        :to="`/products?cat=${cat.categoryName}`"
        class="cat-card card"
        :style="{ '--cat-bg': categoryMeta[cat.categoryName]?.color || '#f1f5f9' }"
      >
        <span class="icon">{{ categoryMeta[cat.categoryName]?.icon || '📦' }}</span>
        <h2>{{ formatCategory(cat.categoryName) }}</h2>
        <p>{{ categoryMeta[cat.categoryName]?.desc }}</p>
        <span class="count">{{ counts[cat.categoryName] || 0 }} מודעות</span>
      </RouterLink>
    </div>
  </div>
</template>

<style scoped>
.cat-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 1.5rem;
}

.cat-card {
  padding: 2rem;
  background: var(--cat-bg);
  transition: transform 0.2s, box-shadow 0.2s;
}

.cat-card:hover {
  transform: translateY(-4px);
  box-shadow: var(--shadow-lg);
}

.icon {
  font-size: 3rem;
  display: block;
  margin-bottom: 1rem;
}

.cat-card h2 {
  font-size: 1.35rem;
  margin-bottom: 0.5rem;
}

.cat-card p {
  color: var(--text-muted);
  font-size: 0.9rem;
  margin-bottom: 1rem;
}

.count {
  font-size: 0.85rem;
  font-weight: 700;
  color: var(--primary);
}
</style>
