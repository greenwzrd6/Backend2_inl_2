package se.yrgo.meccano.domain.dto;

import se.yrgo.meccano.data.projection.*;

public record VehicleBrandDto(
        String registrationNumber,
        String brand) {
    public static VehicleBrandDto from(VehicleBrandProjection vb) {
        return new VehicleBrandDto(
                vb.getRegistrationNumber(),
                vb.getBrand());
    }
}
