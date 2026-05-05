package ch.samt.tournament.service;

import ch.samt.tournament.data.PlayerRepository;
import ch.samt.tournament.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
    public List<Player> getAllNotDeletedPlayers() {
        return playerRepository.findByIsdeletedFalse();
    }
    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    public Player getPlayerById(int id) {
        return playerRepository.findById(id).get();
    }
}
