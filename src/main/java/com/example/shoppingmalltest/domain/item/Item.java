package com.example.shoppingmalltest.domain.item;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Item {
    private Long id;

    private String itemName;
    private Integer quantity;
    private Integer price;

    public Item(){}

    public Item(String itemName, Integer quantity, Integer price){
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

}
