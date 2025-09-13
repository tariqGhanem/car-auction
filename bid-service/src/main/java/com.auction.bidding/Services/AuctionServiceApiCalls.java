package com.auction.bidding.Services;

import com.auction.bidding.Models.AuctionSnapshot;
import com.auction.common.dto.VehicleDTO;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuctionServiceApiCalls {

    private final DiscoveryClient discoveryClient;
    private final WebClient webClient;


    private URI vehicleBaseUri() {
        var instances = discoveryClient.getInstances("auction-service");
        if (instances.isEmpty()) throw new IllegalStateException("auction-service not found in Eureka");
        return instances.getFirst().getUri(); // simplest: pick the first instance
    }
    public AuctionSnapshot getAuctionById(Long id , String token) {

        var base = vehicleBaseUri();
        return webClient.get()
                .uri(base.resolve("/api/v1/auctions/getAuctionById?id=" + id))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(AuctionSnapshot.class)
                .block();
    }

    public AuctionSnapshot updateAuctionById(Long auctionId , BigDecimal currentPrice,Long winnerId,String token) {
        //update winnerId , currentPrice , endsAt --> byAuctionId
        var base = vehicleBaseUri();
        System.out.println("base: " + base);
        return webClient.post()
                .uri(base.resolve("/api/v1/auction/updateAuction?" +
                        "auctionId="+auctionId+"&currentPrice="+currentPrice+"&winnerId="+winnerId))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(AuctionSnapshot.class)
                .block();
    }
}
