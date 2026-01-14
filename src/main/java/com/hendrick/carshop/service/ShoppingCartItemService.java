package com.hendrick.carshop.service;

import com.hendrick.carshop.repository.ShoppingCartItemRepository;
import com.hendrick.carshop.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartItemService {


    private final ShoppingCartRepository shoppingCartRepository;

    public final ShoppingCartItemRepository shoppingCartItemRepository;


    public ShoppingCartItemService(ShoppingCartRepository shoppingCartRepository, ShoppingCartItemRepository shoppingCartItemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
    }


}
