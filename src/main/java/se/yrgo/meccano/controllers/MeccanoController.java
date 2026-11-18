package se.yrgo.meccano.controllers;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import jakarta.transaction.*;
import se.yrgo.meccano.controllers.requests.*;
import se.yrgo.meccano.data.*;
import se.yrgo.meccano.domain.*;
import se.yrgo.meccano.domain.dto.*;

@RestController
public class MeccanoController {
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    public MeccanoController(
            CustomerRepository customerRepository,
            VehicleRepository vehicleRepository) {
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@ModelAttribute CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setPhoneNumber(request.phoneNumber());

        Customer saved = customerRepository.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerDto.from(saved));
    }

    @PostMapping("/vehicles")
    public ResponseEntity<VehicleDto> createVehicle(@ModelAttribute CreateVehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(request.registrationNumber());
        vehicle.setBrand(request.brand());
        vehicle.setModel(request.model());
        vehicle.setProductionYear(request.productionYear());

        Vehicle saved = vehicleRepository.save(vehicle);

        return ResponseEntity.status(HttpStatus.CREATED).body(VehicleDto.from(saved));
    }

    @PostMapping("/addvehicletocustomer")
    @Transactional
    public ResponseEntity<VehicleDto> addVehicleToCustomer(
            @RequestParam Long customerId,
            @RequestParam Long vehicleId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"));

        // Jag har valt att tillåta ägarbyte
        Customer oldOwner = vehicle.getCustomer();
        if (oldOwner != null && !oldOwner.getId().equals(customerId)) {
            oldOwner.removeVehicle(vehicle);
        }

        customer.addVehicle(vehicle);
        Vehicle saved = vehicleRepository.save(vehicle);

        return ResponseEntity.ok(VehicleDto.from(saved));
    }

    @PutMapping("/vehicles/{id}/brand")
    public ResponseEntity<VehicleDto> updateVehicleBrand(
            @PathVariable Long id,
            @RequestParam String brand) {

        int updated = vehicleRepository.updateVehicleBrandByVehicleId(id, brand);
        if (updated == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
        }
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(VehicleDto.from(vehicle));
    }

    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAllWithVehicles().stream()
                .map(CustomerDto::from)
                .toList();
    }

    @GetMapping("/customer-id")
    public Long getCustomerIdByName(@RequestParam String name) {
        return customerRepository.findByName(name).get().getId();
    }

    @GetMapping("/vehicles")
    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(VehicleDto::from)
                .toList();
    }

    @GetMapping("/vehicles-by-brand")
    public List<VehicleDto> getAllVehiclesByBrand(@RequestParam String brand) {
        return vehicleRepository.findByBrand(brand).stream()
                .map(VehicleDto::from)
                .toList();
    }

    @GetMapping("/vehicle-brand-info")
    public List<VehicleBrandDto> getAllVehicleBrands() {
        return vehicleRepository.findAllVehicleBrands().stream()
                .map(VehicleBrandDto::from)
                .toList();
    }
}
