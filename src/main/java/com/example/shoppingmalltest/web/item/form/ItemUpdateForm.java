package com.example.shoppingmalltest.web.item.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemUpdateForm {

    @NotNull
    private Long id;

    @NotBlank
    private String itemName;

    private Integer quantity;

    @NotNull
    @Range(min = 1)
    private Integer itemPrice;

}
