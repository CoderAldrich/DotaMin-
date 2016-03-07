package com.example.levent_j.dotamin_.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by levent_j on 16-3-6.
 */
public class Match implements Parcelable{
    private MatchResult result;


    protected Match(Parcel in) {
        result = in.readParcelable(MatchResult.class.getClassLoader());
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    public void setResult(MatchResult result) {
        this.result = result;
    }
    public MatchResult getResult() {
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
