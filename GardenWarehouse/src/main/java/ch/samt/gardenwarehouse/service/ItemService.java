package ch.samt.gardenwarehouse.service;

import ch.samt.gardenwarehouse.data.ItemRepository;
import ch.samt.gardenwarehouse.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findByCode(String code) {
        return itemRepository.findByCodeIgnoreCase(code);
    }

    public void sellItem(String code) {
        Item item = itemRepository.findByCodeIgnoreCase(code).getFirst();
        item.setItemcount(item.getItemcount() - 1);
        itemRepository.save(item);
    }
    public void save(Item item) {
        itemRepository.save(item);
    }
    public void addItem(String code, int quantity) {
        Item item = itemRepository.findByCodeIgnoreCase(code).getFirst();
        item.setItemcount(item.getItemcount() + quantity);
        itemRepository.save(item);
    }
}
