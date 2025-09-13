package com.auction.auction.Controller;

import com.auction.auction.Models.Auction;
import com.auction.auction.Models.DTO.AuctionDtos;
import com.auction.auction.Repo.AuctionRepo;
import com.auction.auction.Services.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auction")
public class AuctionController {

    private final AuctionService auctionService;

    //GetAll auctions
    @GetMapping("getAllAuctions")
    public List<Auction> getAllAuctions() {
        return auctionService.getAllAuctions();
    }
    //Get AuctionById
    @GetMapping("getAuctionById")
    public Auction getAuctionById(@RequestParam(name = "id") Long id) {
        return auctionService.getAuctionById(id);
    }
    //Create Auction
    @PostMapping("createAuction")
    public Auction createAuction(@RequestBody AuctionDtos.CreateAuctionRequest auction , @AuthenticationPrincipal org.springframework.security.oauth2.jwt.Jwt jwt) {
        return auctionService.createDraft(auction , jwt.getTokenValue());
    }
    //Filter Auctions
    @GetMapping("filterAuction")
    public Page<Auction> filterAuction(
            @RequestParam(name = "dateFrom" , required = false) Instant dateFrom ,
            @RequestParam(required = false , name = "dateTo") Instant dateTo ,
            @RequestParam(name = "maxBuyNow" , required = false) BigDecimal maxBuyNow,
            @RequestParam(name = "maxBid"  , required = false) BigDecimal maxBid) {

        return auctionService.filterAuction(dateFrom , dateTo , maxBuyNow , maxBid);
    }
    //UpdateAuction
    @PostMapping("updateAuction")
    public Auction updateAuction(
            @RequestParam(name = "auctionId")Long auctionId,
            @RequestParam(name = "winnerId") Long winnerId,
            @RequestParam(name = "currentPrice")BigDecimal currentPrice) {

        return auctionService.updateAuction(auctionId, winnerId, currentPrice);
    }

    @GetMapping("test")
    public boolean test (){
        return true;
    }
}
