package com.example.controller;

import com.example.pojo.CartItem;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public List<CartItem> getCartItemsByUserID(Integer userID) {
        return cartService.getCartItemsByUserID(userID);
    }
}
