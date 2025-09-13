package com.auction.bidding.Controller;

import com.auction.bidding.Models.Bid;
import com.auction.bidding.Services.BidAppService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/bid")
public class Controller {

    private final BidAppService bidAppService;

    @PostMapping("/makeABid")
    public Bid makeABid(@RequestBody Bid bid,@AuthenticationPrincipal org.springframework.security.oauth2.jwt.Jwt jwt) {
        return bidAppService.bidOnAuction(bid , jwt.getTokenValue() , jwt.getClaim("email"));
    }

    @GetMapping("/test")
    public boolean test() {
        return true;
    }
}
