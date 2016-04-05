package com.example.levent_j.dotamin_.model;

import java.util.List;

/**
 * Created by levent_j on 16-3-11.
 */
public class ItemResult {
    private List<Items> items;
    private String status;

    public void setItmes(List<Items> itmes) {
        this.items = itmes;
    }

    public List<Items> getItmes() {
        return items;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
