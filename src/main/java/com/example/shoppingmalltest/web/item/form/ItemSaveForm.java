package com.example.shoppingmalltest.web.item.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemSaveForm {

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1)
    private Integer quantity;

    @NotNull
    @Range(min = 100)
    private Integer price;

}
