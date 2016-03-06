package com.example.levent_j.dotamin_.pojo;

import java.util.List;

/**
 * Created by levent_j on 16-3-6.
 */
public class MatchResult {

    private List<MatchPlayer> players;
    private boolean radiant_win;
    private int duration;
    private int startTime;
    private String match_id;
    private int matchSeqNum;
    private int towerStatusRadiant;
    private int towerStatusDire;
    private int barracksStatusRadiant;
    private int barracksStatusDire;
    private int cluster;
    private int firstBloodTime;
    private int lobbyType;
    private int humanPlayers;
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


    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getDuration() {
        return duration;
    }


    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
    public int getStartTime() {
        return startTime;
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

    public void setMatchSeqNum(int matchSeqNum) {
        this.matchSeqNum = matchSeqNum;
    }
    public int getMatchSeqNum() {
        return matchSeqNum;
    }


    public void setTowerStatusRadiant(int towerStatusRadiant) {
        this.towerStatusRadiant = towerStatusRadiant;
    }
    public int getTowerStatusRadiant() {
        return towerStatusRadiant;
    }


    public void setTowerStatusDire(int towerStatusDire) {
        this.towerStatusDire = towerStatusDire;
    }
    public int getTowerStatusDire() {
        return towerStatusDire;
    }


    public void setBarracksStatusRadiant(int barracksStatusRadiant) {
        this.barracksStatusRadiant = barracksStatusRadiant;
    }
    public int getBarracksStatusRadiant() {
        return barracksStatusRadiant;
    }


    public void setBarracksStatusDire(int barracksStatusDire) {
        this.barracksStatusDire = barracksStatusDire;
    }
    public int getBarracksStatusDire() {
        return barracksStatusDire;
    }


    public void setCluster(int cluster) {
        this.cluster = cluster;
    }
    public int getCluster() {
        return cluster;
    }


    public void setFirstBloodTime(int firstBloodTime) {
        this.firstBloodTime = firstBloodTime;
    }
    public int getFirstBloodTime() {
        return firstBloodTime;
    }


    public void setLobbyType(int lobbyType) {
        this.lobbyType = lobbyType;
    }
    public int getLobbyType() {
        return lobbyType;
    }


    public void setHumanPlayers(int humanPlayers) {
        this.humanPlayers = humanPlayers;
    }
    public int getHumanPlayers() {
        return humanPlayers;
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
