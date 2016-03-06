package com.example.levent_j.dotamin_.pojo;

import java.util.List;

/**
 * Created by levent_j on 16-3-6.
 */
public class MatchPlayer {
    private String account_id;
    private int player_slot;
    private int heroId;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int kills;
    private int deaths;
    private int assists;
    private int leaverStatus;
    private int lastHits;
    private int denies;
    private int goldPerMin;
    private int xpPerMin;
    private int level;
    private int gold;
    private int goldSpent;
    private int heroDamage;
    private int towerDamage;
    private int heroHealing;
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


    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }
    public int getHeroId() {
        return heroId;
    }


    public void setItem0(int item0) {
        this.item0 = item0;
    }
    public int getItem0() {
        return item0;
    }


    public void setItem1(int item1) {
        this.item1 = item1;
    }
    public int getItem1() {
        return item1;
    }


    public void setItem2(int item2) {
        this.item2 = item2;
    }
    public int getItem2() {
        return item2;
    }


    public void setItem3(int item3) {
        this.item3 = item3;
    }
    public int getItem3() {
        return item3;
    }


    public void setItem4(int item4) {
        this.item4 = item4;
    }
    public int getItem4() {
        return item4;
    }


    public void setItem5(int item5) {
        this.item5 = item5;
    }
    public int getItem5() {
        return item5;
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


    public void setLastHits(int lastHits) {
        this.lastHits = lastHits;
    }
    public int getLastHits() {
        return lastHits;
    }


    public void setDenies(int denies) {
        this.denies = denies;
    }
    public int getDenies() {
        return denies;
    }


    public void setGoldPerMin(int goldPerMin) {
        this.goldPerMin = goldPerMin;
    }
    public int getGoldPerMin() {
        return goldPerMin;
    }


    public void setXpPerMin(int xpPerMin) {
        this.xpPerMin = xpPerMin;
    }
    public int getXpPerMin() {
        return xpPerMin;
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


    public void setHeroDamage(int heroDamage) {
        this.heroDamage = heroDamage;
    }
    public int getHeroDamage() {
        return heroDamage;
    }


    public void setTowerDamage(int towerDamage) {
        this.towerDamage = towerDamage;
    }
    public int getTowerDamage() {
        return towerDamage;
    }


    public void setHeroHealing(int heroHealing) {
        this.heroHealing = heroHealing;
    }
    public int getHeroHealing() {
        return heroHealing;
    }


    public void setAbilityUpgrades(List<AbilityUpgrades> abilityUpgrades) {
        this.abilityUpgrades = abilityUpgrades;
    }
    public List<AbilityUpgrades> getAbilityUpgrades() {
        return abilityUpgrades;
    }
}
