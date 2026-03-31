package ch.samt.gardenwarehouse;

import ch.samt.gardenwarehouse.data.ItemRepository;
import ch.samt.gardenwarehouse.domain.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ItemContollerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;


    @Test
    void loadItems() throws Exception {
        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(view().name("itemList"))
                .andExpect(model().attribute("items", itemRepository.findAll()))
                .andExpect(model().attribute("items", hasSize(3)))
                .andExpect(model().attribute("items", hasItem(hasProperty("name", is("Quan")))));
    }

    @Test
    void loadItemByCode() throws Exception {
        mockMvc.perform(get("/items/faq-67"))
                .andExpect(status().isOk())
                .andExpect(view().name("itemList"))
                .andExpect(model().attributeExists("items"))
                .andExpect(model().attribute("items", itemRepository.findByCodeIgnoreCase("faq-67")));
    }

    @Test
    void sellItem() throws Exception {
        mockMvc.perform(get("/items/sell?code=faq-67"))
                .andExpect(status().isOk())
                .andExpect(view().name("itemList"));
        Item savedItem = itemRepository.findByCodeIgnoreCase("faq-67").getFirst();
        assert savedItem.getItemcount() == 1232;
    }

    @Test
    void addItem() throws Exception {
        mockMvc.perform(get("/items/add?code=faq-67&number=10"))
                .andExpect(status().isOk())
                .andExpect(view().name("itemList"));
        Item savedItem = itemRepository.findByCodeIgnoreCase("faq-67").getFirst();
        assert savedItem.getItemcount() == 1243;
    }

    @Test
    void loadInsertPage() throws Exception {
        mockMvc.perform(get("/items/insert"))
                .andExpect(status().isOk())
                .andExpect(view().name("insertItem"))
                .andExpect(model().attributeExists("item"));
    }

    @Test
    void saveItems() throws Exception {
        mockMvc.perform(post("/items/insert")
                        .param("code", "jak-12")
                        .param("type", "Plebeo")
                        .param("name", "Quan True")
                        .param("price", "0.99")
                        .param("itemcount", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/items"));

        Item savedItem = itemRepository.findByCodeIgnoreCase("jak-12").getFirst();
        assert savedItem != null;
        assert savedItem.getName().equals("Quan True");
        assert savedItem.getPrice() == 0.99;
    }
}