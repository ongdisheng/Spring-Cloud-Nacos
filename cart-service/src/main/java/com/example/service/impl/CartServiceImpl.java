package com.example.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.example.mapper.CartMapper;
import com.example.pojo.CartItem;
import com.example.pojo.Item;
import com.example.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public List<CartItem> getCartItemsByUserID(Integer userID) {
        List<CartItem> cartItems = cartMapper.getCartItemsByUserID(userID);

        // retrieve cart item ids
        List<Integer> cartItemIDs = cartItems.stream().map(cartItem -> cartItem.getItemID()).collect(Collectors.toList());
        String ids = cartItemIDs.stream().map(String::valueOf).collect(Collectors.joining(","));

//        // A. without nacos
//        // make HTTP request to item service for getting item price info
//        String resourceUrl = "http://localhost:8080/items";
//        List<Item> items = restTemplate.exchange(
//                resourceUrl + "/" + ids,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Item>>() {
//                }
//        ).getBody();

        // B. with nacos (resolve hardcoding issue)
        // retrieve item service provider instances
        List<ServiceInstance> instances = discoveryClient.getInstances("item-service");

        // simple load balancing implementation
        ServiceInstance instance = instances.get(RandomUtil.randomInt(instances.size()));

        // make HTTP request to item service for getting item price info
        ResponseEntity<List<Item>> response = restTemplate.exchange(
                instance.getUri() + "/items/" + ids,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Item>>() {
                }
        );

        // handle response
        List<Item> items = null;
        if (response.getStatusCode().is2xxSuccessful()) {
            items = response.getBody();
            for (int i = 0; i < cartItems.size(); i++) {
                cartItems.get(i).setItemPrice(items.get(i).getPrice());
            }

            return cartItems;
        } else {
            return null;
        }
    }
}
