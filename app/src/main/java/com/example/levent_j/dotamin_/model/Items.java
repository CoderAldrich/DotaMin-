package com.example.levent_j.dotamin_.model;

/**
 * Created by levent_j on 16-3-11.
 */
public class Items {
    private int id;
    private String name;
    private  int cost;
    private int secret_shop;
    private int side_shop;
    private int recipe;
    private String localized_name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setSecret_shop(int secret_shop) {
        this.secret_shop = secret_shop;
    }

    public int getSecret_shop() {
        return secret_shop;
    }

    public void setSide_shop(int side_shop) {
        this.side_shop = side_shop;
    }

    public int getSide_shop() {
        return side_shop;
    }

    public void setRecipe(int recipe) {
        this.recipe = recipe;
    }

    public int getRecipe() {
        return recipe;
    }

    public void setLocalized_name(String localized_name) {
        this.localized_name = localized_name;
    }

    public String getLocalized_name() {
        return localized_name;
    }
}
