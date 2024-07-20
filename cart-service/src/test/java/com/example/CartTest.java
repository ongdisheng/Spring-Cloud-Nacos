package com.example;

import com.example.mapper.CartMapper;
import com.example.pojo.CartItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CartTest {

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void getCartItemsByUserID() {
        List<CartItem> cartItems = cartMapper.getCartItemsByUserID(1);
        for (CartItem cartItem : cartItems) {
            System.out.println(cartItem);
        }
    }
}
