package com.hendrick.carshop.controller;

import com.hendrick.carshop.dto.ShoppingCartAddItemDTO;
import com.hendrick.carshop.dto.ShoppingCartDTO;
import com.hendrick.carshop.dto.ShoppingCartItemDTO;
import com.hendrick.carshop.model.ShoppingCartItem;
import com.hendrick.carshop.service.ShoppingCartItemService;
import com.hendrick.carshop.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/item")
    public ResponseEntity<ShoppingCartDTO> addItemsToCart(@RequestBody ShoppingCartAddItemDTO dto, HttpSession session) {

        return ResponseEntity.ok(shoppingCartService.addItemToCart(dto.getUserId(), dto.getVehicleId()));


    }

    @GetMapping
    public ResponseEntity<ShoppingCartDTO> findCartOrCreate(HttpSession session) {

        Long userId = (Long) session.getAttribute("loggedUserId");
        return ResponseEntity.ok(shoppingCartService.getOrCreateActiveCart(userId));


    }

    @GetMapping("/items")
    public ResponseEntity<List<ShoppingCartItemDTO>> findAllCartItems(HttpSession session) {

        System.out.println(">>> ENTROU NO CONTROLLER /carts/items");
        Long userId = (Long) session.getAttribute("loggedUserId");

        return ResponseEntity.ok(shoppingCartService.findActiveCartByUserId(userId));


    }

//    @GetMapping("/delete")
//    public ResponseEntity<ShoppingCartDTO> deleteChangeStatus(@PathVariable Long cartItemId, HttpSession session) {
//
//        Long userId = (Long) session.getAttribute("loggedUserId");
//
//        return ResponseEntity.ok(shoppingCartService.deleteCartItemChangeStatus(userId, cartItemId));
//
//    }


}
