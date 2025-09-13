package com.auction.inventory.Controller;


import com.auction.common.dto.VehicleDTO;
import com.auction.inventory.Mapper.VehicleMapper;
import com.auction.inventory.Models.Domain.TitleStatus;
import com.auction.inventory.Models.Vehicle;
import com.auction.inventory.Services.VehicleService;
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


    @PostMapping("/addVehicle")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.createVehicle(vehicle));
    }

    @GetMapping("/getVehicleById")
    public ResponseEntity<VehicleDTO> getVehicle(@RequestParam(name="id") Long id) {

        return ResponseEntity.ok(VehicleMapper.vehicleToVehicleDTO(vehicleService.getVehicle(id)));
    }

    @GetMapping("/getAllVehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("test")
    public boolean test(){
        return true;
    }

    @GetMapping("/filter")
    public Page<Vehicle> filterVehicle(
            @RequestParam(name = "page") int page , @RequestParam(required = false , name = "make") String make
            , @RequestParam(required = false , name = "model") String model , @RequestParam(required = false , name = "yearFrom" ) Integer yearFrom
            , @RequestParam(required = false,name = "yearTo") Integer yearTo , @RequestParam(required = false , name = "maxMiles") Integer maxMiles
            , @RequestParam(required = false , name = "priceFrom")BigDecimal priceFrom, @RequestParam(required = false ,name = "priceTo")BigDecimal priceTo
            , @RequestParam(required = false , name = "status") TitleStatus status
    ) {
       return vehicleService.filterVehicle(page , make , model , yearFrom , yearTo , maxMiles , priceFrom , priceTo , status);
    }

}
