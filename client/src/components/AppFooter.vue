<script setup>
import { RouterLink } from 'vue-router'
</script>

<template>
  <footer class="site-footer">
    <div class="container footer-inner">
      <div class="footer-links">
        <RouterLink to="/products">מוצרים</RouterLink>
        <RouterLink to="/categories">קטגוריות</RouterLink>
        <RouterLink to="/sell">פרסום מודעה</RouterLink>
        <RouterLink to="/about">אודות</RouterLink>
      </div>
      <p class="copy">© {{ new Date().getFullYear() }} Hand2 · לוח יד 2</p>
    </div>
  </footer>
</template>

<style scoped>
.site-footer {
  border-top: 1px solid var(--border-light);
  background: #fafafa;
  padding: 1.5rem 0;
  margin-top: auto;
}

.footer-inner {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  gap: 1rem;
}

.footer-links {
  display: flex;
  gap: 1.5rem;
  flex-wrap: wrap;
}

.footer-links a {
  font-size: 0.875rem;
  color: var(--text-muted);
}

.footer-links a:hover {
  color: var(--yad2-orange);
}

.copy {
  font-size: 0.8rem;
  color: #aaa;
}
</style>
