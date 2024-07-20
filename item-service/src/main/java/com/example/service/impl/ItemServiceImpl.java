package com.example.service.impl;

import com.example.mapper.ItemMapper;
import com.example.pojo.Item;
import com.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> getItems() {
        return itemMapper.getItems();
    }

    @Override
    public Item getItemByID(Integer id) {
        return itemMapper.getItemByID(id);
    }

    @Override
    public List<Item> getItemsByIDs(Integer[] ids) {
        return itemMapper.getItemsByIDs(ids);
    }
}
