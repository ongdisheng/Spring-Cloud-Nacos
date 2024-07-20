package com.example.service.impl;

import com.example.mapper.CartMapper;
import com.example.pojo.CartItem;
import com.example.pojo.Item;
import com.example.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<CartItem> getCartItemsByUserID(Integer userID) {
        List<CartItem> cartItems = cartMapper.getCartItemsByUserID(userID);

        // retrieve cart item ids
        List<Integer> cartItemIDs = cartItems.stream().map(cartItem -> cartItem.getItemID()).collect(Collectors.toList());
        String ids = cartItemIDs.stream().map(String::valueOf).collect(Collectors.joining(","));

        // make HTTP request to item service for getting item price info
        String resourceUrl = "http://localhost:8080/items";
        List<Item> items = restTemplate.exchange(
                resourceUrl + "/" + ids,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Item>>() {
                }
        ).getBody();
        for (int i = 0; i < cartItems.size(); i++) {
            cartItems.get(i).setItemPrice(items.get(i).getPrice());
        }

        return cartItems;
    }
}
