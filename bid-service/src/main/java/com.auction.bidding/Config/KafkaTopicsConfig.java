package com.auction.bidding.Config;


import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Map;

@Configuration
public class KafkaTopicsConfig {
    @Bean
    KafkaAdmin kafkaAdmin(@Value("${spring.kafka.bootstrap-servers}") String bootstrap) {
        return new KafkaAdmin(Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap));
    }
    @Bean
    NewTopic bidPlacedTopic() {
        return TopicBuilder.name("bid-placed").partitions(3).replicas(1).build();
    }
}