package com.auction.bidding.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AuctionSnapshot{
    Long id;
    BigDecimal startPrice;
    BigDecimal minBidIncrement;
    BigDecimal currentWinnerId;
    BigDecimal reservePrice;
    Instant endsAt;

}

