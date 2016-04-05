package com.example.levent_j.dotamin_.model;

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
    private String itemUrl_1;
    private String itemUrl_2;
    private String itemUrl_3;
    private String itemUrl_4;
    private String itemUrl_5;
    private String itemUrl_6;

    public void setItemUrl_1(String itemUrl_1) {
        this.itemUrl_1 = itemUrl_1;
    }

    public String getItemUrl_1() {
        return itemUrl_1;
    }

    public void setItemUrl_2(String itemUrl_2) {
        this.itemUrl_2 = itemUrl_2;
    }

    public String getItemUrl_2() {
        return itemUrl_2;
    }

    public void setItemUrl_3(String itemUrl_3) {
        this.itemUrl_3 = itemUrl_3;
    }

    public String getItemUrl_3() {
        return itemUrl_3;
    }

    public void setItemUrl_4(String itemUrl_4) {
        this.itemUrl_4 = itemUrl_4;
    }

    public String getItemUrl_4() {
        return itemUrl_4;
    }

    public void setItemUrl_5(String itemUrl_5) {
        this.itemUrl_5 = itemUrl_5;
    }

    public String getItemUrl_5() {
        return itemUrl_5;
    }

    public void setItemUrl_6(String itemUrl_6) {
        this.itemUrl_6 = itemUrl_6;
    }

    public String getItemUrl_6() {
        return itemUrl_6;
    }

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
