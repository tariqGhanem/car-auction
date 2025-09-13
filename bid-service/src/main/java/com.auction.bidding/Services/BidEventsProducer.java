package com.auction.bidding.Services;

// BidEventsProducer.java
import com.auction.bidding.Models.Bid;
import com.auction.bidding.Models.BidPlacedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class BidEventsProducer {
    private final KafkaTemplate<String, BidPlacedEvent> kafka;

    public void publish(Bid bid , String bidderEmail) {
        kafka.send("bid-placed", new BidPlacedEvent(bidderEmail, bid));
    }
}
