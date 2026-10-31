package com.game.treasurehunt.service;
import com.game.treasurehunt.model.*;
import com.game.treasurehunt.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class GameService {
    private final PlayerRepository playerRepo;
    private final TreasureRepository treasureRepo;

    public GameService(PlayerRepository playerRepo, TreasureRepository treasureRepo) {
        this.playerRepo = playerRepo; this.treasureRepo = treasureRepo;
    }
    public Player registerPlayer(String name) { return playerRepo.save(new Player(name)); }
    
    @Transactional
    public void startNewGame() {
        treasureRepo.deleteAll();
        Random rand = new Random();
        for (int i = 1; i <= 10; i++) {
            treasureRepo.save(new Treasure("LOC-" + i + "-" + rand.nextInt(100), (rand.nextInt(5) + 1) * 10));
        }
    }
    
    public List<Treasure> getMap() { return treasureRepo.findByIsCollectedFalse(); }
    
    @Transactional
    public String collectTreasure(Long treasureId, Long playerId) {
        int rowsUpdated = treasureRepo.atomicCollectTreasure(treasureId, playerId);
        if (rowsUpdated == 1) {
            Treasure treasure = treasureRepo.findById(treasureId).orElseThrow();
            Player player = playerRepo.findById(playerId).orElseThrow();
            player.addScore(treasure.getPoints());
            playerRepo.save(player);
            return "Success: " + treasure.getPoints() + " points added to player " + player.getName();
        }
        return "Failed: Treasure already collected or does not exist.";
    }
    
    public List<Player> getLeaderboard() { return playerRepo.findAllByOrderByScoreDesc(); }
    public Player getPlayerScore(Long playerId) { return playerRepo.findById(playerId).orElseThrow(); }
}
