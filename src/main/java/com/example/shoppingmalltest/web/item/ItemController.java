package com.example.shoppingmalltest.web.item;

import com.example.shoppingmalltest.domain.item.Item;
import com.example.shoppingmalltest.domain.item.ItemRepository;
import com.example.shoppingmalltest.repository.ItemSearchCond;
import com.example.shoppingmalltest.service.ItemService;
import com.example.shoppingmalltest.web.item.form.ItemSaveForm;
import com.example.shoppingmalltest.web.item.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String items(@ModelAttribute("itemSearch")ItemSearchCond itemSearch, Model model){
        List<Item> items = itemService.findItems(itemSearch);
        model.addAttribute("items", items);
        return "items/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemService.findById(itemId).get();
        model.addAttribute("item", item);
        return "items/item";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("item", new Item());
        return "items/addForm";
    }

    /*
    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemService.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }*/
    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item")ItemSaveForm form,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes){
        //특정 필드 예외가 아닌 전체 예외의 경우
        //가격 * 수량의 합 > 10000 이상이어야 함
        if(form.getPrice() != null && form.getQuantity() != null){
            int resultPrice = form.getPrice() * form.getQuantity();
            if(resultPrice < 10000){
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "items/addForm";
        }

        //성공로직
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());

        Item savedItem = itemService.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemService.findById(itemId).get();
        model.addAttribute("item", item);
        return "items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,
                       @Validated @ModelAttribute("item")ItemUpdateForm form, BindingResult bindingResult){
        //특정 필드 예외가 아닌 전체 예외
        if(form.getPrice() != null && form.getQuantity() != null){
            int resultPrice = form.getPrice() * form.getQuantity();
            if(resultPrice < 10000){
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "items/editForm";
        }

        ItemUpdateForm itemParam = new ItemUpdateForm();
        itemParam.setItemName(form.getItemName());
        itemParam.setPrice(form.getPrice());
        itemParam.setQuantity(form.getQuantity());

        itemService.update(itemId, itemParam);
        return "redirect:/items/{itemId}";
    }
}
