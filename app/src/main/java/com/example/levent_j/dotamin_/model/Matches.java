package com.example.levent_j.dotamin_.model;

import java.util.List;

/**
 * Created by levent_j on 16-3-5.
 */
public class Matches {
    private String match_id;
    private String match_seq_num;
    private String start_time;
    private int lobby_type;
    private int radiant_team_id;
    private int dire_team_id;
    private List<Players> players;


    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }
    public String getMatch_id() {
        return match_id;
    }


    public void setMatch_seq_num(String match_seq_num) {
        this.match_seq_num = match_seq_num;
    }
    public String getMatch_seq_num() {
        return match_seq_num;
    }


    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
    public String getStart_time() {
        return start_time;
    }


    public void setLobby_type(int lobby_type) {
        this.lobby_type = lobby_type;
    }
    public int getLobby_type() {
        return lobby_type;
    }


    public void setRadiant_team_id(int radiant_team_id) {
        this.radiant_team_id = radiant_team_id;
    }
    public int getRadiant_team_id() {
        return radiant_team_id;
    }


    public void setDire_team_id(int dire_team_id) {
        this.dire_team_id = dire_team_id;
    }
    public int getDire_team_id() {
        return dire_team_id;
    }


    public void setPlayers(List<Players> players) {
        this.players = players;
    }
    public List<Players> getPlayers() {
        return players;
    }
}
