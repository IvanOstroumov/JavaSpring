package ch.samt.musicstore.service;


import ch.samt.musicstore.data.InstrumentRepository;
import ch.samt.musicstore.domain.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class InstrumentService {
    private InstrumentRepository instrumentRepository;

    public InstrumentService(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    public List<Instrument> findAll() {
        return instrumentRepository.findAll();
    }

    public Instrument findById(long id) {
        return instrumentRepository.findById(id);
    }

    public List<Instrument> findByType(String type) {
        return instrumentRepository.findByTypeIgnoreCase(type);
    }

    public List<Instrument> findByBrand(String brand) {
        return instrumentRepository.findByBrandIgnoreCase(brand);
    }

    public List<Instrument> findByPrice(double price) {
        return instrumentRepository.findByPriceLessThanEqual(price);
    }

    public List<Instrument> findByTypeAndBrand(String type, String brand) {
        return instrumentRepository.findByTypeIgnoreCaseAndBrandIgnoreCase(type, brand);
    }

    public void save(Instrument instrument) {
        instrumentRepository.save(instrument);
    }

    public void sell(Long id) {
        Instrument instrument = findById(id);
        if (instrument.getStock() > 0) {
            instrument.setStock(instrument.getStock() - 1);
            instrumentRepository.save(instrument);
        }
    }
    public void add(Long id, int quantity) {
        Instrument instrument = findById(id);
        instrument.setStock(instrument.getStock() + quantity);
        instrumentRepository.save(instrument);
    }

}
