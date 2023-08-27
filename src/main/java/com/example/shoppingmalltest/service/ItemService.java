package com.example.shoppingmalltest.service;

import com.example.shoppingmalltest.domain.item.Item;
import com.example.shoppingmalltest.domain.item.ItemRepository;
import com.example.shoppingmalltest.repository.ItemSearchCond;
import com.example.shoppingmalltest.web.item.form.ItemUpdateForm;

import java.util.List;
import java.util.Optional;


public interface ItemService {

    Item save(Item item);
    Optional<Item> findById(Long id);
    List<Item> findItems(ItemSearchCond cond);
    void update(Long itemId, ItemUpdateForm updateParam);
}
