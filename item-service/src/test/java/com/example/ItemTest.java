package com.example;

import com.example.mapper.ItemMapper;
import com.example.pojo.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ItemTest {
    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void getItems() {
        List<Item> items = itemMapper.getItems();
        for (Item item : items) {
            System.out.println(item);
        }
    }

//    @Test
//    public void getItemByID() {
//        Item item = itemMapper.getItemByID(2);
//        System.out.println(item);
//    }

    @Test
    public void getItemsByIDs() {
        List<Item> items = itemMapper.getItemsByIDs(new Integer[]{1, 2});
        for (Item item : items) {
            System.out.println(item);
        }
    }
}
