package com.example.hand.Dto;

import com.example.hand.model.eArea;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class areaDto {
    private int ID;
    private eArea areaName;

}
