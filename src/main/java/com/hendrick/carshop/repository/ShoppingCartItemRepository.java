package com.hendrick.carshop.repository;

import com.hendrick.carshop.enums.VehicleStatus;
import com.hendrick.carshop.model.ShoppingCart;
import com.hendrick.carshop.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {



    Optional<ShoppingCartItem> findById(Long cartItemId);

    Optional<ShoppingCartItem> findByShoppingCartId(ShoppingCart cartItemId);

//    Optional<ShoppingCartItem> findByStatus(VehicleStatus status);

    List<ShoppingCartItem> findAndListAllByShoppingCart(ShoppingCart shoppingCart);

//    Optional<ShoppingCartItem> findVehicleStatus(Long vehicleId, VehicleStatus status);
}
