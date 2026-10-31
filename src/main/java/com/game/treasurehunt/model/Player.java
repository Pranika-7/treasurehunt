package com.game.treasurehunt.model;
import jakarta.persistence.*;
@Entity
public class Player {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String name;
    private int score;
    public Player() {}
    public Player(String name) { this.name = name; this.score = 0; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getScore() { return score; }
    public void addScore(int points) { this.score += points; }
}
