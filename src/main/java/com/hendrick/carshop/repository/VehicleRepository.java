package com.hendrick.carshop.repository;

import com.hendrick.carshop.enums.VehicleStatus;
import com.hendrick.carshop.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

//    Optional<Vehicle> findByCartItemId(Long shoppingCartItemId);
    Optional<Vehicle> findByIdAndStatus(Long vehicleId, VehicleStatus status);


    List<Vehicle> findById(Vehicle vehicle);


}
