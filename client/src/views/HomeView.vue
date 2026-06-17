<script setup>
import { ref, onMounted } from 'vue'
import { RouterLink } from 'vue-router'
import { productsApi } from '../api/products'
import ProductCard from '../components/ProductCard.vue'
import LoadingSpinner from '../components/LoadingSpinner.vue'
import { formatCategory } from '../utils/images'

const products = ref([])
const loading = ref(true)
const search = ref('')

const categories = [
  { key: 'רהיטים', icon: '🛋️', desc: 'ספות, שולחנות, ארונות' },
  { key: 'כלי_נגינה', icon: '🎸', desc: 'גיטרות, פסנתרים, מיקrophones' },
  { key: 'אלקטרוניקה', icon: '📱', desc: 'טלפונים, מחשבים, קונsoles' },
  { key: 'בלי_חיים', icon: '👜', desc: 'תיקים, אביזרים, אופנה' },
  { key: 'שונות', icon: '📦', desc: 'כל מה שביניהם' },
]

onMounted(async () => {
  try {
    const { data } = await productsApi.getAll()
    products.value = data
  } catch {
    products.value = []
  } finally {
    loading.value = false
  }
})

function searchProducts() {
  if (search.value.trim()) {
    window.location.href = `/products?q=${encodeURIComponent(search.value)}`
  }
}
</script>

<template>
  <div>
    <section class="hero">
      <div class="container hero-content">
        <div class="hero-text">
          <span class="hero-tag">לוח יד 2 חדש בישראל</span>
          <h1>קנו ומכרו<br /><span>בקלות ובביטחון</span></h1>
          <p>אלפי מוצרים יד שנייה במחירים משתלמים. פרסמו מודעה בחינם והגיעו לקונים בכל הארץ.</p>
          <form class="hero-search" @submit.prevent="searchProducts">
            <input v-model="search" type="search" placeholder="חפשו גיטרה, ספה, אייפון..." />
            <button type="submit" class="btn btn-primary">חיפוש</button>
          </form>
          <div class="hero-stats">
            <div><strong>{{ products.length || '15+' }}</strong><span>מודעות פעילות</span></div>
            <div><strong>5</strong><span>קטגוריות</span></div>
            <div><strong>7</strong><span>אזורים</span></div>
          </div>
        </div>
        <div class="hero-visual">
          <div class="hero-card card">
            <img src="https://images.unsplash.com/photo-1516925856310-b593457a71a5?w=500&q=80" alt="גיטרה" />
            <div class="hero-card-info">
              <span>כלי נגינה</span>
              <strong>גיטרה Yamaha</strong>
              <em>₪850</em>
            </div>
          </div>
          <div class="hero-card card card-2">
            <img src="https://images.unsplash.com/photo-1555041469-a586c0361544?w=500&q=80" alt="ספה" />
            <div class="hero-card-info">
              <span>רהיטים</span>
              <strong>ספה 3 מושבים</strong>
              <em>₪1,350</em>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="categories-section container">
      <h2 class="section-title">קטגוריות פופולריות</h2>
      <p class="section-subtitle">מצאו בדיוק מה שאתם מחפשים</p>
      <div class="categories-grid">
        <RouterLink
          v-for="cat in categories"
          :key="cat.key"
          :to="`/products?cat=${cat.key}`"
          class="category-card card"
        >
          <span class="cat-icon">{{ cat.icon }}</span>
          <h3>{{ formatCategory(cat.key) }}</h3>
          <p>{{ cat.desc }}</p>
        </RouterLink>
      </div>
    </section>

    <section class="featured container">
      <div class="featured-header">
        <div>
          <h2 class="section-title">מודעות אחרונות</h2>
          <p class="section-subtitle">נוספו לאחרונה ללוח</p>
        </div>
        <RouterLink to="/products" class="btn btn-outline">הצג הכל ←</RouterLink>
      </div>

      <LoadingSpinner v-if="loading" size="lg" />
      <div v-else class="grid-products">
        <ProductCard v-for="p in products.slice(0, 8)" :key="p.id" :product="p" />
      </div>
    </section>

    <section class="cta-section">
      <div class="container cta-inner card">
        <div>
          <h2>יש לכם משהו למכור?</h2>
          <p>פרסמו מודעה בחינם והגיעו לאלפי קונים פוטנציאליים</p>
        </div>
        <RouterLink to="/sell" class="btn btn-accent">פרסם עכשיו</RouterLink>
      </div>
    </section>
  </div>
</template>

<style scoped>
.hero {
  background: linear-gradient(135deg, #eff6ff 0%, #f8fafc 50%, #fef3c7 100%);
  padding: 4rem 0 5rem;
  overflow: hidden;
}

.hero-content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 3rem;
  align-items: center;
}

.hero-tag {
  display: inline-block;
  background: var(--primary-light);
  color: var(--primary);
  padding: 0.35rem 1rem;
  border-radius: 999px;
  font-size: 0.85rem;
  font-weight: 600;
  margin-bottom: 1rem;
}

.hero-text h1 {
  font-size: clamp(2.2rem, 5vw, 3.5rem);
  font-weight: 800;
  line-height: 1.15;
  margin-bottom: 1rem;
}

.hero-text h1 span {
  background: linear-gradient(135deg, var(--primary), #7c3aed);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.hero-text > p {
  color: var(--text-muted);
  font-size: 1.1rem;
  max-width: 480px;
  margin-bottom: 1.5rem;
}

.hero-search {
  display: flex;
  gap: 0.5rem;
  max-width: 480px;
  margin-bottom: 2rem;
}

.hero-search input {
  flex: 1;
  padding: 0.85rem 1.25rem;
  border: 2px solid var(--border);
  border-radius: 999px;
  font-size: 1rem;
}

.hero-search input:focus {
  outline: none;
  border-color: var(--primary);
}

.hero-stats {
  display: flex;
  gap: 2.5rem;
}

.hero-stats strong {
  display: block;
  font-size: 1.5rem;
  color: var(--primary);
}

.hero-stats span {
  font-size: 0.85rem;
  color: var(--text-muted);
}

.hero-visual {
  position: relative;
  height: 400px;
}

.hero-card {
  position: absolute;
  width: 240px;
  overflow: hidden;
}

.hero-card:first-child {
  top: 20px;
  inset-inline-end: 60px;
  transform: rotate(3deg);
  z-index: 2;
}

.hero-card.card-2 {
  bottom: 20px;
  inset-inline-start: 20px;
  transform: rotate(-4deg);
}

.hero-card img {
  width: 100%;
  height: 160px;
  object-fit: cover;
}

.hero-card-info {
  padding: 0.75rem 1rem;
}

.hero-card-info span {
  font-size: 0.75rem;
  color: var(--primary);
  font-weight: 600;
}

.hero-card-info strong {
  display: block;
  font-size: 0.9rem;
}

.hero-card-info em {
  color: var(--primary);
  font-style: normal;
  font-weight: 800;
}

.categories-section,
.featured {
  padding: 4rem 0;
}

.categories-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 1rem;
}

.category-card {
  padding: 1.5rem;
  text-align: center;
  transition: transform 0.2s, box-shadow 0.2s;
}

.category-card:hover {
  transform: translateY(-3px);
  box-shadow: var(--shadow-lg);
}

.cat-icon {
  font-size: 2.5rem;
  display: block;
  margin-bottom: 0.75rem;
}

.category-card h3 {
  font-size: 1.1rem;
  margin-bottom: 0.35rem;
}

.category-card p {
  font-size: 0.85rem;
  color: var(--text-muted);
}

.featured-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  margin-bottom: 0.5rem;
  flex-wrap: wrap;
  gap: 1rem;
}

.cta-section {
  padding: 2rem 0 4rem;
}

.cta-inner {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 2.5rem 3rem;
  background: linear-gradient(135deg, var(--primary), #7c3aed);
  color: white;
  gap: 2rem;
  flex-wrap: wrap;
}

.cta-inner h2 {
  font-size: 1.75rem;
  margin-bottom: 0.35rem;
}

.cta-inner p {
  opacity: 0.9;
}

@media (max-width: 900px) {
  .hero-content {
    grid-template-columns: 1fr;
  }

  .hero-visual {
    display: none;
  }
}
</style>
