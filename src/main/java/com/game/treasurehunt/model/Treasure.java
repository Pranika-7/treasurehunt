package com.game.treasurehunt.model;
import jakarta.persistence.*;
@Entity
public class Treasure {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String locationCode;
    private int points;
    private boolean isCollected;
    private Long collectedByPlayerId;
    public Treasure() {}
    public Treasure(String locationCode, int points) {
        this.locationCode = locationCode; this.points = points; this.isCollected = false;
    }
    public Long getId() { return id; }
    public String getLocationCode() { return locationCode; }
    public int getPoints() { return points; }
    public boolean isCollected() { return isCollected; }
    public Long getCollectedByPlayerId() { return collectedByPlayerId; }
}
