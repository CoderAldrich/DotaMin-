package com.example.levent_j.dotamin_.pojo;

import java.util.List;

/**
 * Created by levent_j on 16-3-6.
 */
public class MatchResult {

    private List<MatchPlayer> players;
    private boolean radiant_win;
    private String duration;
    private int start_time;
    private String match_id;
    private int match_seq_num;
    private int tower_status_radiant;
    private int tower_status_dire;
    private int barracks_status_radiant;
    private int barracks_status_dire;
    private int cluster;
    private int first_blood_time;
    private int lobby_type;
    private int human_players;
    private int leagueid;
    private int positiveVotes;
    private int negativeVotes;
    private int gameMode;
    private int flags;
    private int engine;


    public void setPlayers(List<MatchPlayer> players) {
        this.players = players;
    }
    public List<MatchPlayer> getPlayers() {
        return players;
    }


    public void setRadiantWin(boolean radiantWin) {
        this.radiant_win = radiantWin;
    }


    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getDuration() {
        return duration;
    }


    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }
    public int getStart_time() {
        return start_time;
    }

    public boolean isRadiantWin() {
        return radiant_win;
    }

    public String getMatchId() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public void setMatch_seq_num(int match_seq_num) {
        this.match_seq_num = match_seq_num;
    }
    public int getMatch_seq_num() {
        return match_seq_num;
    }


    public void setTower_status_radiant(int tower_status_radiant) {
        this.tower_status_radiant = tower_status_radiant;
    }
    public int getTower_status_radiant() {
        return tower_status_radiant;
    }


    public void setTower_status_dire(int tower_status_dire) {
        this.tower_status_dire = tower_status_dire;
    }
    public int getTower_status_dire() {
        return tower_status_dire;
    }


    public void setBarracks_status_radiant(int barracks_status_radiant) {
        this.barracks_status_radiant = barracks_status_radiant;
    }
    public int getBarracks_status_radiant() {
        return barracks_status_radiant;
    }


    public void setBarracks_status_dire(int barracks_status_dire) {
        this.barracks_status_dire = barracks_status_dire;
    }
    public int getBarracks_status_dire() {
        return barracks_status_dire;
    }


    public void setCluster(int cluster) {
        this.cluster = cluster;
    }
    public int getCluster() {
        return cluster;
    }


    public void setFirst_blood_time(int first_blood_time) {
        this.first_blood_time = first_blood_time;
    }
    public int getFirst_blood_time() {
        return first_blood_time;
    }


    public void setLobbyType(int lobbyType) {
        this.lobby_type = lobbyType;
    }
    public int getLobbyType() {
        return lobby_type;
    }


    public void setHuman_players(int human_players) {
        this.human_players = human_players;
    }
    public int getHuman_players() {
        return human_players;
    }


    public void setLeagueid(int leagueid) {
        this.leagueid = leagueid;
    }
    public int getLeagueid() {
        return leagueid;
    }


    public void setPositiveVotes(int positiveVotes) {
        this.positiveVotes = positiveVotes;
    }
    public int getPositiveVotes() {
        return positiveVotes;
    }


    public void setNegativeVotes(int negativeVotes) {
        this.negativeVotes = negativeVotes;
    }
    public int getNegativeVotes() {
        return negativeVotes;
    }


    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }
    public int getGameMode() {
        return gameMode;
    }


    public void setFlags(int flags) {
        this.flags = flags;
    }
    public int getFlags() {
        return flags;
    }


    public void setEngine(int engine) {
        this.engine = engine;
    }
    public int getEngine() {
        return engine;
    }
}
