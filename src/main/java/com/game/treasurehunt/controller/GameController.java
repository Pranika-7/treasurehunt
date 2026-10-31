package com.game.treasurehunt.controller;
import com.game.treasurehunt.model.*;
import com.game.treasurehunt.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {
    private final GameService gameService;
    public GameController(GameService gameService) { this.gameService = gameService; }
    
    @PostMapping("/players")
    public ResponseEntity<Player> registerPlayer(@RequestParam String name) {
        return ResponseEntity.ok(gameService.registerPlayer(name));
    }
    @PostMapping("/game/start")
    public ResponseEntity<String> startGame() {
        gameService.startNewGame();
        return ResponseEntity.ok("Game started. Treasures have been spawned.");
    }
    @GetMapping("/game/map")
    public ResponseEntity<List<Treasure>> getMap() {
        return ResponseEntity.ok(gameService.getMap());
    }
    @PostMapping("/game/collect/{treasureId}")
    public ResponseEntity<String> collectTreasure(@PathVariable Long treasureId, @RequestParam Long playerId) {
        return ResponseEntity.ok(gameService.collectTreasure(treasureId, playerId));
    }
    @GetMapping("/leaderboard")
    public ResponseEntity<List<Player>> getLeaderboard() {
        return ResponseEntity.ok(gameService.getLeaderboard());
    }
    @GetMapping("/players/{playerId}/score")
    public ResponseEntity<Integer> getPlayerScore(@PathVariable Long playerId) {
        return ResponseEntity.ok(gameService.getPlayerScore(playerId).getScore());
    }
}
