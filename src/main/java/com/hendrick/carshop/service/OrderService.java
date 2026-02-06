package com.hendrick.carshop.service;

import com.hendrick.carshop.dto.OrderDTO;
import com.hendrick.carshop.enums.OrderStatus;
import com.hendrick.carshop.enums.VehicleStatus;
import com.hendrick.carshop.model.*;
import com.hendrick.carshop.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final VehicleRepository vehicleRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final OrderItemRepository orderItemRepository;


    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, UserRepository userRepository, ShoppingCartRepository shoppingCartRepository, VehicleRepository vehicleRepository, ShoppingCartItemRepository shoppingCartItemrepository, OrderItemRepository orderItemRepository){

        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.vehicleRepository = vehicleRepository;
        this.shoppingCartItemRepository = shoppingCartItemrepository;
        this.orderItemRepository = orderItemRepository;


    }
    //Create
    public OrderDTO orderItems(Long userId, Long shoppingCartItemId){
        //verify user acc
        User user = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        Client client = clientRepository.findByUserId(user.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(shoppingCartItemId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shopping Cart Item not found."));
        Vehicle vehicle = vehicleRepository.findByShoppingCartItemId(shoppingCartItemId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item not found."));
        //create order
        Order order = new Order();
        order.setClient(client);
        order.setTotalAmount(vehicle.getPrice());
        order.setStatus(OrderStatus.PLACED);
        order = orderRepository.save(order);

        //create order item
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setVehicle(vehicle);
        orderItem.setCreatedAt(LocalDateTime.now());
        order.setCreatedBy(user);
        //change the vehicle status
        vehicle.setStatus(VehicleStatus.COMMITED);
        orderItem = orderItemRepository.save(orderItem);//save in the repository


        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setClient(order.getClient().getName());
        dto.setVehicleId(orderItem.getVehicle().getId());
        dto.setVehicleName(orderItem.getVehicle().getModel().getName());


        return dto;

        }




}
