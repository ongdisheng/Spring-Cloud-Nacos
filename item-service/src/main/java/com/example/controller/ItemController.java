package com.example.controller;

import com.example.pojo.Item;
import com.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getItems() {
        return itemService.getItems();
    }

//    @GetMapping("/{id}")
//    public Item getItemByID(@PathVariable Integer id) {
//        return itemService.getItemByID(id);
//    }

    @GetMapping("/{ids}")
    public List<Item> getItemsByIDs(@PathVariable Integer[] ids) {
        return itemService.getItemsByIDs(ids);
    }
}
