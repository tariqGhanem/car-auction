package com.auction.billing.Services;

import com.auction.billing.Modules.BidPlacedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BidEventsConsumer {

    private final MailService mailService;

    @KafkaListener(topics = "bid-placed", groupId = "bids-consumer")
    public void onBidPlaced(BidPlacedEvent event) {

        System.out.println("RES -> " + event.toString());
        //Send Notification to the highest bidder to tell him he was outbid
        mailService.sendNotification(event.getBidderEmail()
                ,"Car Auction APP"
                , "Your bid on Auction " + event.getAuctionId() +" was out bid the current bid is : " + event.getAmountCents());
    }
}

