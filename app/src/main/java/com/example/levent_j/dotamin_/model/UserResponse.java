package com.example.levent_j.dotamin_.model;

import java.util.List;

/**
 * Created by levent_j on 16-3-3.
 */
public class UserResponse {
    private List<Player> players;


    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public List<Player> getPlayers() {
        return players;
    }
}
