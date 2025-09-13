package com.auction.bidding.Services;

import com.auction.bidding.Models.AuctionSnapshot;
import com.auction.bidding.Models.Bid;
import com.auction.bidding.Repo.BidRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@AllArgsConstructor
public class BidAppService {

    private final BidRepository bidRepository;
    private final AuctionServiceApiCalls auctionServiceApiCalls;
    private final BidEventsProducer bidEventsProducer;

    @Transactional
    public Bid bidOnAuction(Bid bid , String token , String bidderEmail){
        auctionServiceApiCalls.updateAuctionById(bid.getAuctionId(), bid.getAmountCents(), bid.getBidderId(), token);
        bidEventsProducer.publish(bid , bidderEmail);
        return bidRepository.save(bid);
    }


    public boolean test() {
        return true;
    }
}

