package ch.samt.tournament.service;

import ch.samt.tournament.data.TeamRepository;
import ch.samt.tournament.domain.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public void save(Team team) {
        teamRepository.save(team);
    }

    public Team getTeamById(int id) {
        return teamRepository.findById(id).get();
    }
    public List<Team> getAllNotDeletedTeams() {
        return teamRepository.findByIsdeletedFalse();
    }
}
