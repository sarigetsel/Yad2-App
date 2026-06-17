<script setup>
import { ref, onMounted } from 'vue'
import { RouterLink } from 'vue-router'
import { productsApi } from '../api/products'
import ProductCard from '../components/ProductCard.vue'
import LoadingSpinner from '../components/LoadingSpinner.vue'
import { CATEGORY_NAV, formatCategory } from '../utils/images'
import { MOCK_PRODUCTS, mergeWithImages } from '../data/mockProducts'

const products = ref([])
const loading = ref(true)

onMounted(async () => {
  try {
    const { data } = await productsApi.getAll()
    products.value = mergeWithImages(data)
  } catch {
    products.value = MOCK_PRODUCTS
  } finally {
    loading.value = false
  }
})
</script>

<template>
  <div class="home-page">
    <section class="container cat-section">
      <h2 class="cat-heading">קטגוריות פופולריות</h2>
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
    </section>

    <section class="container listings">
      <div class="listings-head">
        <h1>מודעות אחרונות</h1>
        <RouterLink to="/products" class="see-all">הצג הכל</RouterLink>
      </div>

      <LoadingSpinner v-if="loading" size="lg" />
      <div v-else class="grid-products">
        <ProductCard v-for="p in products.slice(0, 12)" :key="p.id" :product="p" />
      </div>
    </section>
  </div>
</template>

<style scoped>
.home-page {
  padding-bottom: 2rem;
}

.cat-section {
  padding: 1.5rem 0 0.5rem;
}

.cat-heading {
  font-size: 1.1rem;
  font-weight: 600;
  color: var(--text-dark);
  margin-bottom: 1rem;
}

.cat-scroll {
  display: flex;
  gap: 1.25rem;
  overflow-x: auto;
  padding-bottom: 0.75rem;
  scrollbar-width: thin;
}

.cat-item {
  flex-shrink: 0;
  text-align: center;
  width: 90px;
}

.cat-circle {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  overflow: hidden;
  margin: 0 auto 0.5rem;
  border: 2px solid var(--border-light);
  transition: border-color 0.15s;
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
  font-size: 0.78rem;
  color: var(--text);
  line-height: 1.3;
  display: block;
}

.listings {
  padding: 1.5rem 0;
}

.listings-head {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.listings-head h1 {
  font-size: 1.5rem;
  font-weight: 700;
  color: var(--text-dark);
}

.see-all {
  color: var(--yad2-orange);
  font-size: 0.9rem;
  font-weight: 600;
}

.see-all:hover {
  text-decoration: underline;
}
</style>
