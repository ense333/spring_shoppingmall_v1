package com.example.shoppingmalltest.service;

import com.example.shoppingmalltest.domain.item.Item;
import com.example.shoppingmalltest.domain.item.ItemRepository;
import com.example.shoppingmalltest.repository.ItemSearchCond;
import com.example.shoppingmalltest.web.item.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceV1 implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> findItems(ItemSearchCond cond) {
        return itemRepository.findAll(cond);
    }

    @Override
    public void update(Long itemId, ItemUpdateForm updateParam) {
        itemRepository.update(itemId, updateParam);
    }
}
