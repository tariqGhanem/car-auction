package com.auction.bidding.Models.DTO;

public record PlaceBidResponse(boolean accepted, String reason, long currentPriceCents) {}
