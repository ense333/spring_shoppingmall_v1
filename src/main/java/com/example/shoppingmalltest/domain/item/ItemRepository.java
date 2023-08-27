package com.example.shoppingmalltest.domain.item;


import com.example.shoppingmalltest.repository.ItemSearchCond;
import com.example.shoppingmalltest.web.item.form.ItemUpdateForm;

import java.util.List;
import java.util.Optional;

public interface ItemRepository{

    Item save(Item item);
    Optional<Item> findById(Long itemId);
    List<Item> findAll(ItemSearchCond cond);
    void update(Long itemId, ItemUpdateForm updateParam);
    void clearStore();

}
