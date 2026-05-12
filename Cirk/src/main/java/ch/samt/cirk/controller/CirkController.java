package ch.samt.cirk.controller;

import ch.samt.cirk.data.ActorRepository;
import ch.samt.cirk.data.DirectorRepository;
import ch.samt.cirk.data.MovieRepository;
import ch.samt.cirk.domain.Actor;
import ch.samt.cirk.domain.Director;
import ch.samt.cirk.domain.Movie;
import ch.samt.cirk.service.ActorService;
import ch.samt.cirk.service.DirectorService;
import ch.samt.cirk.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class CirkController {
    private final ActorService actorService;
    private final DirectorService directorService;
    private final MovieService movieService;

    @Autowired
    public CirkController(ActorService actorService, DirectorService directorService, MovieService movieService) {
        this.actorService = actorService;
        this.directorService = directorService;
        this.movieService = movieService;
    }

    @GetMapping
    public String loadMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movieList";
    }
    @GetMapping("/insert")
    public String loadInsertPage(@ModelAttribute Movie movie) {
        return "insertMovie";
    }

    @PostMapping("/insert")
    public String saveMovie(@Valid Movie movie, Errors errors) {
        if (errors.hasErrors()) {
            return "insertMovie";
        }
        movieService.save(movie);
        return "redirect:/movies";
    }
    @GetMapping("/actor/insert")
    public String loadInsertActorPage(@ModelAttribute Actor actor) {
        return "insertActor";
    }

    @PostMapping("/actor/insert")
    public String saveActor(@Valid Actor actor, Errors errors) {
        if (errors.hasErrors()) {
            return "insertActor";
        }
        actorService.save(actor);
        return "redirect:/movies";
    }
    @GetMapping("/director/insert")
    public String loadInsertDirectorPage(@ModelAttribute Director director) {
        return "insertDirector";
    }

    @PostMapping("/director/insert")
    public String saveDirector(@Valid Director director, Errors errors) {
        if (errors.hasErrors()) {
            return "insertDirector";
        }
        directorService.save(director);
        return "redirect:/movies";
    }


}
