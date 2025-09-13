package com.auction.auction.Services;

import com.auction.auction.Models.DTO.AuctionDtos;

import java.time.Duration;

public class AuctionServiceValidator {

    public static void isTheAuctionReqValid(AuctionDtos.CreateAuctionRequest req) {
        //check The auction dates
        if (req.endsAt().isBefore(req.startsAt())) {
            throw new IllegalArgumentException("endsAt must be after startsAt");
        }
        //check the auction timeframe
        var hours = Duration.between(req.startsAt(), req.endsAt()).toHours();
        if (hours < 1 || hours > 14*24) {
            throw new IllegalArgumentException("auction duration must be 1h..14d");
        }
        //check the buyNow price
        if (req.buyNowPrice() != null && req.winning_price() != null &&
                req.buyNowPrice().compareTo(req.winning_price()) <= 0) {
            throw new IllegalArgumentException("buyNowPrice ≤ winning_price");
        }
        //check the min INC
        if (req.minBidIncrement().signum() <= 0) {
            throw new IllegalArgumentException("minBidIncrement must be > 0");
        }
    }
}
