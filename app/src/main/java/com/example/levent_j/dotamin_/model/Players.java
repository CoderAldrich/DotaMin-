package com.example.levent_j.dotamin_.model;

/**
 * Created by levent_j on 16-3-5.
 */
public class Players {
    private long account_id;
    private int player_slot;
    private int hero_id;


    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }
    public long getAccount_id() {
        return account_id;
    }


    public void setPlayer_slot(int player_slot) {
        this.player_slot = player_slot;
    }
    public int getPlayer_slot() {
        return player_slot;
    }


    public void setHero_id(int hero_id) {
        this.hero_id = hero_id;
    }
    public int getHero_id() {
        return hero_id;
    }
}
