package com.auction.auction.Models.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class AuctionDtos {
    public record CreateAuctionRequest(
            @NotNull Long vehicleId,
            @NotNull Long sellerId,
            @NotNull @DecimalMin("0.0") BigDecimal startPrice,
            BigDecimal winning_price,
            BigDecimal buyNowPrice,
            @NotNull Instant startsAt,
            @NotNull Instant endsAt,
            @NotNull @DecimalMin("100.00") BigDecimal minBidIncrement
    ) {}

    public record AuctionResponse(
            String id, String state, String vehicleId, String sellerId,
            String startsAt, String endsAt,
            BigDecimal startPrice, BigDecimal reservePrice, BigDecimal buyNowPrice,
            BigDecimal minBidIncrement,                                  // NEW
            String createdAt, String updatedAt
    ) {}
}
