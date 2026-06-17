<script setup>
const features = [
  { icon: '🔒', title: 'אבטחה מתקדמת', desc: 'אימות JWT, הצפנת סיסמאות וגישה מבוקרת ל-API' },
  { icon: '📱', title: 'רספונסיבי מלא', desc: 'חוויה מושלמת בנייד, טאבלט ומחשב' },
  { icon: '⚡', title: 'מהיר וקל', desc: 'פרסום מודעה תוך דקות, חיפוש וסינון מתקדם' },
  { icon: '🗺️', title: 'כל הארץ', desc: 'מודעות מכל האזורים - צפון, דרום, מרכז ועוד' },
  { icon: '🏷️', title: 'קטגוריות מגוונות', desc: 'רהיטים, אלקטרוניקה, כלי נגינה ועוד' },
  { icon: '💬', title: 'יצירת קשר ישירה', desc: 'התקשרו ישירות למוכר ללא מתווכים' },
]

const stats = [
  { num: '15+', label: 'מודעות פעילות' },
  { num: '5', label: 'קטegorיות' },
  { num: '7', label: 'אזורים' },
  { num: '100%', label: 'חינם לפרסום' },
]
</script>

<template>
  <div>
    <section class="about-hero">
      <div class="container">
        <h1>אודות Hand2</h1>
        <p>לוח יד 2 מודרני, בטוח ונוח — הבית החדש שלכם לקנייה ומכירה</p>
      </div>
    </section>

    <section class="container about-content">
      <div class="about-text card">
        <h2>מי אנחנו?</h2>
        <p>
          Hand2 הוא לוח מודעות יד שנייה שנבנה במיוחד עבור הקהילה הישראלית.
          המטרה שלנו פשוטה: לאפשר לכל אחד למכור מוצרים שלא בשימוש ולמצוא
          מציאות במחירים משתלמים — בקלות, במהירות ובביטחון.
        </p>
        <p>
          הפlatפורמה מבוססת על טכנולוגיות מתקדמות — Spring Boot בצד השרת
          ו-Vue.js בצד הלקוח — עם מערכת אימות JWT, ניהול מודעות, העלאת תמונות
          וחיפוש חכם.
        </p>
      </div>

      <div class="stats-row">
        <div v-for="s in stats" :key="s.label" class="stat card">
          <strong>{{ s.num }}</strong>
          <span>{{ s.label }}</span>
        </div>
      </div>

      <h2 class="section-title">למה Hand2?</h2>
      <div class="features-grid">
        <div v-for="f in features" :key="f.title" class="feature card">
          <span class="f-icon">{{ f.icon }}</span>
          <h3>{{ f.title }}</h3>
          <p>{{ f.desc }}</p>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
.about-hero {
  background: linear-gradient(135deg, var(--primary), #7c3aed);
  color: white;
  padding: 4rem 0;
  text-align: center;
}

.about-hero h1 {
  font-size: clamp(2rem, 4vw, 3rem);
  font-weight: 800;
  margin-bottom: 0.75rem;
}

.about-hero p {
  font-size: 1.15rem;
  opacity: 0.9;
}

.about-content {
  padding: 3rem 0 4rem;
}

.about-text {
  padding: 2rem;
  margin-bottom: 2rem;
}

.about-text h2 {
  margin-bottom: 1rem;
}

.about-text p {
  color: var(--text-muted);
  line-height: 1.8;
  margin-bottom: 1rem;
}

.stats-row {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 1rem;
  margin-bottom: 3rem;
}

.stat {
  text-align: center;
  padding: 1.5rem;
}

.stat strong {
  display: block;
  font-size: 2rem;
  color: var(--primary);
}

.stat span {
  font-size: 0.85rem;
  color: var(--text-muted);
}

.features-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 1.5rem;
  margin-top: 1.5rem;
}

.feature {
  padding: 1.75rem;
}

.f-icon {
  font-size: 2rem;
  display: block;
  margin-bottom: 0.75rem;
}

.feature h3 {
  margin-bottom: 0.5rem;
}

.feature p {
  color: var(--text-muted);
  font-size: 0.9rem;
}

@media (max-width: 768px) {
  .stats-row {
    grid-template-columns: repeat(2, 1fr);
  }
}
</style>
