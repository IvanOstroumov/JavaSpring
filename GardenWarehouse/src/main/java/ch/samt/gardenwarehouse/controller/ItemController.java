package ch.samt.gardenwarehouse.controller;

import ch.samt.gardenwarehouse.domain.Item;
import ch.samt.gardenwarehouse.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/items")
@Controller
public class ItemController {
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String loadItems(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "itemList";
    }

    @GetMapping("/{codeToFilter}")
    public String loadItemByCode(Model model, @PathVariable String codeToFilter) {
        List<Item> filteredItems = itemService.findByCode(codeToFilter);
        if(filteredItems.isEmpty()){
            return "error";
        }
        model.addAttribute("items", filteredItems);
        return "itemList";
    }
    @GetMapping("/sell")
    public String sellItem(Model model, @RequestParam(value = "code", required = true) String code) {
        if(itemService.findByCode(code) == null){
            return "error";
        }
        itemService.sellItem(code);
        List<Item> filteredItems = itemService.findByCode(code);
        model.addAttribute("items", filteredItems);
        return "itemList";
    }
    @GetMapping("/add")
    public String addItem(Model model, @RequestParam(value = "code", required = true) String code, @RequestParam(value = "number", required = true ) Integer number) {
        if(itemService.findByCode(code) == null){
            return "error";
        }
        itemService.addItem(code, number);
        List<Item> filteredItems = itemService.findByCode(code);
        model.addAttribute("items", filteredItems);
        return "itemList";
    }

    @GetMapping("/insert")
    public String loadInsertPage(@ModelAttribute Item item) {
        return "insertItem";
    }

    @PostMapping("/insert")
    public String saveItems(@Valid Item item, Errors errors) {
        if (errors.hasErrors()) {
            return "insertItem";
        }
        itemService.save(item);
        return "redirect:/items";
    }
}
