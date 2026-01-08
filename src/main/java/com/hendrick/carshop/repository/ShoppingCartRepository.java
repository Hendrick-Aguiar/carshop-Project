package com.hendrick.carshop.repository;

import com.hendrick.carshop.dto.ShoppingCartDTO;
import com.hendrick.carshop.enums.ShoppingCartStatus;
import com.hendrick.carshop.model.Client;
import com.hendrick.carshop.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long > {



    ;

    Optional<ShoppingCart> findByClientAndStatus(Client client, ShoppingCartStatus status);


}
