package com.hendrick.carshop.service;

import com.hendrick.carshop.dto.ShoppingCartDTO;
import com.hendrick.carshop.dto.ShoppingCartItemDTO;
import com.hendrick.carshop.enums.ShoppingCartStatus;
import com.hendrick.carshop.enums.VehicleStatus;
import com.hendrick.carshop.model.*;
import com.hendrick.carshop.repository.ClientRepository;
import com.hendrick.carshop.repository.ShoppingCartItemRepository;
import com.hendrick.carshop.repository.ShoppingCartRepository;
import com.hendrick.carshop.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;


    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ClientRepository clientRepository, VehicleRepository vehicleRepository, ShoppingCartItemRepository shoppingCartItemRepository) {

        this.shoppingCartRepository = shoppingCartRepository;
        this.clientRepository = clientRepository;
        this.vehicleRepository = vehicleRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
    }


    //Create Verify or Shopping Cart        //Login >> auth >>> userId>>Client

    /**
     * Logic: If the user has an ACTIVE cart, return it.
     * If they don't have one, create a new one first.
     */
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

    public ShoppingCartDTO addItemToCart(Long userId, Long vehicleId) {

        //find client with the userid.
        Client client = clientRepository.findByUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));

        //find vehicle using vehicle id and the status.
        Vehicle vehicle = vehicleRepository.findByIdAndStatus(vehicleId, VehicleStatus.AVAILABLE).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle is not avaliable."));

        //find find cart with a client id and a active status, if the cart does not exist, create a client.
        ShoppingCart shoppingCart = shoppingCartRepository.findByClientAndStatus(client, ShoppingCartStatus.ACTIVE).orElseGet(() -> createNewCart(client));

        // shopping cart gets with the method the item(vehicle) and the the user.
        shoppingCart.addItem(vehicle, client.getUser());

        vehicle.setStatus(VehicleStatus.RESERVED);

        shoppingCart = shoppingCartRepository.save(shoppingCart);

        //save shoppingcart in the repository

        // Create a DTO to expose cart data (id, clientId, status) to the controller
        ShoppingCartDTO cartDTO = new ShoppingCartDTO();
        cartDTO.setId(shoppingCart.getId());
        cartDTO.setClientId(shoppingCart.getClient().getId());
        cartDTO.setStatus(shoppingCart.getStatus());

        // This list will hold ShoppingCartItemDTO objects (not entities)
        List<ShoppingCartItemDTO> shoppingCartItemDTO = new ArrayList<>();

        // Convert each ShoppingCartItem entity into a ShoppingCartItemDTO
        for (ShoppingCartItem cart : shoppingCart.getItems()) {

            ShoppingCartItemDTO itemDTO = new ShoppingCartItemDTO();
            itemDTO.setId(cart.getId());
            itemDTO.setVehicleId(cart.getVehicle().getId());
            itemDTO.setVehicleName(cart.getVehicle().getModel().getName());
            itemDTO.setPrice(cart.getVehicle().getPrice());
            // Add the mapped item DTO to the cart item DTO list
            shoppingCartItemDTO.add(itemDTO);

        }

        // Attach the list of item DTOs to the cart DTO
        cartDTO.setItems(shoppingCartItemDTO);
        // Set the total number of items in the cart
        cartDTO.setTotalItems(shoppingCart.getItems().size());
        //Set the total number of items in the cart
        cartDTO.setItems(shoppingCartItemDTO);
                                                    //“Turn this list into a stream so I can process its elements one by one.”
        BigDecimal totalValue = shoppingCart.getItems().stream()//stream is a pipeline or a path to process one by one
                .map(cart -> cart.getVehicle().getPrice()) //transformation: For each item, take it and transform it into its price
                //combine: Start with ZERO and keep adding each price
                .reduce(BigDecimal.ZERO, BigDecimal::add);//add:: is a accumulator +=
        cartDTO.setTotalValue(totalValue);

        vehicleRepository.save(vehicle);

        return cartDTO;

    }

    public List<ShoppingCartItemDTO> findActiveCartByUserId(Long id){

        Client client = clientRepository.findByUserId(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));

        ShoppingCart shoppingCart = shoppingCartRepository.findByClientAndStatus(client, ShoppingCartStatus.ACTIVE).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shopping Cart not found."));

        List<ShoppingCartItem> items = shoppingCartItemRepository.findAndListAllByShoppingCartId(shoppingCart.getId());

        if (items.isEmpty()){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No items found in the cart.");

        }

        List<ShoppingCartItemDTO> cartItemDTOS = new ArrayList<>();

        for (ShoppingCartItem item : items) {

            ShoppingCartItemDTO dto = new ShoppingCartItemDTO();
            dto.setId(item.getId());
            dto.setVehicleId(item.getVehicle().getId());
            dto.setVehicleName(item.getVehicle().getModel().getName());
            dto.setPrice(item.getVehicle().getPrice());
            cartItemDTOS.add(dto);

        }

        return cartItemDTOS;
    }
    @Transactional
    public void deleteCartitemChangeStatus(Long cartItemId){

        //Find user client
        Client client = clientRepository.findByUserId(cartItemId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));

        //Find client on shoppincart
        ShoppingCart shoppingCart = shoppingCartRepository.findByClientId(client).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle is not reserved"));

        //find cart item by id and the status
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findVehicleById(cartItemId ,VehicleStatus.RESERVED).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found."));

        Vehicle vehicle = shoppingCartItem.getVehicle();

        vehicle.setStatus(VehicleStatus.AVAILABLE);

        vehicleRepository.save(vehicle);

        shoppingCartItemRepository.delete(shoppingCartItem);


    }

}
