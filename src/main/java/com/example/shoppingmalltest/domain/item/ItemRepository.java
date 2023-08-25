package com.example.shoppingmalltest.domain.item;

import com.example.shoppingmalltest.domain.item.Item;

import java.util.List;

public interface ItemRepository{

    Item save(Item item);
    Item findById(Long itemId);
    List<Item> findAll();
    void update(Long itemId, Item updateParam);
    void clearStore();

}
