package com.example.levent_j.dotamin_.pojo;

/**
 * Created by levent_j on 16-3-6.
 */
public class HistoryItemBean {
    private String heroName;
    private String heroAvaterUrl;
    private String team;
    private String time;
    private String type;
    private int K;
    private int D;
    private int A;
    private String win;
    private MatchResult result;

    public void setResult(MatchResult result) {
        this.result = result;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroAvaterUrl(String heroAvaterUrl) {
        this.heroAvaterUrl = heroAvaterUrl;
    }

    public String getHeroAvaterUrl() {
        return heroAvaterUrl;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setA(int a) {
        A = a;
    }

    public int getA() {
        return A;
    }

    public void setK(int k) {
        K = k;
    }

    public int getK() {
        return K;
    }

    public void setD(int d) {
        D = d;
    }

    public int getD() {
        return D;
    }

    public String isWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }
}
