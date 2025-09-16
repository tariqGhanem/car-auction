package com.auction.inventory.Models;

import com.auction.inventory.Models.Domain.TitleStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {

    @NotBlank
    @Size(min = 11, max = 17) // VIN length constraints
    private String vin;

    @NotBlank
    @Size(max = 50)
    private String make;

    @NotBlank
    @Size(max = 50)
    private String model;

    @NotNull
    @Min(1900)
    @Max(2100)
    private Integer year;

    @NotNull
    @PositiveOrZero
    private Integer mileage;

    @NotNull
    private TitleStatus titleStatus; // CLEAN, SALVAGE, REBUILT

    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal startingPrice;

    @DecimalMin(value = "0.00")
    private BigDecimal buyNowPrice;

    @NotNull
    @Min(0)
    @Max(5)
    private Integer conditionGrade;

    private String description;

}
