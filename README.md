# Hand2 - לוח יד 2

פlatפורמת מודעות יד שנייה עם Spring Boot (Backend) ו-Vue 3 (Frontend).

## הרצה

### Backend (פורט 8585)
```bash
./mvnw spring-boot:run
```

### Frontend (פורט 5173)
```bash
cd client
npm install
npm run dev
```

## חשבונות דמו
| אימייל | סיסמה |
|--------|--------|
| dani@hand2.co.il | 1234 |
| michal@hand2.co.il | 1234 |
| yossi@hand2.co.il | 1234 |

## מבנה
- `src/` - Spring Boot API + JWT Security
- `client/` - Vue 3 + Vite + Pinia + Vue Router
- `images/` - תמונות מוצרים

## API
- `GET /api/product/getAll` - כל המוצרים
- `GET /api/images/{filename}` - תמונת מוצר
- `POST /api/auth/login` - התחברות
- `POST /api/auth/register` - הרשמה
- `POST /api/product/addDto` - פרסום מודעה (מאומת)
