package com.auction.billing.Modules;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidPlacedEvent {

    String auctionId;
    String bidderId;
    long amountCents;
    Instant createdAt;
    String bidderEmail;

}

