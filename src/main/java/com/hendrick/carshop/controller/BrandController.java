package com.hendrick.carshop.controller;

import com.hendrick.carshop.dto.BrandDTO;
import com.hendrick.carshop.service.BrandService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@CrossOrigin("*")
public final class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService){

        this.brandService = brandService;

    }
    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok(brandService.findById(id));

    }

    //Read: Find all
    @GetMapping
    public ResponseEntity<List<BrandDTO>> findAll(HttpSession session){

        return ResponseEntity.ok(brandService.findAll());

    }




}
