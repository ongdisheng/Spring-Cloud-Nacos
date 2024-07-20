package com.example.service;

import com.example.pojo.CartItem;

import java.util.List;

public interface CartService {

    List<CartItem> getCartItemsByUserID(Integer userID);
}
