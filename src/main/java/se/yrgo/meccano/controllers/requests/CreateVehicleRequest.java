package se.yrgo.meccano.controllers.requests;

import java.time.*;

public record CreateVehicleRequest(
                String registrationNumber,
                String brand,
                String model,
                Year productionYear) {
}
