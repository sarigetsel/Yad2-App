<script setup>
defineProps({
  size: { type: String, default: 'md' },
})
</script>

<template>
  <div class="spinner" :class="size">
    <div class="ring"></div>
    <p v-if="size === 'lg'">טוען...</p>
  </div>
</template>

<style scoped>
.spinner {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 1rem;
  padding: 3rem;
}

.ring {
  border: 3px solid var(--border);
  border-top-color: var(--primary);
  border-radius: 50%;
  animation: spin 0.8s linear infinite;
}

.spinner.md .ring {
  width: 40px;
  height: 40px;
}

.spinner.lg .ring {
  width: 56px;
  height: 56px;
}

.spinner p {
  color: var(--text-muted);
}

@keyframes spin {
  to { transform: rotate(360deg); }
}
</style>
