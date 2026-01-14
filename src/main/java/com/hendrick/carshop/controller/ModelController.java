package com.hendrick.carshop.controller;

import com.hendrick.carshop.dto.ModelDTO;
import com.hendrick.carshop.service.ModelService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
@CrossOrigin("*")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {

        this.modelService = modelService;

    }


    @GetMapping("/id")
    public ResponseEntity<ModelDTO> findById(@RequestBody Long id) {

        return ResponseEntity.ok(modelService.findById(id));

    }


    @GetMapping
    public ResponseEntity<List<ModelDTO>> findAll() {

        return ResponseEntity.ok(modelService.findAll());

    }


}
