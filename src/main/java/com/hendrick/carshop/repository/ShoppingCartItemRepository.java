package com.hendrick.carshop.repository;

import com.hendrick.carshop.enums.VehicleStatus;
import com.hendrick.carshop.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

;
    Optional<ShoppingCartItem> findAllByShoppingCartId(Long id);
    List<ShoppingCartItem> findAndListAllByShoppingCartId(Long id);
    Optional<ShoppingCartItem> findVehicleById(Long vehicleId, VehicleStatus status);
}
