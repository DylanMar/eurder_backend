package com.switchfully.eurder_backend.repository;

import com.switchfully.eurder_backend.entity.Item;
import com.switchfully.eurder_backend.exception.NotFoundException;
import com.switchfully.eurder_backend.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {}

    @Test
    void getItemById_ExistingId_ReturnsItem() {
        Long itemId = itemRepository.findAll().get(0).getItemId();
        Item retrievedItem = itemRepository.findById(itemId).get();
        assertNotNull(retrievedItem);
        assertEquals(itemId, retrievedItem.getItemId());
    }

    @Test
    void addItem_NewItem_ReturnsAddedItem() {
        Item newItem = new Item("New Item", "Description", 15.0, 5);
        Item addedItem = itemRepository.save(newItem);

        assertNotNull(addedItem.getItemId());
        assertEquals(newItem.getName(), addedItem.getName());
        assertEquals(newItem.getDescription(), addedItem.getDescription());
        assertEquals(newItem.getPrice(), addedItem.getPrice());
        assertEquals(newItem.getAmountInStock(), addedItem.getAmountInStock());
    }

    @Test
    void getAllItems_ReturnsAllItemsInRepository() {
        List<Item> allItems = itemRepository.findAll();
        assertNotNull(allItems);
        assertNotNull(allItems);
    }
}
