package com.auction.inventory.Services;

import com.auction.inventory.Models.Domain.TitleStatus;
import com.auction.inventory.Models.Vehicle;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class VehicleSpecs {


    public static Specification<Vehicle> makeEq(String make){
        return (make == null || make.isBlank()) ? null :
                (root, query, cb) -> cb.equal(cb.lower(root.get("make")), make.toLowerCase());
    }

    public static Specification<Vehicle> modelEq(String model){
        return (model == null || model.isBlank()) ? null
                : (root,query,cb) -> cb.equal(cb.lower(root.get("model")), model.toLowerCase());
    }

    public static Specification<Vehicle> yearBetween(Integer from, Integer to) {
        return (from == null && to == null) ? null : (root, q, cb) -> {
            if (from != null && to != null) return cb.between(root.get("year"), from, to);
            if (from != null) return cb.greaterThanOrEqualTo(root.get("year"), from);
            return cb.lessThanOrEqualTo(root.get("year"), to);
        };
    }

    public static Specification<Vehicle> mileageMax(Integer max) {
        return (max == null) ? null : (root, q, cb) -> cb.lessThanOrEqualTo(root.get("mileage"), max);
    }

    public static Specification<Vehicle> priceBetween(BigDecimal min, BigDecimal max) {
        return (min == null && max == null) ? null : (root, q, cb) -> {
            if (min != null && max != null) return cb.between(root.get("startingPrice"), min, max);
            if (min != null) return cb.greaterThanOrEqualTo(root.get("startingPrice"), min);
            return cb.lessThanOrEqualTo(root.get("startingPrice"), max);
        };
    }

    public static Specification<Vehicle> titleStatusEq(TitleStatus status) {
        return (status == null) ? null : (root, q, cb) -> cb.equal(root.get("titleStatus"), status);
    }

}