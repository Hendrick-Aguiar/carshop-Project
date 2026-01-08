package com.hendrick.carshop.controller;

import com.hendrick.carshop.dto.CartDTO;
import com.hendrick.carshop.dto.ShoppingCartDTO;
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

//    @PostMapping("/additems")
//    public ResponseEntity<List<CartDTO>> addItemsToCart(@RequestBody CartDTO dto) {
//
//        return ResponseEntity.ok(shoppingCartService.)
//
//
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> findCart(@PathVariable Long id) {

        return ResponseEntity.ok(shoppingCartService.getOrCreateActiveCart(id));

    }

//    @PostMapping("/items")
//    public ResponseEntity<List<CartDTO>> findAll(@RequestBody CartDTO dto){
//
//        return ResponseEntity.ok(shoppingCartService.findAllCartItems(dto.getUserId(), dto.getItemId()));
//
//    }



}
