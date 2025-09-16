package com.auction.inventory.Models;

import com.auction.inventory.Models.Domain.TitleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {
    private Long id;
    private String vin;
    private String make;
    private String model;
    private Integer year;
    private Integer mileage;
    private TitleStatus titleStatus;
    private BigDecimal startingPrice;
    private BigDecimal buyNowPrice;
    private Integer conditionGrade;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
