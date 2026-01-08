package com.hendrick.carshop.service;

import com.hendrick.carshop.dto.CartDTO;
import com.hendrick.carshop.dto.ShoppingCartDTO;
import com.hendrick.carshop.dto.ShoppingCartItemDTO;
import com.hendrick.carshop.enums.ShoppingCartStatus;
import com.hendrick.carshop.enums.VehicleStatus;
import com.hendrick.carshop.model.Client;
import com.hendrick.carshop.model.ShoppingCart;
import com.hendrick.carshop.model.ShoppingCartItem;
import com.hendrick.carshop.model.Vehicle;
import com.hendrick.carshop.repository.ClientRepository;
import com.hendrick.carshop.repository.ShoppingCartRepository;
import com.hendrick.carshop.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;


    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ClientRepository clientRepository, VehicleRepository vehicleRepository) {

        this.shoppingCartRepository = shoppingCartRepository;
        this.clientRepository = clientRepository;
        this.vehicleRepository = vehicleRepository;
    }


    //Create Verify or Shopping Cart        //Login >> auth >>> userId>>Client
    /**
     * Logic: If the user has an ACTIVE cart, return it.
     * If they don't have one, create a new one first.*/
    public ShoppingCartDTO getOrCreateActiveCart(Long userId) {

        // 1. Convert the userId (from login) into a Client entity.
        // If the client doesn't exist in our records, throw a 404 error.
        Client client = clientRepository.findByUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

        // 2. Look for a cart that belongs to this client AND is still "ACTIVE".
        // Using Optional allows us to handle the "not found" case gracefully.
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findByClientAndStatus(client, ShoppingCartStatus.ACTIVE);

        ShoppingCart cart;

        // 3. The "Get or Create" check
        if (optionalCart.isPresent()) {
            // // If the database found an active cart, use it.
            cart = optionalCart.get();

        } else {
            // If no active cart exists, call our private helper method to make a new one.
            cart = createNewCart(client);

        }
        // 4. Mapping: Convert the Database Entity into a DTO (Data Transfer Object).
        // This hides internal DB fields and sends only what the frontend needs.
        ShoppingCartDTO dto = new ShoppingCartDTO();
        dto.setClientName(cart.getClient().getName());
        dto.setStatus(cart.getStatus());
        dto.setCreatedAt(cart.getCreatedAt());
        return dto;

    }

    //Helper Method: Handles the actual creation and persistence of a new cart.
    private ShoppingCart createNewCart(Client client) {

        ShoppingCart cart = new ShoppingCart();
        cart.setClient(client);
        cart.setStatus(ShoppingCartStatus.ACTIVE);
        cart.setCreatedAt(LocalDateTime.now());

        return shoppingCartRepository.save(cart);
    }
//
//    public List<CartDTO> addItemToCart(Long userId, Long vehicleId){
//
//        Client client = clientRepository.findByUserId(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));
//
//        Vehicle vehicle = vehicleRepository.findByIdAndStatus(vehicleId, VehicleStatus.AVAILABLE).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle is not avaliable."));
//
//        ShoppingCart shoppingCart = shoppingCartRepository.findByClientAndStatus(client, ShoppingCartStatus.ACTIVE).orElseGet(()-> createNewCart(client));
//
//        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
//        shoppingCartItem.setShoppingCart(shoppingCart);
//        shoppingCartItem.setVehicle(vehicle);
//        sho().add(shoppingCartItem);
//        shoppingCartRepository.save(shoppingCart);








}
