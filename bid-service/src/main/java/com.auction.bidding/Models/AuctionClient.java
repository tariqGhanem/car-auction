package com.auction.bidding.Models;

import java.time.Duration;

public interface AuctionClient {
    AuctionSnapshot get(Long auctionId);
    void updatePrice(Long auctionId, long newPriceCents, Long winnerId, long expectedVersion);
    void extendEndTime(Long auctionId, Duration by, long expectedVersion);
}

