package se.yrgo.meccano.domain.dto;

import java.time.*;

import se.yrgo.meccano.domain.*;

public record VehicleDto(
        Long id,
        String registrationNumber,
        String brand,
        String model,
        Year productionYear,
        Long customerId) {
    public static VehicleDto from(Vehicle v) {
        Long customerId = (v.getCustomer() != null) ? v.getCustomer().getId() : null;
        return new VehicleDto(
                v.getId(),
                v.getRegistrationNumber(),
                v.getBrand(),
                v.getModel(),
                v.getProductionYear(),
                customerId);
    }
}
