package com.hendrick.carshop.service;

import com.hendrick.carshop.dto.VehicleDTO;
import com.hendrick.carshop.enums.VehicleStatus;
import com.hendrick.carshop.model.Vehicle;
import com.hendrick.carshop.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {

        this.vehicleRepository = vehicleRepository;

    }

    public VehicleDTO findById(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found."));

        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setLicencePlate(vehicle.getLicencePlate());
        dto.setChassis(vehicle.getChassis());
        dto.setRenavam(vehicle.getRenavam());
        dto.setYear(vehicle.getYear());
        dto.setBrandId(vehicle.getModel().getBrand().getId());
        dto.setBrand(vehicle.getModel().getBrand().getName());
        dto.setModelId(vehicle.getModel().getId());
        dto.setModel(vehicle.getModel().getName());
        dto.setColorId(vehicle.getColor().getId());
        dto.setColor(vehicle.getColor().getName());
        dto.setPrice(vehicle.getPrice());
        dto.setStatus(vehicle.getStatus());

        return dto;


    }


    public List<VehicleDTO> findAll() {


        List<Vehicle> vehicles = vehicleRepository.findAll();

        List<VehicleDTO> vehicleDTOList = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getStatus().equals(VehicleStatus.AVAILABLE)) {
                VehicleDTO dto = new VehicleDTO();
                dto.setId(vehicle.getId());
                dto.setLicencePlate(vehicle.getLicencePlate());
                dto.setChassis(vehicle.getChassis());
                dto.setRenavam(vehicle.getRenavam());
                dto.setYear(vehicle.getYear());
                dto.setBrandId(vehicle.getModel().getBrand().getId());
                dto.setBrand(vehicle.getModel().getBrand().getName());
                dto.setModelId(vehicle.getModel().getId());
                dto.setModel(vehicle.getModel().getName());
                dto.setColorId(vehicle.getColor().getId());
                dto.setColor(vehicle.getColor().getName());
                dto.setPrice(vehicle.getPrice());
                dto.setStatus(vehicle.getStatus());
                vehicleDTOList.add(dto);
            }
        }

        return vehicleDTOList;

    }


}
