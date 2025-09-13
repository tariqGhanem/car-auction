package com.auction.auction.Mapper;

import com.auction.auction.Models.Auction;
import com.auction.auction.Models.DTO.AuctionDtos;

public class AuctionMapper {
    public static AuctionDtos.AuctionResponse toResponse(Auction a) {
        return new AuctionDtos.AuctionResponse(
                a.getId().toString(),
                a.getState().name(),
                a.getVehicleId().toString(),
                a.getSellerId().toString(),
                a.getStartsAt().toString(),
                a.getEndsAt().toString(),
                a.getStartPrice(),
                a.getWinning_price(),
                a.getBuyNowPrice(),
                a.getMinBidIncrement(),     // NEW
                a.getCreatedAt().toString(),
                a.getUpdatedAt().toString()
        );
    }
}

