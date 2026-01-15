package com.hendrick.carshop.repository;

import com.hendrick.carshop.enums.ShoppingCartStatus;
import com.hendrick.carshop.model.Client;
import com.hendrick.carshop.model.ShoppingCart;
import com.hendrick.carshop.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {



    Optional<ShoppingCart> findByClient(Client client);

    Optional<ShoppingCart> findByClientAndStatus(Client client, ShoppingCartStatus status);




}
