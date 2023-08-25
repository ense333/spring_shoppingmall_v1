package com.example.shoppingmalltest.domain.item;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Item {
    private Long id;
    @NotNull
    private String itemName;
    private Integer quantity;
    private Integer itemPrice;

    public Item(){}

    public Item(String itemName, Integer quantity, Integer itemPrice){
        this.itemName = itemName;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

}
