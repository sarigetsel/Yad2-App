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
                "050-1234567", 850, eStatus.כמו_חדש, "guitar.jpg",
                "גיטרה אקוסטית מצוינת ללימודים והופעות. כוללת כיסוי ומediator. נקנתה לפני שנה, מעט שימוש.");

        saveProduct("פסנתר חשמלי Casio PX-S1100", catMusic, s2, areaDan,
                "052-9876543", 2200, eStatus.חדש_באריזה, "piano.jpg",
                "פסנתר חשמלי דק ואלגנטי עם 88 קלידים. חדש באריזה, לא נפתח.");

        saveProduct("מיקרופון Shure SM58", catMusic, s3, areaNorth,
                "054-5551234", 480, eStatus.כמו_חדש, "microphone.jpg",
                "מיקרופון דינמי מקצועי, סטנדרט בתעשייה. מושלם להופעות ואולפן.");

        saveProduct("ספה 3 מושבים מעור", catFurniture, s1, areaCenter,
                "050-1234567", 1350, eStatus.ישן, "sofa.jpg",
                "ספה נוחה ומרווחת בצבע חום, מצב טוב. איסוף עצמי מרמת גan.");

        saveProduct("שולחן אוכל + 6 כיסאות", catFurniture, s2, areaJerusalem,
                "052-9876543", 980, eStatus.כמו_חדש, "dining-table.jpg",
                "שולחן אוכל מעץ מלא עם 6 כיסאות מרופדים. נמכר בגלל מעבר דירה.");

        saveProduct("ארון הזזה 3 דלתות", catFurniture, s3, areaSouth,
                "054-5551234", 750, eStatus.ישן, "wardrobe.jpg",
                "ארון הזזה גדול, 3 דלתות מראה. מידות: 240x60x220 ס\"מ.");

        saveProduct("iPhone 13 128GB", catElectronics, s1, areaDan,
                "050-1234567", 2450, eStatus.כמו_חדש, "iphone.jpg",
                "אייפון 13 בצבע כחול, סוללה 92%. כולל קופסה מקורית ומטען.");

        saveProduct("MacBook Air M1 256GB", catElectronics, s2, areaCenter,
                "052-9876543", 4200, eStatus.כמו_חדש, "macbook.jpg",
                "מקבוק אייר M1, 8GB RAM. מצב מעולה, ללא שריטות. כולל מטען מקורי.");

        saveProduct("מקרר Samsung דיגיטלי", catElectronics, s3, areaNorth,
                "054-5551234", 1650, eStatus.ישן, "fridge.jpg",
                "מקרר 4 דלתות, No Frost, צבע נירוסטה. עובד מצוין.");

        saveProduct("PlayStation 5 + 2 בקרים", catElectronics, s1, areaSouth,
                "050-1234567", 2100, eStatus.כמו_חדש, "ps5.jpg",
                "PS5 דיסק edition, כולל 2 בקרי DualSense ו-3 משחקים.");

        saveProduct("תיק עור איטalקי", catAccessories, s2, areaJerusalem,
                "052-9876543", 320, eStatus.חדש, "leather-bag.jpg",
                "תיק עור איטalקי איכותי, מתאים לגברים ונשים. חדש עם תווית.");

        saveProduct("שעון Garmin Forerunner 255", catAccessories, s3, areaDan,
                "054-5551234", 890, eStatus.כמו_חדש, "watch.jpg",
                "שעון ספורt חכם עם GPS, מד דופק וניטור שינה. מצב מצוין.");

        saveProduct("אופניים חשמליים", catMisc, s1, areaCenter,
                "050-1234567", 3500, eStatus.כמו_חדש, "ebike.jpg",
                "אופניים חשמליים עם טווח 60 ק\"מ. סוללה חדשה, כולל מנעול.");

        saveProduct("מכונת קפה DeLonghi", catMisc, s2, areaNorth,
                "052-9876543", 650, eStatus.ישן, "coffee-machine.jpg",
                "מכונת קפה אוטומטית, מכינה espresso ו-cappuccino. ניקוי descaling אחרון.");

        saveProduct("סט סירים נירוסטה 12 חלקים", catMisc, s3, areaSouth,
                "054-5551234", 280, eStatus.חדש_באריזה, "cookware.jpg",
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
