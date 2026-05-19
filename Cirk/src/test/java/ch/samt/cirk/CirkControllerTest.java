package ch.samt.cirk;

import ch.samt.cirk.data.ActorRepository;
import ch.samt.cirk.domain.Actor;
import ch.samt.cirk.domain.Director;
import ch.samt.cirk.domain.Movie;
import ch.samt.cirk.service.ActorService;
import ch.samt.cirk.service.DirectorService;
import ch.samt.cirk.service.MovieService;
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
class CirkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ActorService actorService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private MovieService movieService;

    @Test
    public void testLoadMovies() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(view().name("movieList"))
                .andExpect(model().attribute("movies", movieService.getAllMovies()))
                .andExpect(model().attribute("movies", hasSize(3)))
                .andExpect(model().attribute("movies", hasItem(hasProperty("title", is("Inception")))))
                .andExpect(model().attribute("movies", hasItem(hasProperty("actors", is(hasItem(hasProperty("name", is("Leonardo"))))))));
    }

    @Test
    public void testLoadInsertPage() throws Exception {
        mockMvc.perform(get("/movies/insert"))
                .andExpect(status().isOk())
                .andExpect(view().name("insertMovie"))
                .andExpect(model().attributeExists("movie"));
    }

    @Test
    public void testSaveActor_Success() throws Exception {
        mockMvc.perform(post("/movies/actor/insert")
                        .param("name", "Ela")
                        .param("surname", "Desa"))
                .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/movies"));
        Actor savedActor = actorService.findBySurnameIgnoreCase("Desa");
        assert savedActor != null;
        assert savedActor.getName().equals("Ela");
    }

    @Test
    public void testSaveDirector_Success() throws Exception {
        mockMvc.perform(post("/movies/director/insert")
                        .param("name", "Eknulo")
                        .param("surname", "Despamina"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));
        Director savedDirector = directorService.findBySurnameIgnoreCase("Despamina");
        assert savedDirector != null;
        assert savedDirector.getName().equals("Eknulo");
    }

    @Test
    public void testSaveMovie_Success() throws Exception {

        Director director = directorService.findBySurnameIgnoreCase("Nolan");
        Actor actor1 = actorService.findBySurnameIgnoreCase("DiCaprio");
        Actor actor2 = actorService.findBySurnameIgnoreCase("Gordon-Levitt");

        mockMvc.perform(post("/movies/insert")
                        .param("title", "Inception")
                        .param("genre", "Sci-Fi")
                        .param("director", director.getId().toString())
                        .param("actors", actor1.getId().toString())
                        .param("actors", actor2.getId().toString())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));

        Movie savedMovie = movieService.findByTitleIgnoreCase("Inception").getFirst();
        assert savedMovie != null;
        assert savedMovie.getGenre().equals("Sci-Fi");
        assert savedMovie.getDirector().getSurname().equals("Nolan");
        assert savedMovie.getActors().size() == 2;
    }
}