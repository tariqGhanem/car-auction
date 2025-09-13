package com.auction.billing.Controller;


import com.auction.billing.Services.MailService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/notification")
@AllArgsConstructor
@RestController
public class NotificationController {

    @GetMapping("/test")
    public boolean test(){
        return true;
    }
}
