    package com.hendrick.carshop.controller;

    import com.hendrick.carshop.dto.*;
    import com.hendrick.carshop.service.ShoppingCartItemService;
    import com.hendrick.carshop.service.ShoppingCartService;
    import jakarta.servlet.http.HttpSession;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.server.ResponseStatusException;

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
        public ResponseEntity<ShoppingCartDTO> findCart(@RequestBody Long id, HttpSession session) {

            Long userId = (Long) session.getAttribute("loggedUserId");
            return ResponseEntity.ok(shoppingCartService.getOrCreateActiveCart(userId));



        }

        @GetMapping("/items")
        public ResponseEntity<List<ShoppingCartItemDTO>> findAll(HttpSession session){
            System.out.println(">>> ENTROU NO CONTROLLER /carts/items");
            Long userId = (Long) session.getAttribute("loggedUserId");



            if (userId == null) {
                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok(shoppingCartService.findActiveCartByUserId(userId));



        }

    }
