package ch.samt.tournament.controller;

import ch.samt.tournament.domain.GameProfile;
import ch.samt.tournament.domain.Player;
import ch.samt.tournament.domain.Team;
import ch.samt.tournament.service.PlayerService;
import ch.samt.tournament.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tournament")
@Controller
public class TournamentController {
    private final PlayerService playerService;
    private final TeamService teamService;

    @Autowired
    public TournamentController(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping
    public String loadPlayers(Model model) {
        model.addAttribute("players", playerService.getAllNotDeletedPlayers());
        return "playerList";
    }

    @GetMapping("/insert")
    public String loadInsertPage(@ModelAttribute Player player, Model model) {
        player.setGameProfile(new GameProfile());
        model.addAttribute("teams", teamService.getAllNotDeletedTeams());
        return "insertPlayer";
    }

    @PostMapping("/insert")
    public String savePlayers(@Valid Player player, Errors errors) {
        if (errors.hasErrors()) {
            return "insertPlayer";
        }
        player.setIsdeleted(false);
        playerService.savePlayer(player);
        return "redirect:/tournament";
    }

    @GetMapping("/teams")
    public String loadTeams(Model model) {
        model.addAttribute("teams", teamService.getAllNotDeletedTeams());
        return "teamList";
    }

    @GetMapping("/teams/insert")
    public String loadInsertTeamPage(@ModelAttribute Team team, Model model) {
        return "insertTeam";
    }

    @PostMapping("/teams/insert")
    public String saveTeams(@Valid Team team, Errors errors) {
        if (errors.hasErrors()) {
            return "insertTeam";
        }
        team.setIsdeleted(false);
        teamService.save(team);
        return "redirect:/tournament/teams";
    }

    @GetMapping("/delete/{playerToDelete}")
    public String deletePlayer(@PathVariable int playerToDelete) {
         Player player = playerService.getPlayerById(playerToDelete);
         player.setIsdeleted(true);
         playerService.savePlayer(player);
         return "redirect:/tournament";
    }

    @GetMapping("/teams/delete/{teamToDelete}")
    public String deleteTeam(@PathVariable int teamToDelete) {
        Team team = teamService.getTeamById(teamToDelete);
        team.setIsdeleted(true);
        teamService.save(team);
        return "redirect:/tournament/teams";
    }
}