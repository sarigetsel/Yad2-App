<script setup>
import { ref } from 'vue'
import { RouterLink } from 'vue-router'
import { getProductImage, formatPrice, formatCategory, formatStatus } from '../utils/images'

const props = defineProps({
  product: { type: Object, required: true },
})

const liked = ref(false)
const imgSrc = ref(getProductImage(props.product))

function onImgError() {
  imgSrc.value = getProductImage({ categor: props.product?.categor })
}

function toggleLike(e) {
  e.preventDefault()
  e.stopPropagation()
  liked.value = !liked.value
}
</script>

<template>
  <RouterLink :to="`/products/${product.id}`" class="yad-card">
    <div class="img-box">
      <img
        :src="imgSrc"
        :alt="product.nameProduct"
        loading="lazy"
        @error="onImgError"
      />
      <button class="heart" :class="{ active: liked }" @click="toggleLike" aria-label="שמירה">
        <svg width="20" height="20" viewBox="0 0 24 24" :fill="liked ? '#ff7100' : 'none'" stroke="currentColor" stroke-width="2">
          <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"/>
        </svg>
      </button>
    </div>
    <div class="info">
      <p class="price">{{ formatPrice(product.price) }}</p>
      <h3>{{ product.nameProduct }}</h3>
      <p class="meta">
        <span v-if="product.area">{{ formatCategory(product.area.areaName) }}</span>
        <span v-if="product.status" class="dot">·</span>
        <span v-if="product.status">{{ formatStatus(product.status) }}</span>
      </p>
    </div>
  </RouterLink>
</template>

<style scoped>
.yad-card {
  display: block;
  background: white;
  border-radius: var(--radius);
  overflow: hidden;
  transition: box-shadow 0.15s;
}

.yad-card:hover {
  box-shadow: var(--shadow-md);
}

.img-box {
  position: relative;
  aspect-ratio: 4/3;
  background: #f5f5f5;
  overflow: hidden;
}

.img-box img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.heart {
  position: absolute;
  top: 0.5rem;
  inset-inline-start: 0.5rem;
  background: rgba(255,255,255,0.92);
  border: none;
  border-radius: 50%;
  width: 34px;
  height: 34px;
  display: grid;
  place-items: center;
  cursor: pointer;
  color: #757575;
  box-shadow: 0 1px 3px rgba(0,0,0,0.12);
}

.heart.active {
  color: var(--yad2-orange);
}

.info {
  padding: 0.65rem 0.5rem 0.75rem;
}

.price {
  font-size: 1.15rem;
  font-weight: 700;
  color: var(--text-dark);
  margin-bottom: 0.2rem;
}

.info h3 {
  font-size: 0.875rem;
  font-weight: 400;
  color: var(--text);
  line-height: 1.4;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  margin-bottom: 0.25rem;
}

.meta {
  font-size: 0.78rem;
  color: var(--text-muted);
}

.dot {
  margin: 0 0.2rem;
}
</style>
