<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, RouterLink } from 'vue-router'
import { productsApi } from '../api/products'
import LoadingSpinner from '../components/LoadingSpinner.vue'
import { getProductImage, formatPrice, formatCategory, formatStatus } from '../utils/images'

const route = useRoute()
const product = ref(null)
const loading = ref(true)
const imgSrc = ref('')

onMounted(async () => {
  try {
    const { data } = await productsApi.getById(route.params.id)
    product.value = data
    imgSrc.value = getProductImage(data)
  } finally {
    loading.value = false
  }
})

function onImgError() {
  imgSrc.value = getProductImage({ categor: product.value?.categor })
}

function callSeller() {
  if (product.value?.phoneNumber) {
    window.location.href = `tel:${product.value.phoneNumber}`
  }
}
</script>

<template>
  <div class="page container">
    <LoadingSpinner v-if="loading" size="lg" />

    <template v-else-if="product">
      <nav class="breadcrumb">
        <RouterLink to="/">בית</RouterLink>
        <span>/</span>
        <RouterLink to="/products">מודעות</RouterLink>
        <span>/</span>
        <span>{{ product.nameProduct }}</span>
      </nav>

      <div class="detail-grid">
        <div class="gallery card">
          <img :src="imgSrc" :alt="product.nameProduct" @error="onImgError" />
        </div>

        <div class="info">
          <span v-if="product.categor" class="tag">
            {{ formatCategory(product.categor.categoryName) }}
          </span>
          <h1>{{ product.nameProduct }}</h1>
          <div class="price-row">
            <span class="price">{{ formatPrice(product.price) }}</span>
            <span v-if="product.status" class="status-badge">
              {{ formatStatus(product.status) }}
            </span>
          </div>

          <div class="details-list card">
            <div v-if="product.area">
              <span>📍 אזור</span>
              <strong>{{ formatCategory(product.area.areaName) }}</strong>
            </div>
            <div v-if="product.seller">
              <span>👤 מוכר</span>
              <strong>{{ product.seller.nameSeller }}</strong>
            </div>
            <div v-if="product.phoneNumber">
              <span>📞 טלפון</span>
              <strong>{{ product.phoneNumber }}</strong>
            </div>
          </div>

          <div class="actions">
            <button class="btn btn-primary btn-lg" @click="callSeller">
              📞 צור קשר עם המוכר
            </button>
            <RouterLink to="/products" class="btn btn-outline btn-lg">
              ← חזרה למודעות
            </RouterLink>
          </div>
        </div>
      </div>

      <div v-if="product.description" class="description card">
        <h2>תיאור המוצר</h2>
        <p>{{ product.description }}</p>
      </div>

      <div class="security-note card">
        <span>🔒</span>
        <div>
          <strong>טיפים לבטיחות</strong>
          <p>פגשו את המוכר במקום ציבורי, בדקו את המוצר לפני התשלום, ואל תשלמו מראש ללא אמון.</p>
        </div>
      </div>
    </template>

    <div v-else class="empty card">
      <h2>מוצר לא נמצא</h2>
      <RouterLink to="/products" class="btn btn-primary">חזרה למודעות</RouterLink>
    </div>
  </div>
</template>

<style scoped>
.breadcrumb {
  display: flex;
  gap: 0.5rem;
  font-size: 0.875rem;
  color: var(--text-muted);
  margin-bottom: 1.5rem;
  flex-wrap: wrap;
}

.breadcrumb a:hover {
  color: var(--primary);
}

.detail-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 2rem;
  margin-bottom: 2rem;
}

.gallery img {
  width: 100%;
  aspect-ratio: 4/3;
  object-fit: cover;
  border-radius: var(--radius);
}

.tag {
  display: inline-block;
  background: var(--primary-light);
  color: var(--primary);
  padding: 0.3rem 0.85rem;
  border-radius: 999px;
  font-size: 0.8rem;
  font-weight: 600;
  margin-bottom: 0.75rem;
}

.info h1 {
  font-size: clamp(1.5rem, 3vw, 2rem);
  font-weight: 800;
  margin-bottom: 1rem;
  line-height: 1.3;
}

.price-row {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.price {
  font-size: 2rem;
  font-weight: 800;
  color: var(--primary);
}

.status-badge {
  background: #fef3c7;
  color: var(--accent-dark);
  padding: 0.3rem 0.85rem;
  border-radius: 999px;
  font-size: 0.85rem;
  font-weight: 600;
}

.details-list {
  padding: 1.25rem;
  margin-bottom: 1.5rem;
}

.details-list div {
  display: flex;
  justify-content: space-between;
  padding: 0.75rem 0;
  border-bottom: 1px solid var(--border);
}

.details-list div:last-child {
  border-bottom: none;
}

.details-list span {
  color: var(--text-muted);
}

.actions {
  display: flex;
  gap: 1rem;
  flex-wrap: wrap;
}

.btn-lg {
  padding: 1rem 2rem;
  font-size: 1rem;
}

.description {
  padding: 2rem;
  margin-bottom: 1.5rem;
}

.description h2 {
  font-size: 1.25rem;
  margin-bottom: 1rem;
}

.description p {
  color: var(--text-muted);
  line-height: 1.8;
  white-space: pre-line;
}

.security-note {
  display: flex;
  gap: 1rem;
  padding: 1.25rem 1.5rem;
  background: #f0fdf4;
  border: 1px solid #bbf7d0;
}

.security-note span {
  font-size: 1.5rem;
}

.security-note strong {
  display: block;
  margin-bottom: 0.25rem;
}

.security-note p {
  font-size: 0.9rem;
  color: var(--text-muted);
}

.empty {
  text-align: center;
  padding: 4rem;
}

@media (max-width: 768px) {
  .detail-grid {
    grid-template-columns: 1fr;
  }
}
</style>
