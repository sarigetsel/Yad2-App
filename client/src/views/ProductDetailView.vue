<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, RouterLink } from 'vue-router'
import { productsApi } from '../api/products'
import LoadingSpinner from '../components/LoadingSpinner.vue'
import { getProductImage, formatPrice, formatCategory, formatStatus } from '../utils/images'
import { MOCK_PRODUCTS, mergeWithImages } from '../data/mockProducts'

const route = useRoute()
const product = ref(null)
const loading = ref(true)
const imgSrc = ref('')

onMounted(async () => {
  try {
    const { data } = await productsApi.getById(route.params.id)
    const merged = mergeWithImages([data])[0]
    product.value = merged
    imgSrc.value = getProductImage(merged)
  } catch {
    const found = MOCK_PRODUCTS.find((p) => p.id === Number(route.params.id))
    product.value = found || null
    if (found) imgSrc.value = getProductImage(found)
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
  <div class="detail-page container">
    <LoadingSpinner v-if="loading" size="lg" />

    <template v-else-if="product">
      <nav class="breadcrumb">
        <RouterLink to="/">דף הבית</RouterLink>
        <span class="sep">/</span>
        <RouterLink to="/products">מוצרים</RouterLink>
        <span class="sep">/</span>
        <span>{{ product.nameProduct }}</span>
      </nav>

      <div class="detail-layout">
        <div class="gallery">
          <img :src="imgSrc" :alt="product.nameProduct" @error="onImgError" />
        </div>

        <aside class="sidebar">
          <p class="price">{{ formatPrice(product.price) }}</p>
          <h1>{{ product.nameProduct }}</h1>

          <div class="chips">
            <span v-if="product.status">{{ formatStatus(product.status) }}</span>
            <span v-if="product.categor">{{ formatCategory(product.categor.categoryName) }}</span>
            <span v-if="product.area">{{ formatCategory(product.area.areaName) }}</span>
          </div>

          <div class="seller-box">
            <p class="seller-label">מוכר/ת</p>
            <p class="seller-name">{{ product.seller?.nameSeller || 'מוכר פרטי' }}</p>
            <p v-if="product.phoneNumber" class="seller-phone">{{ product.phoneNumber }}</p>
          </div>

          <button class="btn-contact" @click="callSeller">צור קשר</button>
          <RouterLink to="/products" class="back-link">← חזרה לכל המודעות</RouterLink>
        </aside>
      </div>

      <div v-if="product.description" class="desc-block">
        <h2>תיאור</h2>
        <p>{{ product.description }}</p>
      </div>

      <div class="safety-tip">
        <strong>טיפ בטיחות:</strong>
        פגשו את המוכר במקום ציבורי, בדקו את המוצר לפני התשלום.
      </div>
    </template>

    <div v-else class="empty">
      <p>המודעה לא נמצאה</p>
      <RouterLink to="/products" class="btn btn-primary">חזרה למודעות</RouterLink>
    </div>
  </div>
</template>

<style scoped>
.detail-page {
  padding: 1rem 0 3rem;
}

.detail-layout {
  display: grid;
  grid-template-columns: 1fr 340px;
  gap: 2rem;
  margin-bottom: 2rem;
}

.gallery {
  background: #f5f5f5;
  border-radius: var(--radius);
  overflow: hidden;
}

.gallery img {
  width: 100%;
  aspect-ratio: 4/3;
  object-fit: cover;
}

.sidebar {
  border: 1px solid var(--border);
  border-radius: var(--radius);
  padding: 1.5rem;
  height: fit-content;
  position: sticky;
  top: calc(var(--header-h) + 120px);
}

.price {
  font-size: 2rem;
  font-weight: 700;
  color: var(--text-dark);
  margin-bottom: 0.5rem;
}

.sidebar h1 {
  font-size: 1.25rem;
  font-weight: 600;
  line-height: 1.4;
  margin-bottom: 1rem;
  color: var(--text);
}

.chips {
  display: flex;
  flex-wrap: wrap;
  gap: 0.4rem;
  margin-bottom: 1.25rem;
}

.chips span {
  background: #f5f5f5;
  padding: 0.25rem 0.65rem;
  border-radius: var(--radius-pill);
  font-size: 0.78rem;
  color: var(--text-muted);
}

.seller-box {
  border-top: 1px solid var(--border-light);
  padding-top: 1rem;
  margin-bottom: 1.25rem;
}

.seller-label {
  font-size: 0.78rem;
  color: var(--text-muted);
  margin-bottom: 0.2rem;
}

.seller-name {
  font-weight: 600;
  font-size: 1rem;
}

.seller-phone {
  font-size: 0.9rem;
  color: var(--text-muted);
  margin-top: 0.2rem;
}

.btn-contact {
  width: 100%;
  background: var(--yad2-orange);
  color: white;
  border: none;
  padding: 0.85rem;
  border-radius: var(--radius-pill);
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  margin-bottom: 0.75rem;
}

.btn-contact:hover {
  background: var(--yad2-orange-hover);
}

.back-link {
  display: block;
  text-align: center;
  font-size: 0.875rem;
  color: var(--text-muted);
}

.back-link:hover {
  color: var(--yad2-orange);
}

.desc-block {
  border-top: 1px solid var(--border-light);
  padding-top: 1.5rem;
  margin-bottom: 1.5rem;
}

.desc-block h2 {
  font-size: 1.1rem;
  margin-bottom: 0.75rem;
}

.desc-block p {
  color: var(--text-muted);
  line-height: 1.7;
}

.safety-tip {
  background: #fff8f0;
  border: 1px solid #ffe0c2;
  border-radius: var(--radius);
  padding: 1rem 1.25rem;
  font-size: 0.875rem;
  color: var(--text-muted);
}

.empty {
  text-align: center;
  padding: 4rem;
}

@media (max-width: 768px) {
  .detail-layout {
    grid-template-columns: 1fr;
  }

  .sidebar {
    position: static;
  }
}
</style>
