<script setup>
import { ref, onMounted } from 'vue'
import { RouterLink } from 'vue-router'
import { categoriesApi } from '../api/catalog'
import { productsApi } from '../api/products'
import { CATEGORY_NAV, formatCategory } from '../utils/images'
import { MOCK_PRODUCTS } from '../data/mockProducts'

const counts = ref({})

onMounted(async () => {
  try {
    const { data } = await productsApi.getAll()
    const map = {}
    data.forEach((p) => {
      const key = p.categor?.categoryName
      if (key) map[key] = (map[key] || 0) + 1
    })
    counts.value = map
  } catch {
    const map = {}
    MOCK_PRODUCTS.forEach((p) => {
      const key = p.categor?.categoryName
      if (key) map[key] = (map[key] || 0) + 1
    })
    counts.value = map
  }
})

const mainCategories = [
  { key: 'רהיטים', label: 'רהיטים', image: 'https://images.unsplash.com/photo-1555041469-a586c0361544?w=400&h=300&fit=crop&q=80' },
  { key: 'כלי_נגינה', label: 'כלי נגינה', image: 'https://images.unsplash.com/photo-1516925856310-b593457a71a5?w=400&h=300&fit=crop&q=80' },
  { key: 'אלקטרוניקה', label: 'אלקטרוניקה', image: 'https://images.unsplash.com/photo-1498049794561-7780e7231661?w=400&h=300&fit=crop&q=80' },
  { key: 'בלי_חיים', label: 'אביזרים', image: 'https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=400&h=300&fit=crop&q=80' },
  { key: 'שונות', label: 'שונות', image: 'https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400&h=300&fit=crop&q=80' },
]
</script>

<template>
  <div class="categories-page container">
    <nav class="breadcrumb">
      <RouterLink to="/">דף הבית</RouterLink>
      <span class="sep">/</span>
      <span>קטגוריות</span>
    </nav>

    <h1 class="page-title">כל הקטגוריות</h1>

    <div class="main-grid">
      <RouterLink
        v-for="cat in mainCategories"
        :key="cat.key"
        :to="`/products?cat=${cat.key}`"
        class="main-cat"
      >
        <img :src="cat.image" :alt="cat.label" loading="lazy" />
        <div class="main-cat-info">
          <h2>{{ cat.label }}</h2>
          <span>{{ counts[cat.key] || 0 }} מודעות</span>
        </div>
      </RouterLink>
    </div>

    <h2 class="sub-title">תת-קטגוריות</h2>
    <div class="cat-scroll">
      <RouterLink
        v-for="(cat, i) in CATEGORY_NAV"
        :key="i"
        :to="`/products?cat=${cat.key}`"
        class="cat-item"
      >
        <div class="cat-circle">
          <img :src="cat.image" :alt="cat.label" loading="lazy" />
        </div>
        <span>{{ cat.label }}</span>
      </RouterLink>
    </div>
  </div>
</template>

<style scoped>
.categories-page {
  padding: 1rem 0 3rem;
}

.page-title {
  font-size: 1.75rem;
  font-weight: 700;
  margin-bottom: 1.5rem;
}

.main-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 1rem;
  margin-bottom: 2.5rem;
}

.main-cat {
  border-radius: var(--radius);
  overflow: hidden;
  border: 1px solid var(--border-light);
  transition: box-shadow 0.15s;
}

.main-cat:hover {
  box-shadow: var(--shadow-md);
}

.main-cat img {
  width: 100%;
  height: 140px;
  object-fit: cover;
}

.main-cat-info {
  padding: 0.85rem 1rem;
}

.main-cat-info h2 {
  font-size: 1rem;
  font-weight: 600;
  margin-bottom: 0.2rem;
}

.main-cat-info span {
  font-size: 0.8rem;
  color: var(--text-muted);
}

.sub-title {
  font-size: 1.1rem;
  font-weight: 600;
  margin-bottom: 1rem;
}

.cat-scroll {
  display: flex;
  gap: 1rem;
  overflow-x: auto;
  padding-bottom: 0.5rem;
}

.cat-item {
  flex-shrink: 0;
  text-align: center;
  width: 88px;
}

.cat-circle {
  width: 72px;
  height: 72px;
  border-radius: 50%;
  overflow: hidden;
  margin: 0 auto 0.4rem;
  border: 2px solid var(--border-light);
}

.cat-item:hover .cat-circle {
  border-color: var(--yad2-orange);
}

.cat-circle img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.cat-item span {
  font-size: 0.75rem;
  color: var(--text);
}
</style>
