package com.example.shoppingmalltest.domain.item;


import java.util.List;
import java.util.Optional;

public interface ItemRepository{

    Item save(Item item);
    Item findById(Long itemId);
    List<Item> findAll();
    void update(Long itemId, Item updateParam);
    void clearStore();

}
