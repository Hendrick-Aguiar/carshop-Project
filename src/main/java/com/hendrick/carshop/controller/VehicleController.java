package com.hendrick.carshop.controller;

import com.hendrick.carshop.dto.VehicleDTO;
import com.hendrick.carshop.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin("*")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){

        this.vehicleService = vehicleService;

    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id){

        return ResponseEntity.ok(vehicleService.findById(id));

    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> findAll() {

        return ResponseEntity.ok(vehicleService.findAll());

    }



}
