package com.auction.bidding.Models;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class BidPlacedEvent {

    String auctionId;
    Long bidderId;
    BigDecimal amountCents;
    Instant createdAt;
    String bidderEmail;

    public BidPlacedEvent(String bidderEmail , Bid bid) {
        this.bidderEmail = bidderEmail;
        this.bidderId = bid.getBidderId();
        this.createdAt = bid.getCreatedAt();
        this.amountCents = bid.getAmountCents();

    }
}
