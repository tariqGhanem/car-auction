package com.auction.auction.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "auctions",
        indexes = {
                @Index(name = "idx_auction_state", columnList = "state, starts_at, ends_at"),
                @Index(name = "idx_auction_vehicle", columnList = "vehicle_id")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auction {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    @Column(name = "seller_id", nullable = false)
    private Long sellerId;

    @Column(name = "starts_at", nullable = false)
    private Instant startsAt;

    @Column(name = "ends_at", nullable = false)
    private Instant endsAt;

    @Column(name = "winning_price")
    private BigDecimal winning_price;

    @Column(name = "buy_now_price")
    private BigDecimal buyNowPrice;

    @Column(name = "start_price", nullable = false)
    private BigDecimal startPrice;

    @Column(name = "min_bid_increment", nullable = false)
    private BigDecimal minBidIncrement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private AuctionState state;

    @Column(name = "winner_bid_id")
    private Long winnerBidId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        var now = Instant.now();
        createdAt = now;
        updatedAt = now;
        if (state == null) state = AuctionState.DRAFT;
    }
    @PreUpdate void onUpdate() { updatedAt = Instant.now(); }
}
