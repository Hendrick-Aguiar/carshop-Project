package com.hendrick.carshop.controller;

import com.hendrick.carshop.dto.*;
import com.hendrick.carshop.service.ShoppingCartItemService;
import com.hendrick.carshop.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@CrossOrigin("*")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;
    private ShoppingCartItemService shoppingCartItemService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ShoppingCartItemService shoppingCartItemService) {

        this.shoppingCartService = shoppingCartService;
        this.shoppingCartItemService = shoppingCartItemService;

    }

    @PostMapping("/items")
    public ResponseEntity<ShoppingCartDTO> addItemsToCart(@RequestBody ShoppingCartAddItemDTO dto) {

        return ResponseEntity.ok(shoppingCartService.addItemToCart(dto.getUserId(), dto.getVehicleId()));


    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> findCart(@PathVariable Long id) {

        return ResponseEntity.ok(shoppingCartService.getOrCreateActiveCart(id));

    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<ShoppingCartItemDTO>> findAll(@PathVariable Long id){

        return ResponseEntity.ok(shoppingCartService.findActiveCartByUserId(id));

    }

}
