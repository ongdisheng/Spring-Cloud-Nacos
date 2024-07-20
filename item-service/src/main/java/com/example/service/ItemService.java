package com.example.service;

import com.example.pojo.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItems();

    Item getItemByID(Integer id);

    List<Item> getItemsByIDs(Integer[] ids);
}
