package com.hendrick.carshop.service;

import com.hendrick.carshop.dto.ShoppingCartDTO;
import com.hendrick.carshop.dto.ShoppingCartItemDTO;
import com.hendrick.carshop.dto.VehicleDTO;
import com.hendrick.carshop.enums.ShoppingCartStatus;
import com.hendrick.carshop.enums.VehicleStatus;
import com.hendrick.carshop.model.Client;
import com.hendrick.carshop.model.ShoppingCart;
import com.hendrick.carshop.model.ShoppingCartItem;
import com.hendrick.carshop.model.Vehicle;
import com.hendrick.carshop.repository.ClientRepository;
import com.hendrick.carshop.repository.ShoppingCartItemRepository;
import com.hendrick.carshop.repository.ShoppingCartRepository;
import com.hendrick.carshop.repository.VehicleRepository;
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

        Client client = clientRepository.findByUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));

        Vehicle vehicle = vehicleRepository.findByIdAndStatus(vehicleId, VehicleStatus.AVAILABLE).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle is not avaliable."));

        ShoppingCart shoppingCart = shoppingCartRepository.findByClientAndStatus(client, ShoppingCartStatus.ACTIVE).orElseGet(() -> createNewCart(client));

        shoppingCart.addItem(vehicle, client.getUser());

        shoppingCart = shoppingCartRepository.save(shoppingCart);

        ShoppingCartDTO dto = new ShoppingCartDTO();
        dto.setId(shoppingCart.getId());
        dto.setClientId(shoppingCart.getClient().getId());
        dto.setStatus(shoppingCart.getStatus());
        List<ShoppingCartItemDTO> shoppingCartItemDTO = new ArrayList<>();



        for (ShoppingCartItem cart : shoppingCart.getItems()) {

            ShoppingCartItemDTO itemDTO = new ShoppingCartItemDTO();
            itemDTO.setId(cart.getId());
            itemDTO.setVehicleId(cart.getVehicle().getId());
            itemDTO.setVehicleName(cart.getVehicle().getModel().getName());
            itemDTO.setPrice(cart.getVehicle().getPrice());


            shoppingCartItemDTO.add(itemDTO);


        }
        dto.setItems(shoppingCartItemDTO);
        dto.setTotalItems(shoppingCart.getItems().size());
        dto.setItems(shoppingCartItemDTO);
        BigDecimal totalValue = shoppingCart.getItems().stream()
                .map(cart -> cart.getVehicle().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            dto.setTotalValue(totalValue);
            return dto;
//        List<ShoppingCartItem> cartItems =  shoppingCartItemRepository.findAllById(vehicleId);
//        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
//
//        for (ShoppingCartItem item : cartItems){
//
//            VehicleDTO vehicleDTO = new VehicleDTO();
//            vehicleDTO.setId(item.getVehicle().getId());


    }

}
