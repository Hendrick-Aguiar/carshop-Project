package com.hendrick.carshop.repository;

import com.hendrick.carshop.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

;
    Optional<List<ShoppingCartItem>> findAllListsById(Long shoppinCartItemId);
    Optional<ShoppingCartItem> findAllById(Long shoppingCartItemId);
}
