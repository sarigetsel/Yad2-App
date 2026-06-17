<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useRoute } from 'vue-router'
import { productsApi } from '../api/products'
import { categoriesApi, areasApi } from '../api/catalog'
import ProductCard from '../components/ProductCard.vue'
import LoadingSpinner from '../components/LoadingSpinner.vue'
import { formatCategory } from '../utils/images'

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
    products.value = prodRes.data
    categories.value = catRes.data
    areas.value = areaRes.data
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

  if (minPrice.value) {
    list = list.filter((p) => p.price >= Number(minPrice.value))
  }

  if (maxPrice.value) {
    list = list.filter((p) => p.price <= Number(maxPrice.value))
  }

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
  <div class="page container">
    <div class="page-header">
      <h1 class="section-title">כל המודעות</h1>
      <p class="section-subtitle">{{ filtered.length }} מוצרים נמצאו</p>
    </div>

    <div class="layout">
      <aside class="filters card">
        <h3>סינון</h3>

        <div class="form-group">
          <label>חיפוש</label>
          <input v-model="search" type="search" placeholder="שם מוצר..." />
        </div>

        <div class="form-group">
          <label>קטגוריה</label>
          <select v-model="selectedCat">
            <option value="">הכל</option>
            <option v-for="c in categories" :key="c.id" :value="c.categoryName">
              {{ formatCategory(c.categoryName) }}
            </option>
          </select>
        </div>

        <div class="form-group">
          <label>אזור</label>
          <select v-model="selectedArea">
            <option value="">הכל</option>
            <option v-for="a in areas" :key="a.id" :value="a.areaName">
              {{ formatCategory(a.areaName) }}
            </option>
          </select>
        </div>

        <div class="form-group">
          <label>מצב</label>
          <select v-model="selectedStatus">
            <option value="">הכל</option>
            <option v-for="s in statuses" :key="s" :value="s">
              {{ formatCategory(s) }}
            </option>
          </select>
        </div>

        <div class="price-row">
          <div class="form-group">
            <label>מ-₪</label>
            <input v-model="minPrice" type="number" min="0" placeholder="0" />
          </div>
          <div class="form-group">
            <label>עד-₪</label>
            <input v-model="maxPrice" type="number" min="0" placeholder="99999" />
          </div>
        </div>

        <button class="btn btn-outline full" @click="resetFilters">נקה סינון</button>
      </aside>

      <div class="results">
        <div class="sort-bar">
          <label>מיון:</label>
          <select v-model="sortBy">
            <option value="newest">חדש ביותר</option>
            <option value="price-asc">מחיר: נמוך לגבוה</option>
            <option value="price-desc">מחיר: גבוה לנמוך</option>
          </select>
        </div>

        <LoadingSpinner v-if="loading" size="lg" />

        <div v-else-if="filtered.length" class="grid-products">
          <ProductCard v-for="p in filtered" :key="p.id" :product="p" />
        </div>

        <div v-else class="empty card">
          <span>🔍</span>
          <h3>לא נמצאו מוצרים</h3>
          <p>נסו לשנות את הסינון או לחפש משהו אחר</p>
          <button class="btn btn-primary" @click="resetFilters">נקה סינון</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.layout {
  display: grid;
  grid-template-columns: 280px 1fr;
  gap: 2rem;
  align-items: start;
}

.filters {
  padding: 1.5rem;
  position: sticky;
  top: calc(var(--header-h) + 1rem);
}

.filters h3 {
  margin-bottom: 1.25rem;
  font-size: 1.1rem;
}

.price-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 0.75rem;
}

.full {
  width: 100%;
  margin-top: 0.5rem;
}

.sort-bar {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  margin-bottom: 1.5rem;
}

.sort-bar select {
  padding: 0.5rem 1rem;
  border: 2px solid var(--border);
  border-radius: var(--radius);
  background: white;
}

.empty {
  text-align: center;
  padding: 4rem 2rem;
}

.empty span {
  font-size: 3rem;
}

.empty h3 {
  margin: 1rem 0 0.5rem;
}

.empty p {
  color: var(--text-muted);
  margin-bottom: 1.5rem;
}

@media (max-width: 768px) {
  .layout {
    grid-template-columns: 1fr;
  }

  .filters {
    position: static;
  }
}
</style>
