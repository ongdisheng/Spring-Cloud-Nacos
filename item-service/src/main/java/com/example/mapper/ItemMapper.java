package com.example.mapper;

import com.example.pojo.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemMapper {

    @Select("select * from tb_item")
    List<Item> getItems();

    @Select("select * from tb_item where id = #{id}")
    Item getItemByID(Integer id);

    List<Item> getItemsByIDs(Integer[] ids);
}
