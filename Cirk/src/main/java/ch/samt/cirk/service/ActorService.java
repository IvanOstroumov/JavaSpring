package ch.samt.cirk.service;

import ch.samt.cirk.data.ActorRepository;
import ch.samt.cirk.domain.Actor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ActorService{
    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAllActors(){
        return actorRepository.findAll();
    }
    public Actor getActorById(long id){
        return actorRepository.getById(id);
    }
    public void save(@Valid Actor actor){
        try{
            actorRepository.save(actor);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public Actor findBySurnameIgnoreCase(String surname) {
        return actorRepository.findBySurnameIgnoreCase(surname);
    }
}
