package ch.samt.cirk.service;

import ch.samt.cirk.data.ActorRepository;
import ch.samt.cirk.data.DirectorRepository;
import ch.samt.cirk.domain.Actor;
import ch.samt.cirk.domain.Director;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }
    public Director getDirectorById(long id){
        return directorRepository.getById(id);
    }
    public void save(@Valid Director director) {
        try{
            directorRepository.save(director);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public Director findBySurnameIgnoreCase(String surname) {
        return directorRepository.findBySurnameIgnoreCase(surname);
    }
}
