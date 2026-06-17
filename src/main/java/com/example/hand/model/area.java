package com.example.hand.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class area {
    @Id
    @GeneratedValue
    private int ID;
    @Enumerated(EnumType.STRING)
    private eArea areaName;

}
