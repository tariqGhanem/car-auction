package com.auction.inventory.Mapper;

import com.auction.inventory.Models.Vehicle;
import com.auction.inventory.Models.VehicleRequest;
import com.auction.inventory.Models.VehicleResponse;

public class VehicleMapper {

    public static Vehicle toEntity(VehicleRequest request) {
        return Vehicle.builder()
                .vin(request.getVin())
                .make(request.getMake())
                .model(request.getModel())
                .year(request.getYear())
                .mileage(request.getMileage())
                .titleStatus(request.getTitleStatus())
                .startingPrice(request.getStartingPrice())
                .buyNowPrice(request.getBuyNowPrice())
                .conditionGrade(request.getConditionGrade())
                .description(request.getDescription())
                .build();
    }

    public static VehicleResponse toResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .vin(vehicle.getVin())
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .year(vehicle.getYear())
                .mileage(vehicle.getMileage())
                .titleStatus(vehicle.getTitleStatus())
                .startingPrice(vehicle.getStartingPrice())
                .buyNowPrice(vehicle.getBuyNowPrice())
                .conditionGrade(vehicle.getConditionGrade())
                .description(vehicle.getDescription())
                .createdAt(vehicle.getCreatedAt())
                .updatedAt(vehicle.getUpdatedAt())
                .build();
    }
}
