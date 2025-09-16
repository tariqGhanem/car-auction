package com.auction.inventory.Controller;

import com.auction.inventory.Mapper.VehicleMapper;
import com.auction.inventory.Models.Domain.TitleStatus;
import com.auction.inventory.Models.Vehicle;
import com.auction.inventory.Models.VehicleRequest;
import com.auction.inventory.Models.VehicleResponse;
import com.auction.inventory.Services.VehicleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    @PostMapping
    public ResponseEntity<VehicleResponse> createVehicle(@Valid @RequestBody VehicleRequest request) {
        Vehicle saved = vehicleService.createVehicle(VehicleMapper.toEntity(request));
        return ResponseEntity.ok(VehicleMapper.toResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicle(@PathVariable("id") Long id) {
        Vehicle vehicle = vehicleService.getVehicle(id);
        return ResponseEntity.ok(VehicleMapper.toResponse(vehicle));
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        List<VehicleResponse> response = vehicleService.getAllVehicles()
                .stream()
                .map(VehicleMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<Boolean> test() {
        return ResponseEntity.ok(true);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<VehicleResponse>> filterVehicle(
            @RequestParam(name = "page") int page,
            @RequestParam(required = false, name = "make") String make,
            @RequestParam(required = false, name = "model") String model,
            @RequestParam(required = false, name = "yearFrom") Integer yearFrom,
            @RequestParam(required = false, name = "yearTo") Integer yearTo,
            @RequestParam(required = false, name = "maxMiles") Integer maxMiles,
            @RequestParam(required = false, name = "priceFrom") BigDecimal priceFrom,
            @RequestParam(required = false, name = "priceTo") BigDecimal priceTo,
            @RequestParam(required = false, name = "status") TitleStatus status
    ) {
        Page<VehicleResponse> response = vehicleService.filterVehicle(page, make, model, yearFrom, yearTo, maxMiles, priceFrom, priceTo, status)
                .map(VehicleMapper::toResponse);
        return ResponseEntity.ok(response);
    }

}
