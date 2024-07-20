package com.example.mapper;

import com.example.pojo.CartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartMapper {

    @Select("select * from tb_cart where user_id = #{userID}")
    List<CartItem> getCartItemsByUserID(Integer userID);
}
