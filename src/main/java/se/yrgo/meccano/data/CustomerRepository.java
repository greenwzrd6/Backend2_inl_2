package se.yrgo.meccano.data;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import se.yrgo.meccano.domain.*;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String name);

    @Query("SELECT DISTINCT c FROM Customer c LEFT JOIN FETCH c.vehicles")
    List<Customer> findAllWithVehicles();
}
