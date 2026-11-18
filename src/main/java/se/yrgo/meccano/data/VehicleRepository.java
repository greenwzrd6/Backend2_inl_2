package se.yrgo.meccano.data;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.*;

import se.yrgo.meccano.data.projection.*;
import se.yrgo.meccano.domain.*;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByBrand(String brandName);

    List<Vehicle> findByCustomerId(Long customerId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Vehicle v SET v.brand = :brand WHERE v.id = :id")
    int updateVehicleBrandByVehicleId(@Param("id") Long vehicleId, @Param("brand") String newBrand);

    @Query("SELECT v.registrationNumber AS registrationNumber, v.brand AS brand FROM Vehicle v")
    List<VehicleBrandProjection> findAllVehicleBrands();
}
