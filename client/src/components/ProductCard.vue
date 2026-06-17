<script setup>
import { ref } from 'vue'
import { RouterLink } from 'vue-router'
import { getProductImage, formatPrice, formatCategory, formatStatus } from '../utils/images'

defineProps({
  product: { type: Object, required: true },
})

const imgError = ref(false)

function onImgError(e) {
  imgError.value = true
  e.target.src = getProductImage({ categor: { categoryName: 'שונות' } })
}
</script>

<template>
  <RouterLink :to="`/products/${product.id}`" class="product-card card">
    <div class="image-wrap">
      <img
        :src="getProductImage(product)"
        :alt="product.nameProduct"
        loading="lazy"
        @error="onImgError"
      />
      <span v-if="product.status" class="badge">{{ formatStatus(product.status) }}</span>
    </div>
    <div class="card-body">
      <span v-if="product.categor" class="category">
        {{ formatCategory(product.categor.categoryName) }}
      </span>
      <h3>{{ product.nameProduct }}</h3>
      <div class="meta">
        <span class="price">{{ formatPrice(product.price) }}</span>
        <span v-if="product.area" class="area">
          📍 {{ formatCategory(product.area.areaName) }}
        </span>
      </div>
    </div>
  </RouterLink>
</template>

<style scoped>
.product-card {
  transition: transform 0.25s, box-shadow 0.25s;
  display: flex;
  flex-direction: column;
}

.product-card:hover {
  transform: translateY(-4px);
  box-shadow: var(--shadow-lg);
}

.image-wrap {
  position: relative;
  aspect-ratio: 4/3;
  overflow: hidden;
  background: var(--primary-light);
}

.image-wrap img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.4s;
}

.product-card:hover img {
  transform: scale(1.05);
}

.badge {
  position: absolute;
  top: 0.75rem;
  inset-inline-start: 0.75rem;
  background: rgba(255, 255, 255, 0.95);
  padding: 0.25rem 0.75rem;
  border-radius: 999px;
  font-size: 0.75rem;
  font-weight: 600;
  color: var(--primary);
}

.card-body {
  padding: 1rem 1.25rem 1.25rem;
  flex: 1;
  display: flex;
  flex-direction: column;
}

.category {
  font-size: 0.75rem;
  color: var(--primary);
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.03em;
}

.card-body h3 {
  font-size: 1rem;
  font-weight: 700;
  margin: 0.35rem 0 0.75rem;
  line-height: 1.4;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.meta {
  margin-top: auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 0.5rem;
}

.price {
  font-size: 1.2rem;
  font-weight: 800;
  color: var(--primary);
}

.area {
  font-size: 0.8rem;
  color: var(--text-muted);
}
</style>
