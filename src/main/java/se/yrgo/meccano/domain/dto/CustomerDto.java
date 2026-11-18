package se.yrgo.meccano.domain.dto;

import java.util.*;
import java.util.stream.*;

import se.yrgo.meccano.domain.*;

public record CustomerDto(
                Long id,
                String name,
                String phoneNumber,
                Set<VehicleDto> vehicles) {
        public static CustomerDto from(Customer c) {
                return new CustomerDto(
                                c.getId(),
                                c.getName(),
                                c.getPhoneNumber(),
                                c.getVehicles().stream()
                                                .map(VehicleDto::from)
                                                .collect(Collectors.toSet()));
        }
}
