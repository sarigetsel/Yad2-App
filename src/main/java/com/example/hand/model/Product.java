package com.example.hand.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
;
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;
    private String nameProduct;

    @ManyToOne
    //@JoinColumn(name = "categor")  לבדוק על זה אח"כ וגם לבדוק אם אני שולפת את הsellerDto נגיד אז אם נשךף לי גם הseller אם זה טוב
    private category categor;

    @ManyToOne
    //@JoinColumn(name = "seller")
    private seller seller;
    private String phoneNumber;
    @ManyToOne
    //@JoinColumn(name = "area")
    private area area;
    private double price;
    @Enumerated(EnumType.STRING)
    private eStatus status;
    private String picture;
    private String description;

}
