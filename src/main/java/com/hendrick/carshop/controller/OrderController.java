package com.hendrick.carshop.controller;

import com.hendrick.carshop.dto.OrderDTO;
import com.hendrick.carshop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrderByCartItemId(@RequestBody HttpSession session, Long shoppingCartItemId){

       Long userId = (Long) session.getAttribute("loggedUserId");
       return ResponseEntity.ok(orderService.orderItems(userId, shoppingCartItemId));
    }
}
