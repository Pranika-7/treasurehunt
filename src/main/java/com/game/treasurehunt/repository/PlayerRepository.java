package com.game.treasurehunt.repository;
import com.game.treasurehunt.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByOrderByScoreDesc();
}
