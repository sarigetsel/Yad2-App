<script setup>
import { RouterLink } from 'vue-router'
</script>

<template>
  <footer class="footer">
    <div class="container footer-grid">
      <div class="footer-brand">
        <div class="logo-row">
          <span class="logo-icon">H2</span>
          <span>Hand<strong>2</strong></span>
        </div>
        <p>לוח יד 2 המוביל בישראל. קנו ומכרו בקלות, בביטחון ובמהירות.</p>
      </div>

      <div>
        <h4>ניווט</h4>
        <ul>
          <li><RouterLink to="/products">כל המודעות</RouterLink></li>
          <li><RouterLink to="/categories">קטגוריות</RouterLink></li>
          <li><RouterLink to="/sell">פרסום מודעה</RouterLink></li>
          <li><RouterLink to="/about">אודות</RouterLink></li>
        </ul>
      </div>

      <div>
        <h4>קטגוריות</h4>
        <ul>
          <li><RouterLink to="/products?cat=רהיטים">רהיטים</RouterLink></li>
          <li><RouterLink to="/products?cat=כלי_נגינה">כלי נגינה</RouterLink></li>
          <li><RouterLink to="/products?cat=אלקטרוניקה">אלקטרוניקה</RouterLink></li>
          <li><RouterLink to="/products?cat=שונות">שונות</RouterLink></li>
        </ul>
      </div>

      <div>
        <h4>יצירת קשר</h4>
        <ul class="contact">
          <li>📧 support@hand2.co.il</li>
          <li>📞 03-1234567</li>
          <li>📍 תל אביב, ישראל</li>
        </ul>
      </div>
    </div>

    <div class="footer-bottom container">
      <p>© {{ new Date().getFullYear() }} Hand2. כל הזכויות שמורות.</p>
      <p class="secure">🔒 מוגן ב-JWT | SSL Ready</p>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background: var(--text);
  color: #cbd5e1;
  padding: 3rem 0 0;
  margin-top: auto;
}

.footer-grid {
  display: grid;
  grid-template-columns: 2fr 1fr 1fr 1fr;
  gap: 2rem;
  padding-bottom: 2rem;
}

.footer h4 {
  color: white;
  margin-bottom: 1rem;
  font-size: 1rem;
}

.footer ul {
  list-style: none;
}

.footer li {
  margin-bottom: 0.5rem;
}

.footer a:hover {
  color: white;
}

.logo-row {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 1.25rem;
  font-weight: 700;
  color: white;
  margin-bottom: 0.75rem;
}

.logo-icon {
  background: linear-gradient(135deg, var(--primary), #7c3aed);
  color: white;
  width: 36px;
  height: 36px;
  border-radius: 8px;
  display: grid;
  place-items: center;
  font-size: 0.75rem;
  font-weight: 800;
}

.logo-row strong {
  color: #60a5fa;
}

.footer-brand p {
  font-size: 0.9rem;
  line-height: 1.7;
  max-width: 280px;
}

.contact li {
  font-size: 0.9rem;
}

.footer-bottom {
  border-top: 1px solid #334155;
  padding: 1.25rem 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 0.85rem;
  flex-wrap: wrap;
  gap: 0.5rem;
}

.secure {
  color: var(--success);
}

@media (max-width: 768px) {
  .footer-grid {
    grid-template-columns: 1fr 1fr;
  }
}

@media (max-width: 480px) {
  .footer-grid {
    grid-template-columns: 1fr;
  }
}
</style>
