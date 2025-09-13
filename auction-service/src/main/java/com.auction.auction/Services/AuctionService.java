package com.auction.auction.Services;

import com.auction.auction.Models.Auction;
import com.auction.auction.Models.AuctionFilterClass;
import com.auction.auction.Models.AuctionState;
import com.auction.auction.Models.DTO.AuctionDtos;
import com.auction.auction.Repo.AuctionRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuctionService {

    private final AuctionRepo auctionRepo;
    private final VehicleApiClient vehicleApiClient;

    @Transactional
    public Auction createDraft(AuctionDtos.CreateAuctionRequest req, String tokenValue) {

        //check the auction attribute if valid
        AuctionServiceValidator.isTheAuctionReqValid(req);

        //check if the vehicle is existed
        vehicleApiClient.getVehicles(req.vehicleId(), tokenValue).block();

        //check if there is another auction for the same vehicle
        boolean exists = getAllAuctions().stream()
                .anyMatch(a -> a.getVehicleId().equals(req.vehicleId())
                        && a.getState() == AuctionState.LIVE);

        if (exists) {
            throw new IllegalStateException("An active auction already exists for vehicle " + req.vehicleId());
        }
        //create the auction in the DB
        var a = Auction.builder()
                .vehicleId(req.vehicleId())
                .sellerId(req.sellerId())
                .startsAt(req.startsAt())
                .endsAt(req.endsAt())
                .winning_price(req.startPrice())
                .buyNowPrice(req.buyNowPrice())
                .startPrice(req.startPrice())
                .minBidIncrement(req.minBidIncrement())
                .state(AuctionState.LIVE)
                .build();

        return auctionRepo.save(a);
    }

    //Auction Filter
    public Page<Auction> filterAuction(Instant dateFrom , Instant dateTo , BigDecimal maxBuyNow , BigDecimal maxBid){

        Specification<Auction> spec = Specification.where(AuctionFilterClass.dateBetween(dateFrom,dateTo))
                .and(AuctionFilterClass.maxBuyNow(maxBuyNow))
                .and(AuctionFilterClass.priceOfTheBid(maxBid));

        Page<Auction> auctionsPage = auctionRepo.findAll(spec , Pageable.ofSize(10));
        auctionsPage.forEach(System.out::println);
        return auctionsPage;
    }

    public List<Auction> getAllAuctions(){
        return auctionRepo.findAll();
    }

    public Auction getAuctionById(Long id) {
        return auctionRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Auction not found"));
    }

    @Transactional
    public Auction updateAuction(Long auctionId , Long winnerId , BigDecimal biddingPrice) {
        //get the auction from the DB
        Auction auction = getAuctionById(auctionId);
        //check if the auction is live
        if (!auction.getState().equals(AuctionState.LIVE))
            throw new IllegalStateException("Auction state is not LIVE");
        //check if the bidding is acceptable
        if (auction.getWinning_price().add(auction.getMinBidIncrement()).compareTo(biddingPrice) >= 0)
            throw new IllegalStateException("bidding must be greater than or equal to minBidIncrement + current bidding price");
        auction.setWinning_price(biddingPrice);
        //anti snipe logic
        var min = Duration.between(Instant.now(),auction.getEndsAt()).toMinutes();
        if (min < 55)
            auction.setEndsAt(Instant.now().plus(Duration.ofMinutes(55)));
        //update the auction
        auction.setWinnerBidId(winnerId);
        return auctionRepo.save(auction);
    }

}
