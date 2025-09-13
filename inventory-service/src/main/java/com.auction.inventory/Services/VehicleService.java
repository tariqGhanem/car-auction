package com.auction.inventory.Services;


import com.auction.inventory.Models.Domain.TitleStatus;
import com.auction.inventory.Models.Vehicle;
import com.auction.inventory.Repo.VehicleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
    public Vehicle getVehicle(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));
    }
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Transactional
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.setMake(updatedVehicle.getMake());
                    vehicle.setModel(updatedVehicle.getModel());
                    vehicle.setYear(updatedVehicle.getYear());
                    vehicle.setMileage(updatedVehicle.getMileage());
                    vehicle.setTitleStatus(updatedVehicle.getTitleStatus());
                    vehicle.setStartingPrice(updatedVehicle.getStartingPrice());
                    vehicle.setBuyNowPrice(updatedVehicle.getBuyNowPrice());
                    vehicle.setConditionGrade(updatedVehicle.getConditionGrade());
                    vehicle.setDescription(updatedVehicle.getDescription());
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    //FilterVehicles
    public Page<Vehicle> filterVehicle (int pageSize,String make , String model , Integer yearFrom , Integer yearTo
            , Integer maxMiles , BigDecimal priceFrom , BigDecimal priceTo , TitleStatus titleStatus){


        Specification<Vehicle> specification = Specification.where(VehicleSpecs.makeEq(make))
                .and(VehicleSpecs.modelEq(model))
                .and(VehicleSpecs.mileageMax(maxMiles))
                .and(VehicleSpecs.priceBetween(priceFrom,priceTo))
                .and(VehicleSpecs.yearBetween(yearFrom , yearTo))
                .and(VehicleSpecs.titleStatusEq(titleStatus));
        Page<Vehicle> all = vehicleRepository.findAll(specification, PageRequest.of(0, pageSize));
        all.forEach(System.out::println);
        return all;
    }
}
