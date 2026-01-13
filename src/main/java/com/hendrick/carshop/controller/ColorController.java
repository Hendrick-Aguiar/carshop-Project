package com.hendrick.carshop.controller;

import com.hendrick.carshop.dto.ColorDTO;
import com.hendrick.carshop.model.Color;
import com.hendrick.carshop.service.ColorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colors")
@CrossOrigin("*")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {

        this.colorService = colorService;

    }

    //Read: fetch by id
    @GetMapping("/{id}")
    public ResponseEntity<ColorDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok(colorService.findById(id));

    }

    //Read: All Colors
    @GetMapping
    public ResponseEntity<List<ColorDTO>> findAll() {

        return ResponseEntity.ok(colorService.findAll());


    }


}
