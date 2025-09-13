package com.auction.auction.Services;

import com.auction.common.dto.VehicleDTO;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
@AllArgsConstructor
public class VehicleApiClient {

    private final DiscoveryClient discoveryClient;
    private final WebClient webClient;


    private URI vehicleBaseUri() {
        var instances = discoveryClient.getInstances("inventory-service");
        if (instances.isEmpty()) throw new IllegalStateException("inventory-service not found in Eureka");
        return instances.getFirst().getUri(); // simplest: pick the first instance
    }

    public Mono<VehicleDTO> getVehicles(Long id, String token) {
        var base = vehicleBaseUri();
        return webClient.get()
                .uri(base.resolve("/api/v1/vehicles/getVehicleById?id=" + id))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(VehicleDTO.class);
    }

}

