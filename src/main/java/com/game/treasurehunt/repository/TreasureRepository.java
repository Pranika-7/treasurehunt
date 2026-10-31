package com.game.treasurehunt.repository;
import com.game.treasurehunt.model.Treasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface TreasureRepository extends JpaRepository<Treasure, Long> {
    List<Treasure> findByIsCollectedFalse();
    @Modifying
    @Query("UPDATE Treasure t SET t.isCollected = true, t.collectedByPlayerId = :playerId WHERE t.id = :treasureId AND t.isCollected = false")
    int atomicCollectTreasure(@Param("treasureId") Long treasureId, @Param("playerId") Long playerId);
}
