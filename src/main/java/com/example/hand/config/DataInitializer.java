package com.example.hand.config;

import com.example.hand.Repository.areaRepository;
import com.example.hand.Repository.categoryRepository;
import com.example.hand.Repository.productRepository;
import com.example.hand.Repository.sellerRepository;
import com.example.hand.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final categoryRepository categoryRepository;
    private final areaRepository areaRepository;
    private final sellerRepository sellerRepository;
    private final productRepository productRepository;

    public DataInitializer(categoryRepository categoryRepository,
                           areaRepository areaRepository,
                           sellerRepository sellerRepository,
                           productRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.areaRepository = areaRepository;
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() > 0) return;

        category catMusic = saveCategory(eCategor.כלי_נגינה);
        category catFurniture = saveCategory(eCategor.רהיטים);
        category catElectronics = saveCategory(eCategor.אלקטרוניקה);
        category catAccessories = saveCategory(eCategor.בלי_חיים);
        category catMisc = saveCategory(eCategor.שונות);

        area areaCenter = saveArea(eArea.מרכז);
        area areaNorth = saveArea(eArea.צפון);
        area areaSouth = saveArea(eArea.דרום);
        area areaDan = saveArea(eArea.גוש_דן);
        area areaJerusalem = saveArea(eArea.ירושלים);

        seller s1 = saveSeller("דני כהן", "050-1234567", "dani@hand2.co.il", "1234");
        seller s2 = saveSeller("מיכל לוי", "052-9876543", "michal@hand2.co.il", "1234");
        seller s3 = saveSeller("יוסי אברהם", "054-5551234", "yossi@hand2.co.il", "1234");

        saveProduct("גיטרה אקוסטית Yamaha F310", catMusic, s1, areaCenter,
                "050-1234567", 850, eStatus.כמו_חדש,
                "https://images.unsplash.com/photo-1516925856310-b593457a71a5?w=600&h=450&fit=crop&q=80",
                "גיטרה אקוסטית מצוינת ללימודים והופעות. כוללת כיסוי. נקנתה לפני שנה, מעט שימוש.");

        saveProduct("פסנתר חשמלי Casio PX-S1100", catMusic, s2, areaDan,
                "052-9876543", 2200, eStatus.חדש_באריזה,
                "https://images.unsplash.com/photo-1520523839897-bd0b52f945a0?w=600&h=450&fit=crop&q=80",
                "פסנתר חשמלי דק ואלגנטי עם 88 קלידים. חדש באריזה, לא נפתח.");

        saveProduct("מיקרופון Shure SM58", catMusic, s3, areaNorth,
                "054-5551234", 480, eStatus.כמו_חדש,
                "https://images.unsplash.com/photo-1598488035139-bdbb2231ce04?w=600&h=450&fit=crop&q=80",
                "מיקרופון דינמי מקצועי, סטנדרט בתעשייה. מושלם להופעות ואולפן.");

        saveProduct("ספה 3 מושבים מעור", catFurniture, s1, areaCenter,
                "050-1234567", 1350, eStatus.ישן,
                "https://images.unsplash.com/photo-1555041469-a586c0361544?w=600&h=450&fit=crop&q=80",
                "ספה נוחה ומרווחת בצבע חום, מצב טוב. איסוף עצמי.");

        saveProduct("שולחן אוכל + 6 כיסאות", catFurniture, s2, areaJerusalem,
                "052-9876543", 980, eStatus.כמו_חדש,
                "https://images.unsplash.com/photo-1617806118773-1e0e938d9a24?w=600&h=450&fit=crop&q=80",
                "שולחן אוכל מעץ מלא עם 6 כיסאות מרופדים. נמכר בגלל מעבר דירה.");

        saveProduct("ארון הזזה 3 דלתות", catFurniture, s3, areaSouth,
                "054-5551234", 750, eStatus.ישן,
                "https://images.unsplash.com/photo-1616598336755-72e7888753f8?w=600&h=450&fit=crop&q=80",
                "ארון הזזה גדול, 3 דלתות מראה. מידות: 240x60x220 ס\"מ.");

        saveProduct("סמארטפון Samsung Galaxy S23", catElectronics, s1, areaDan,
                "050-1234567", 2450, eStatus.כמו_חדש,
                "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=600&h=450&fit=crop&q=80",
                "סמארטפון בצבע כחול, סוללה 92%. כולל קופסה מקורית ומטען.");

        saveProduct("MacBook Air M1 256GB", catElectronics, s2, areaCenter,
                "052-9876543", 4200, eStatus.כמו_חדש,
                "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=600&h=450&fit=crop&q=80",
                "מקבוק אייר M1, 8GB RAM. מצב מעולה, ללא שריטות. כולל מטען מקורי.");

        saveProduct("מקרר Samsung דיגיטלי", catElectronics, s3, areaNorth,
                "054-5551234", 1650, eStatus.ישן,
                "https://images.unsplash.com/photo-1571175443880-49a1e1e2d8a2?w=600&h=450&fit=crop&q=80",
                "מקרר 4 דלתות, No Frost, צבע נירוסטה. עובד מצוין.");

        saveProduct("PlayStation 5 + 2 בקרים", catElectronics, s1, areaSouth,
                "050-1234567", 2100, eStatus.כמו_חדש,
                "https://images.unsplash.com/photo-1606813907291-d86efa9b94e4?w=600&h=450&fit=crop&q=80",
                "PS5 דיסק edition, כולל 2 בקרי DualSense ו-3 משחקים.");

        saveProduct("תיק עור איטלקי", catAccessories, s2, areaJerusalem,
                "052-9876543", 320, eStatus.חדש,
                "https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=600&h=450&fit=crop&q=80",
                "תיק עור איכותי, מתאים לגברים ונשים. חדש עם תווית.");

        saveProduct("שעון Garmin Forerunner 255", catAccessories, s3, areaDan,
                "054-5551234", 890, eStatus.כמו_חדש,
                "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=600&h=450&fit=crop&q=80",
                "שעון ספורט חכם עם GPS, מד דופק וניטור שינה. מצב מצוין.");

        saveProduct("אופניים חשמליים", catMisc, s1, areaCenter,
                "050-1234567", 3500, eStatus.כמו_חדש,
                "https://images.unsplash.com/photo-1571068316344-75bc76f77890?w=600&h=450&fit=crop&q=80",
                "אופניים חשמליים עם טווח 60 ק\"מ. סוללה חדשה, כולל מנעול.");

        saveProduct("מכונת קפה DeLonghi", catMisc, s2, areaNorth,
                "052-9876543", 650, eStatus.ישן,
                "https://images.unsplash.com/photo-1517668808822-9ebb02f2a0e6?w=600&h=450&fit=crop&q=80",
                "מכונת קפה אוטומטית, מכינה espresso ו-cappuccino.");

        saveProduct("סט סירים נירוסטה 12 חלקים", catMisc, s3, areaSouth,
                "054-5551234", 280, eStatus.חדש_באריזה,
                "https://images.unsplash.com/photo-1584990347499-66a5b6c9112a?w=600&h=450&fit=crop&q=80",
                "סט סירים ומחבתות מנירוסטה איכותית, חדש באריזה.");
    }

    private category saveCategory(eCategor name) {
        category c = new category();
        c.setCategoryName(name);
        return categoryRepository.save(c);
    }

    private area saveArea(eArea name) {
        area a = new area();
        a.setAreaName(name);
        return areaRepository.save(a);
    }

    private seller saveSeller(String name, String phone, String email, String password) {
        seller s = new seller();
        s.setNameSeller(name);
        s.setPhone(phone);
        s.setEmail(email);
        s.setPassword(password);
        return sellerRepository.save(s);
    }

    private void saveProduct(String name, category cat, seller sel, area ar,
                             String phone, double price, eStatus status,
                             String picture, String description) {
        Product p = new Product();
        p.setNameProduct(name);
        p.setCategor(cat);
        p.setSeller(sel);
        p.setArea(ar);
        p.setPhoneNumber(phone);
        p.setPrice(price);
        p.setStatus(status);
        p.setPicture(picture);
        p.setDescription(description);
        productRepository.save(p);
    }
}
