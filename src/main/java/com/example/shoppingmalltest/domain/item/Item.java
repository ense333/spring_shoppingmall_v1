package com.example.shoppingmalltest.domain.item;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Item {
    private Long id;
    @NotNull
    private String itemName;
    private Integer itemPrice;

}
