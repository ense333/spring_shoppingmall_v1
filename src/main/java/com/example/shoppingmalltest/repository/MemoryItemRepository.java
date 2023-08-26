package com.example.shoppingmalltest.repository;

import com.example.shoppingmalltest.domain.item.Item;
import com.example.shoppingmalltest.domain.item.ItemRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryItemRepository implements ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public Item findById(Long itemId) {
        return store.get(itemId);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long itemId, Item updateParam) {
        Item findItem = store.get(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setQuantity(updateParam.getQuantity());
        findItem.setItemPrice(updateParam.getItemPrice());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
