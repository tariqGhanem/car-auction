package com.auction.auction.Models;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.Instant;


public class AuctionFilterClass {


    public static Specification<Auction> dateBetween(Instant dateFrom, Instant dateTo) {
        return (dateFrom == null || dateTo == null) ? null
                : (root, query, cb) -> cb.between(root.get("startsAt") ,dateFrom, dateTo);
    }

    public static Specification<Auction> priceOfTheBid(BigDecimal maxBidPrice) {
        return (maxBidPrice == null) ? null
                : (root, query, cb) -> cb.lessThanOrEqualTo(root.get("winning_price") , maxBidPrice);
    }

    public static Specification<Auction> maxBuyNow(BigDecimal price) {
        return (price == null) ? null
                : (root, query, cb) -> cb.lessThanOrEqualTo(root.get("buyNowPrice"), price);
    }
}
