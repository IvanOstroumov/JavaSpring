package ch.samt.musicstore.controller;

import ch.samt.musicstore.domain.Instrument;
import ch.samt.musicstore.service.InstrumentService;
import jakarta.validation.Valid;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequestMapping("/instruments")
@Controller
public class InstrumentController {
    private InstrumentService instrumentService;

    @Autowired
    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping("")
    public String loadItems(Model model, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "brand", required = false) String brand) {
        List<Instrument> filteredInstruments = null;
        if(type != null && brand != null) {
            filteredInstruments = instrumentService.findByTypeAndBrand(type, brand);
        } else if (type != null) {
            filteredInstruments = instrumentService.findByType(type);
        } else if (brand != null) {
            filteredInstruments = instrumentService.findByBrand(brand);
        } else {
            filteredInstruments = instrumentService.findAll();
        }
        if(filteredInstruments.isEmpty()) {
            return "error";
        }
        model.addAttribute("instruments", filteredInstruments);
        return "instrumentList";
    }

    @GetMapping("/{priceToFilter}")
    public String loadInstrumentByPrice(Model model, @PathVariable double priceToFilter) {
        List<Instrument> filteredInstruments = instrumentService.findByPrice(priceToFilter);
        if(filteredInstruments.isEmpty()){
            return "error";
        }
        model.addAttribute("instruments", filteredInstruments);
        return "instrumentList";
    }

    @GetMapping("/insert")
    public String loadInsertPage(@ModelAttribute Instrument instrument) {
        return "insertInstrument";
    }

    @PostMapping("/insert")
    public String saveInstruments(@Valid Instrument instrument, Errors errors) {
        if (errors.hasErrors()) {
            return "insertInstrument";
        }
        instrumentService.save(instrument);
        return "redirect:/instruments";
    }

    @GetMapping("/sell/{id}")
    public String sellItem(Model model, @PathVariable Long id) {
        if(instrumentService.findById(id) == null){
            return "error";
        }
        instrumentService.sell(id);
        List<Instrument> filteredInstruments = instrumentService.findAll();
        model.addAttribute("instruments", filteredInstruments);
        return "instrumentList";
    }

    @GetMapping("/add/{id}/{quantity}")
    public String addItem(Model model, @PathVariable Long id, @PathVariable int quantity) {
        if(instrumentService.findById(id) == null){
            return "error";
        }
        instrumentService.add(id, quantity);
        List<Instrument> filteredInstruments = Collections.singletonList(instrumentService.findById(id));
        model.addAttribute("instruments", filteredInstruments);
        return "instrumentList";
    }
}
