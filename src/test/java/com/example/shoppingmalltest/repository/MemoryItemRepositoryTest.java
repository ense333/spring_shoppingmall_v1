package com.example.shoppingmalltest.repository;

import com.example.shoppingmalltest.domain.item.Item;
import com.example.shoppingmalltest.domain.item.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryItemRepositoryTest {

    MemoryItemRepository itemRepository = new MemoryItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10, 10000);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("item1", 10, 10000);
        Item item2 = new Item("item2", 20, 20000);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("item1", 10, 10000);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("item2", 30, 20000);
        itemRepository.update(itemId, updateParam);

        Item findItem = itemRepository.findById(itemId);

        //then
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getItemPrice()).isEqualTo(updateParam.getItemPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}

