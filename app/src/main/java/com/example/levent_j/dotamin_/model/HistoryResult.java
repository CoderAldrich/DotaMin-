package com.example.levent_j.dotamin_.model;

import java.util.List;

/**
 * Created by levent_j on 16-3-5.
 */
public class HistoryResult {
    private int status;
    private int num_results;
    private int total_results;
    private int results_remaining;
    private List<Matches> matches;


    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }


    public void setNumResults(int numResults) {
        this.num_results = numResults;
    }
    public int getNumResults() {
        return num_results;
    }


    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
    public int getTotal_results() {
        return total_results;
    }


    public void setResults_remaining(int results_remaining) {
        this.results_remaining = results_remaining;
    }
    public int getResults_remaining() {
        return results_remaining;
    }


    public void setMatches(List<Matches> matches) {
        this.matches = matches;
    }
    public List<Matches> getMatches() {
        return matches;
    }
}
