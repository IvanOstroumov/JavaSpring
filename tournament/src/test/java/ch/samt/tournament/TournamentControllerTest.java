package ch.samt.tournament;

import ch.samt.tournament.data.PlayerRepository;
import ch.samt.tournament.data.TeamRepository;
import ch.samt.tournament.domain.Player;
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
class TournamentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private TeamRepository teamRepo;

    @Test
    void loadPlayers() throws Exception {
        mockMvc.perform(get("/tournament"))
                .andExpect(status().isOk())
                .andExpect(view().name("playerList"))
                .andExpect(model().attribute("players", playerRepo.findByIsdeletedFalse()))
                .andExpect(model().attribute("players", hasSize(3)))
                .andExpect(model().attribute("players", hasItem(hasProperty("name", is("Ken")))));
    }

    @Test
    void savePlayers() throws Exception {
        mockMvc.perform(post("/tournament/insert")
                        .param("name", "Sanya")
                        .param("surname", "Kostilev")
                        .param("age", "29")
                        .param("gender", "Male")
                        .param("race", "White")
                        .param("city", "Lviv")
                        .param("team", "NaVi")
                        .param("nickname", "impa_10t"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tournament"));

        Player savedPlayer = playerRepo.findByName("Sanya");
        assert savedPlayer != null;
        assert savedPlayer.getName().equals("Sanya");
    }

    @Test
    void loadTeams() throws Exception {
    }

    @Test
    void saveTeams() throws Exception {
    }

    @Test
    void deletePlayer() throws Exception {
    }
}