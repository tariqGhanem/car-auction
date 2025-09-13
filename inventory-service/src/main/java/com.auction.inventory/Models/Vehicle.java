package com.auction.inventory.Models;

import com.auction.inventory.Models.Domain.TitleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "vehicles",
        indexes = {
                @Index(name = "ux_vehicles_vin", columnList = "vin", unique = true),
                @Index(name = "idx_vehicles_make_model", columnList = "make, model"),
                @Index(name = "idx_vehicles_year", columnList = "year")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 17, unique = true)
    private String vin;

    @Column(nullable = false, length = 50)
    private String make;

    @Column(nullable = false, length = 50)
    private String model;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer mileage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TitleStatus titleStatus; // CLEAN, SALVAGE, REBUILT

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal startingPrice;

    @Column(precision = 10, scale = 2)
    private BigDecimal buyNowPrice;

    @Column(nullable = false)
    private Integer conditionGrade; // 1-5 or 0-5 scale

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
