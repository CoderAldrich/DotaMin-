package com.example.levent_j.dotamin_.pojo;

import java.util.List;

/**
 * Created by levent_j on 16-3-10.
 */
public class PlayerDetailBean {
    private int hits;
    private int denies;
    private int GPM;
    private int XPM;
    private int heroDamage;
    private int TowerDamage;
    private int Healing;
    private List<Integer> itemID;
    private float fight;
    private float kda;

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getHits() {
        return hits;
    }

    public void setDenies(int denies) {
        this.denies = denies;
    }

    public int getDenies() {
        return denies;
    }

    public void setGPM(int GPM) {
        this.GPM = GPM;
    }

    public int getGPM() {
        return GPM;
    }

    public void setXPM(int XPM) {
        this.XPM = XPM;
    }

    public int getXPM() {
        return XPM;
    }

    public void setHeroDamage(int heroDamage) {
        this.heroDamage = heroDamage;
    }

    public int getHeroDamage() {
        return heroDamage;
    }

    public void setTowerDamage(int towerDamage) {
        TowerDamage = towerDamage;
    }

    public int getTowerDamage() {
        return TowerDamage;
    }

    public void setHealing(int healing) {
        Healing = healing;
    }

    public int getHealing() {
        return Healing;
    }

    public void setFight(float fight) {
        this.fight = fight;
    }

    public float getFight() {
        return fight;
    }

    public void setItemID(List<Integer> itemID) {
        this.itemID = itemID;
    }

    public List<Integer> getItemID() {
        return itemID;
    }

    public void setKda(float kda) {
        this.kda = kda;
    }

    public float getKda() {
        return kda;
    }
}
