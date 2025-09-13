package com.auction.inventory.Mapper;

import com.auction.common.dto.TitleStatus;
import com.auction.common.dto.VehicleDTO;
import com.auction.inventory.Models.Vehicle;

public class VehicleMapper {

    public static VehicleDTO vehicleToVehicleDTO(Vehicle vehicle) {
        return new VehicleDTO(vehicle.getId(), vehicle.getVin(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getMileage()
        ,TitleStatus.valueOf(vehicle.getTitleStatus().name()),vehicle.getStartingPrice(),vehicle.getBuyNowPrice(),vehicle.getConditionGrade()
                ,vehicle.getDescription());
    }
}
