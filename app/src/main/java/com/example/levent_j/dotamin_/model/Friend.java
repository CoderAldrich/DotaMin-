package com.example.levent_j.dotamin_.model;

/**
 * Created by levent_j on 16-3-4.
 */
public class Friend {
    private String steamid;
    private String relationship;
    private long friendSince;


    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }
    public String getSteamid() {
        return steamid;
    }


    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    public String getRelationship() {
        return relationship;
    }


    public void setFriendSince(long friendSince) {
        this.friendSince = friendSince;
    }
    public long getFriendSince() {
        return friendSince;
    }
}
