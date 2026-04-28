package ch.samt.musicstore.controller;

import ch.samt.musicstore.data.InstrumentRepository;
import ch.samt.musicstore.domain.Instrument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class InstrumentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InstrumentRepository instrumentRepository;

    @Test
    void loadInstrument() throws Exception {
        mockMvc.perform(get("/instruments?type=guitar&brand=fender"))
                .andExpect(status().isOk())
                .andExpect(view().name("instrumentList"))
                .andExpect(model().attribute("instruments", instrumentRepository.findByTypeIgnoreCaseAndBrandIgnoreCase("guitar", "fender")))
                .andExpect(model().attribute("instruments", hasSize(1)))
                .andExpect(model().attribute("instruments", hasItem(hasProperty("model", is("Stratocaster")))));
    }

    @Test
    void addStock() throws Exception {
        mockMvc.perform(get("/instruments/add/1/10"))
                .andExpect(status().isOk())
                .andExpect(view().name("instrumentList"));
        Instrument savedItem = instrumentRepository.findById(1);
        assert savedItem.getStock() == 13;
    }

}