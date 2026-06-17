<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useRoute, RouterLink } from 'vue-router'
import { productsApi } from '../api/products'
import { categoriesApi, areasApi } from '../api/catalog'
import ProductCard from '../components/ProductCard.vue'
import LoadingSpinner from '../components/LoadingSpinner.vue'
import { CATEGORY_NAV, formatCategory } from '../utils/images'
import { MOCK_PRODUCTS, mergeWithImages } from '../data/mockProducts'

const route = useRoute()
const products = ref([])
const categories = ref([])
const areas = ref([])
const loading = ref(true)

const search = ref(route.query.q || '')
const selectedCat = ref(route.query.cat || '')
const selectedArea = ref('')
const selectedStatus = ref('')
const sortBy = ref('newest')
const minPrice = ref('')
const maxPrice = ref('')

const statuses = ['חדש', 'חדש_באריזה', 'כמו_חדש', 'ישן']

const pageTitle = computed(() => {
  if (selectedCat.value) return formatCategory(selectedCat.value)
  return 'מוצרים'
})

onMounted(loadData)

watch(() => route.query, () => {
  search.value = route.query.q || ''
  selectedCat.value = route.query.cat || ''
})

async function loadData() {
  loading.value = true
  try {
    const [prodRes, catRes, areaRes] = await Promise.all([
      productsApi.getAll(),
      categoriesApi.getAll(),
      areasApi.getAll(),
    ])
    products.value = mergeWithImages(prodRes.data)
    categories.value = catRes.data
    areas.value = areaRes.data
  } catch {
    products.value = MOCK_PRODUCTS
    categories.value = [
      { id: 1, categoryName: 'רהיטים' },
      { id: 2, categoryName: 'כלי_נגינה' },
      { id: 3, categoryName: 'אלקטרוניקה' },
      { id: 4, categoryName: 'בלי_חיים' },
      { id: 5, categoryName: 'שונות' },
    ]
    areas.value = [
      { id: 1, areaName: 'מרכז' },
      { id: 2, areaName: 'צפון' },
      { id: 3, areaName: 'דרום' },
      { id: 4, areaName: 'גוש_דן' },
      { id: 5, areaName: 'ירושלים' },
    ]
  } finally {
    loading.value = false
  }
}

const filtered = computed(() => {
  let list = [...products.value]

  if (search.value) {
    const q = search.value.toLowerCase()
    list = list.filter(
      (p) =>
        p.nameProduct?.toLowerCase().includes(q) ||
        p.description?.toLowerCase().includes(q)
    )
  }

  if (selectedCat.value) {
    list = list.filter((p) => p.categor?.categoryName === selectedCat.value)
  }

  if (selectedArea.value) {
    list = list.filter((p) => p.area?.areaName === selectedArea.value)
  }

  if (selectedStatus.value) {
    list = list.filter((p) => p.status === selectedStatus.value)
  }

  if (minPrice.value) list = list.filter((p) => p.price >= Number(minPrice.value))
  if (maxPrice.value) list = list.filter((p) => p.price <= Number(maxPrice.value))

  if (sortBy.value === 'price-asc') list.sort((a, b) => a.price - b.price)
  else if (sortBy.value === 'price-desc') list.sort((a, b) => b.price - a.price)
  else list.sort((a, b) => b.id - a.id)

  return list
})

function resetFilters() {
  search.value = ''
  selectedCat.value = ''
  selectedArea.value = ''
  selectedStatus.value = ''
  minPrice.value = ''
  maxPrice.value = ''
  sortBy.value = 'newest'
}
</script>

<template>
  <div class="products-page container">
    <nav class="breadcrumb">
      <RouterLink to="/">דף הבית</RouterLink>
      <span class="sep">/</span>
      <RouterLink to="/products">מוצרים</RouterLink>
      <template v-if="selectedCat">
        <span class="sep">/</span>
        <span>{{ formatCategory(selectedCat) }}</span>
      </template>
    </nav>

    <h1 class="page-title">{{ pageTitle }}</h1>

    <div class="cat-scroll">
      <RouterLink
        v-for="(cat, i) in CATEGORY_NAV"
        :key="i"
        :to="`/products?cat=${cat.key}`"
        class="cat-item"
        :class="{ active: selectedCat === cat.key && cat.label.includes(formatCategory(selectedCat).split(' ')[0]) }"
      >
        <div class="cat-circle">
          <img :src="cat.image" :alt="cat.label" loading="lazy" />
        </div>
        <span>{{ cat.label }}</span>
      </RouterLink>
    </div>

    <div class="filters-row">
      <select v-model="selectedArea" class="filter-select">
        <option value="">עיר/אזור</option>
        <option v-for="a in areas" :key="a.id ?? a.ID" :value="a.areaName">
          {{ formatCategory(a.areaName) }}
        </option>
      </select>
      <select v-model="minPrice" class="filter-select">
        <option value="">מחיר</option>
        <option value="500">מ-₪500</option>
        <option value="1000">מ-₪1,000</option>
        <option value="2000">מ-₪2,000</option>
      </select>
      <select v-model="selectedStatus" class="filter-select">
        <option value="">מצב המוצר</option>
        <option v-for="s in statuses" :key="s" :value="s">{{ formatCategory(s) }}</option>
      </select>
      <select v-model="selectedCat" class="filter-select">
        <option value="">סוג מוצר</option>
        <option v-for="c in categories" :key="c.id" :value="c.categoryName">
          {{ formatCategory(c.categoryName) }}
        </option>
      </select>
      <button class="filter-all" @click="resetFilters">
        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <line x1="4" y1="6" x2="20" y2="6"/><line x1="4" y1="12" x2="20" y2="12"/><line x1="4" y1="18" x2="20" y2="18"/>
          <circle cx="8" cy="6" r="2" fill="white"/><circle cx="16" cy="12" r="2" fill="white"/><circle cx="10" cy="18" r="2" fill="white"/>
        </svg>
        כל הסינונים
      </button>
    </div>

    <div class="results-bar">
      <span class="count">{{ filtered.length }} מודעות</span>
      <div class="sort-wrap">
        <select v-model="sortBy" class="filter-select sort">
          <option value="newest">רלוונטיות</option>
          <option value="price-asc">מחיר: נמוך לגבוה</option>
          <option value="price-desc">מחיר: גבוה לנמוך</option>
        </select>
      </div>
    </div>

    <LoadingSpinner v-if="loading" size="lg" />

    <div v-else-if="filtered.length" class="grid-products">
      <ProductCard v-for="p in filtered" :key="p.id" :product="p" />
    </div>

    <div v-else class="empty">
      <p>לא נמצאו מודעות</p>
      <button class="btn btn-primary" @click="resetFilters">נקה סינון</button>
    </div>
  </div>
</template>

<style scoped>
.products-page {
  padding: 1rem 0 3rem;
}

.page-title {
  font-size: 1.75rem;
  font-weight: 700;
  color: var(--text-dark);
  margin-bottom: 1.25rem;
}

.cat-scroll {
  display: flex;
  gap: 1rem;
  overflow-x: auto;
  padding-bottom: 1.25rem;
  margin-bottom: 0.5rem;
  border-bottom: 1px solid var(--border-light);
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
  border: 2px solid transparent;
}

.cat-item:hover .cat-circle,
.cat-item.active .cat-circle {
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
  line-height: 1.25;
}

.filters-row {
  display: flex;
  gap: 0.5rem;
  flex-wrap: wrap;
  padding: 1rem 0;
  align-items: center;
}

.filter-all {
  display: flex;
  align-items: center;
  gap: 0.35rem;
  padding: 0.5rem 1rem;
  border: 1px solid var(--border);
  border-radius: var(--radius-pill);
  background: white;
  font-size: 0.875rem;
  cursor: pointer;
  color: var(--text);
  margin-inline-start: auto;
}

.filter-all:hover {
  border-color: var(--text);
}

.results-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
  padding-bottom: 0.75rem;
}

.count {
  font-size: 0.875rem;
  color: var(--text-muted);
}

.sort {
  min-width: 130px;
}

.empty {
  text-align: center;
  padding: 3rem;
  color: var(--text-muted);
}

.empty .btn {
  margin-top: 1rem;
}
</style>
