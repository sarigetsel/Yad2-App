<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { productsApi } from '../api/products'
import { categoriesApi, areasApi } from '../api/catalog'
import { useAuthStore } from '../stores/auth'
import { formatCategory } from '../utils/images'

const auth = useAuthStore()
const router = useRouter()

const categories = ref([])
const areas = ref([])
const loading = ref(false)
const error = ref('')
const success = ref('')
const preview = ref(null)

const form = ref({
  nameProduct: '',
  price: '',
  phoneNumber: '',
  description: '',
  status: 'כמו_חדש',
  categorId: '',
  areaId: '',
})
const file = ref(null)

const statuses = ['חדש', 'חדש_באריזה', 'כמו_חדש', 'ישן']

onMounted(async () => {
  const [catRes, areaRes] = await Promise.all([
    categoriesApi.getAll(),
    areasApi.getAll(),
  ])
  categories.value = catRes.data
  areas.value = areaRes.data
})

function onFileChange(e) {
  const f = e.target.files[0]
  if (!f) return
  if (!f.type.startsWith('image/')) {
    error.value = 'יש להעלות קובץ תמונה בלבד'
    return
  }
  if (f.size > 5 * 1024 * 1024) {
    error.value = 'גודל הקובץ המקסימלי הוא 5MB'
    return
  }
  file.value = f
  preview.value = URL.createObjectURL(f)
  error.value = ''
}

async function submit() {
  error.value = ''
  success.value = ''
  if (!file.value) {
    error.value = 'יש לצרף תמונה'
    return
  }

  const selectedCat = categories.value.find((c) => c.id === Number(form.value.categorId))
  const selectedArea = areas.value.find((a) => (a.id ?? a.ID) === Number(form.value.areaId))

  const productData = {
    nameProduct: form.value.nameProduct,
    price: Number(form.value.price),
    phoneNumber: form.value.phoneNumber,
    description: form.value.description,
    status: form.value.status,
    categor: selectedCat,
    area: selectedArea,
    seller: { id: auth.sellerId },
  }

  const formData = new FormData()
  formData.append('file', file.value)
  formData.append('product', new Blob([JSON.stringify(productData)], { type: 'application/json' }))

  loading.value = true
  try {
    await productsApi.addWithImage(formData)
    success.value = 'המודעה פורסמה בהצלחה!'
    setTimeout(() => router.push('/products'), 1500)
  } catch (e) {
    error.value = e.response?.data?.message || 'שגיאה בפרסום המודעה'
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="page container">
    <h1 class="section-title">פרסום מודעה חדשה</h1>
    <p class="section-subtitle">מלאו את הפרטים ופרסמו את המוצר שלכם</p>

    <form class="sell-form card" @submit.prevent="submit">
      <div class="form-grid">
        <div class="form-main">
          <div class="form-group">
            <label>שם המוצר *</label>
            <input v-model="form.nameProduct" type="text" required placeholder="לדוגמה: גיטרה אקוסטית" />
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>מחיר (₪) *</label>
              <input v-model="form.price" type="number" min="1" required />
            </div>
            <div class="form-group">
              <label>טלפון ליצירת קשר *</label>
              <input v-model="form.phoneNumber" type="tel" required placeholder="050-1234567" />
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>קטגוריה *</label>
              <select v-model="form.categorId" required>
                <option value="" disabled>בחרו קטגוריה</option>
                <option v-for="c in categories" :key="c.id" :value="c.id">
                  {{ formatCategory(c.categoryName) }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label>אזור *</label>
              <select v-model="form.areaId" required>
                <option value="" disabled>בחרו אזור</option>
                <option v-for="a in areas" :key="a.id ?? a.ID" :value="a.id ?? a.ID">
                  {{ formatCategory(a.areaName) }}
                </option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label>מצב המוצר *</label>
            <select v-model="form.status" required>
              <option v-for="s in statuses" :key="s" :value="s">
                {{ formatCategory(s) }}
              </option>
            </select>
          </div>

          <div class="form-group">
            <label>תיאור</label>
            <textarea v-model="form.description" rows="4" placeholder="תארו את המוצר, מצבו, וכל פרט רלוונטי..."></textarea>
          </div>
        </div>

        <div class="form-side">
          <label class="upload-label">
            <div class="upload-area" :class="{ 'has-image': preview }">
              <img v-if="preview" :src="preview" alt="תצוגה מקדימה" />
              <template v-else>
                <span>📷</span>
                <p>לחצו להעלאת תמונה</p>
                <small>JPG, PNG עד 5MB</small>
              </template>
            </div>
            <input type="file" accept="image/*" hidden @change="onFileChange" />
          </label>
        </div>
      </div>

      <p v-if="error" class="error-msg">{{ error }}</p>
      <p v-if="success" class="success-msg">{{ success }}</p>

      <button type="submit" class="btn btn-accent btn-lg" :disabled="loading">
        {{ loading ? 'מפרסם...' : 'פרסם מודעה' }}
      </button>
    </form>
  </div>
</template>

<style scoped>
.sell-form {
  padding: 2rem;
  max-width: 900px;
}

.form-grid {
  display: grid;
  grid-template-columns: 1fr 280px;
  gap: 2rem;
  margin-bottom: 1.5rem;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1rem;
}

.upload-area {
  border: 2px dashed var(--border);
  border-radius: var(--radius);
  aspect-ratio: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  cursor: pointer;
  transition: border-color 0.2s;
  overflow: hidden;
}

.upload-area:hover {
  border-color: var(--primary);
}

.upload-area span {
  font-size: 2.5rem;
}

.upload-area p {
  font-weight: 600;
}

.upload-area small {
  color: var(--text-muted);
}

.upload-area.has-image {
  border-style: solid;
  padding: 0;
}

.upload-area img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.btn-lg {
  padding: 1rem 2.5rem;
}

@media (max-width: 768px) {
  .form-grid {
    grid-template-columns: 1fr;
  }

  .form-row {
    grid-template-columns: 1fr;
  }
}
</style>
