package com.hendrick.carshop.repository;

import com.hendrick.carshop.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleInterface extends JpaRepository<Vehicle, Long> {

    public Vehicle findVehicleById(Long id);

}
