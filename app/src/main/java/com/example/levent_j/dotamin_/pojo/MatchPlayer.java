package com.example.levent_j.dotamin_.pojo;

import java.util.List;

/**
 * Created by levent_j on 16-3-6.
 */
public class MatchPlayer {
    private String account_id;
    private int player_slot;
    private int hero_id;
    private int item_0;
    private int item_1;
    private int item_2;
    private int item_3;
    private int item_4;
    private int item_5;
    private int kills;
    private int deaths;
    private int assists;
    private int leaverStatus;
    private int last_hits;
    private int denies;
    private int gold_per_min;
    private int xp_per_min;
    private int level;
    private int gold;
    private int goldSpent;
    private int hero_damage;
    private int tower_damage;
    private int hero_healing;
    private List<AbilityUpgrades> abilityUpgrades;


    public void setAccountId(String accountId) {
        this.account_id = accountId;
    }
    public String getAccountId() {
        return account_id;
    }


    public void setPlayerSlot(int playerSlot) {
        this.player_slot = playerSlot;
    }
    public int getPlayerSlot() {
        return player_slot;
    }


    public void setHero_id(int hero_id) {
        this.hero_id = hero_id;
    }
    public int getHero_id() {
        return hero_id;
    }


    public void setItem0(int item0) {
        this.item_0 = item0;
    }
    public int getItem0() {
        return item_0;
    }


    public void setItem1(int item1) {
        this.item_1 = item1;
    }
    public int getItem1() {
        return item_1;
    }


    public void setItem2(int item2) {
        this.item_2 = item2;
    }
    public int getItem2() {
        return item_2;
    }


    public void setItem_3(int item_3) {
        this.item_3 = item_3;
    }
    public int getItem_3() {
        return item_3;
    }


    public void setItem_4(int item_4) {
        this.item_4 = item_4;
    }
    public int getItem_4() {
        return item_4;
    }


    public void setItem_5(int item_5) {
        this.item_5 = item_5;
    }
    public int getItem_5() {
        return item_5;
    }


    public void setKills(int kills) {
        this.kills = kills;
    }
    public int getKills() {
        return kills;
    }


    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
    public int getDeaths() {
        return deaths;
    }


    public void setAssists(int assists) {
        this.assists = assists;
    }
    public int getAssists() {
        return assists;
    }


    public void setLeaverStatus(int leaverStatus) {
        this.leaverStatus = leaverStatus;
    }
    public int getLeaverStatus() {
        return leaverStatus;
    }


    public void setLast_hits(int last_hits) {
        this.last_hits = last_hits;
    }
    public int getLast_hits() {
        return last_hits;
    }


    public void setDenies(int denies) {
        this.denies = denies;
    }
    public int getDenies() {
        return denies;
    }


    public void setGold_per_min(int gold_per_min) {
        this.gold_per_min = gold_per_min;
    }
    public int getGold_per_min() {
        return gold_per_min;
    }


    public void setXp_per_min(int xp_per_min) {
        this.xp_per_min = xp_per_min;
    }
    public int getXp_per_min() {
        return xp_per_min;
    }


    public void setLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }


    public void setGold(int gold) {
        this.gold = gold;
    }
    public int getGold() {
        return gold;
    }


    public void setGoldSpent(int goldSpent) {
        this.goldSpent = goldSpent;
    }
    public int getGoldSpent() {
        return goldSpent;
    }


    public void setHero_damage(int hero_damage) {
        this.hero_damage = hero_damage;
    }
    public int getHero_damage() {
        return hero_damage;
    }


    public void setTower_damage(int tower_damage) {
        this.tower_damage = tower_damage;
    }
    public int getTower_damage() {
        return tower_damage;
    }


    public void setHero_healing(int hero_healing) {
        this.hero_healing = hero_healing;
    }
    public int getHero_healing() {
        return hero_healing;
    }


    public void setAbilityUpgrades(List<AbilityUpgrades> abilityUpgrades) {
        this.abilityUpgrades = abilityUpgrades;
    }
    public List<AbilityUpgrades> getAbilityUpgrades() {
        return abilityUpgrades;
    }
}
