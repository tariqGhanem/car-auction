package com.auction.billing.Services;

public interface NotificationService {
    void sendNotification(String to, String subject, String body);
}
