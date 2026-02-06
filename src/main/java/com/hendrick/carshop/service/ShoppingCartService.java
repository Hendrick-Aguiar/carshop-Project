package com.hendrick.carshop.service;

import com.hendrick.carshop.dto.ShoppingCartDTO;
import com.hendrick.carshop.dto.ShoppingCartItemDTO;
import com.hendrick.carshop.enums.ShoppingCartStatus;
import com.hendrick.carshop.enums.VehicleStatus;
import com.hendrick.carshop.model.*;
import com.hendrick.carshop.repository.*;
import org.springframework.http.HttpOutputMessage;
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
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;


    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ClientRepository clientRepository, VehicleRepository vehicleRepository, ShoppingCartItemRepository shoppingCartItemRepository) {

        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
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

    public List<ShoppingCartItemDTO> addItemToCart(Long userId, Long vehicleId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        //find client with the userid.
        Client client = clientRepository.findByUserId(user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));

        //find vehicle using vehicle id and the status.
        Vehicle vehicle = vehicleRepository.findByIdAndStatus(vehicleId, VehicleStatus.AVAILABLE).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle is not avaliable."));

        //find find cart with a client id and a active status, if the cart does not exist, create a client.
        ShoppingCart shoppingCart = shoppingCartRepository.findByClientAndStatus(client, ShoppingCartStatus.ACTIVE).orElseGet(() -> createNewCart(client));


        // shopping cart gets with the method the item(vehicle) and the the user.

        shoppingCart.addItem(vehicle, client.getUser());
        shoppingCart = shoppingCartRepository.save(shoppingCart);
        //save shoppingcart in the repository
        Optional<ShoppingCartItem> CartItem = shoppingCartItemRepository.findByShoppingCartId(vehicle.getShoppingCartItem().getShoppingCart());

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setShoppingCart(shoppingCart);
        shoppingCartItem.setVehicle(vehicle);
        shoppingCartItem.setCreatedAt(LocalDateTime.now());
        shoppingCartItem.setCreatedBy(user);


        vehicle.setShoppingCartItem(shoppingCartItem);
        vehicle.setStatus(VehicleStatus.RESERVED);

        shoppingCartItemRepository.save(shoppingCartItem);

        vehicleRepository.save(vehicle);


        return getItemDTOS(userId);

    }

    public List<ShoppingCartItemDTO> getItemDTOS(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        Client client = clientRepository.findByUserId(user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));
        ShoppingCart shoppingCart = shoppingCartRepository.findByClient(client).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));
        ShoppingCartDTO dto = new ShoppingCartDTO();
        dto.setId(shoppingCart.getId());
        dto.setClientId(shoppingCart.getClient().getId());
        dto.setStatus(shoppingCart.getStatus());

        List<ShoppingCartItem> items = shoppingCartItemRepository.findAndListAllByShoppingCart(shoppingCart);
        List<ShoppingCartItemDTO> listItems = new ArrayList<>();

        for (ShoppingCartItem item : items) {

            ShoppingCartItemDTO itemDTO = new ShoppingCartItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setPrice(item.getVehicle().getPrice());
            itemDTO.setVehicleId(item.getVehicle().getId());
            itemDTO.setVehicleName(item.getVehicle().getModel().getName());
            listItems.add(itemDTO);

        }
        return listItems;


    }

    private ShoppingCartDTO sumItemsDTO(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        Client client = clientRepository.findByUserId(user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));
        ShoppingCart shoppingCart = shoppingCartRepository.findByClient(client).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found."));

        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        //List and Stream items
        List<ShoppingCartItemDTO> shoppingCartItemDTO = getItemDTOS(shoppingCart.getClient().getUser().getId());
        shoppingCartDTO.setItems(shoppingCartItemDTO);
        shoppingCartDTO.setTotalItems(shoppingCartItemDTO.size());

        BigDecimal totalValues = shoppingCart.getItems().stream().map(cart -> cart.getVehicle().getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        shoppingCartDTO.setTotalValue(totalValues);
        shoppingCartDTO.setItems(getItemDTOS(userId));

        return shoppingCartDTO;
    }

    public List<ShoppingCartItemDTO> findActiveCartByUserId(Long id) {

        Client client = clientRepository.findByUserId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));

        ShoppingCart shoppingCart = shoppingCartRepository.findByClientAndStatus(client, ShoppingCartStatus.ACTIVE).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shopping Cart not found."));

        List<ShoppingCartItem> items = shoppingCartItemRepository.findAndListAllByShoppingCart(shoppingCart);

        if (items.isEmpty()) {

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


    public ShoppingCartDTO deleteCartItemChangeStatus(Long userId, Long cartItemId) {


        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        //Find user client
        Client client = clientRepository.findByUserId(user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));

        //Find client on shoppincart
        ShoppingCart shoppingCart = shoppingCartRepository.findByClient(client).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle is not reserved"));

        //find cart item by id
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(cartItemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item no found."));
        // Vehicle Entity take shopping cart
        Vehicle vehicle = shoppingCartItem.getVehicle();
        //change the status Reserved to Available and save
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        vehicleRepository.save(vehicle);
        //delete items
        shoppingCartItemRepository.delete(shoppingCartItem);
        //Return remaining items from shopping cart


        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setId(shoppingCart.getId());
        shoppingCartDTO.setClientId(shoppingCart.getClient().getId());
        shoppingCartDTO.setStatus(shoppingCart.getStatus());

        List<ShoppingCartItemDTO> shoppingCartItemDTOList = getItemDTOS(client.getUser().getId());
        shoppingCartDTO.setItems(shoppingCartItemDTOList);
        shoppingCartDTO.setTotalItems(shoppingCartItemDTOList.size());

        BigDecimal totalValue = shoppingCart.getItems()
                .stream().map(cart -> cart.getVehicle()
                        .getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        vehicleRepository.save(vehicle);

        shoppingCartDTO.setTotalValue(totalValue);
        return shoppingCartDTO;


    }
}



