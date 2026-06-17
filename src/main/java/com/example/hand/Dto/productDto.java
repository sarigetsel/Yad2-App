package com.example.hand.Dto;

import com.example.hand.model.area;
import com.example.hand.model.category;
import com.example.hand.model.eStatus;
import com.example.hand.model.seller;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class productDto {
    private int id;
    private String nameProduct;
    private category categor;
    private seller seller;
    private String phoneNumber;
    private area area;
    private double price;
    private eStatus status;
    private String description;
    private String picture;
    private byte[] arrPicture;

}
