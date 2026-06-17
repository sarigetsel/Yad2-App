package com.example.hand.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

;
@Getter
@Setter
@Entity
public class category {
    @Id
    @GeneratedValue
    private int id;
    @Enumerated(EnumType.STRING)
    private eCategor categoryName;

    @OneToMany
    private List<Product> productList;



}
