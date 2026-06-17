package com.example.hand.Dto;


import com.example.hand.model.eCategor;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class categoryDto {
    private int id;
    private eCategor categoryName;
}
