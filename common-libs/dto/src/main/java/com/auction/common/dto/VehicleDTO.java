package com.auction.common.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class VehicleDTO {
    private Long id;
    private String vin;
    private String make;
    private String model;
    private int year;
    private int mileage;
    private TitleStatus titleStatus; // CLEAN, SALVAGE, REBUILT
    private BigDecimal startingPrice;
    private BigDecimal buyNowPrice;
    private Integer conditionGrade;
    private String description;

}
