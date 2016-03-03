package com.example.levent_j.dotamin_.utils;

/**
 * Created by levent_j on 16-3-3.
 */
public class Util {
    public static String get64Id(long id){
        long add = 76561197960265728L;
        return String.valueOf(id+add);
    }
}
