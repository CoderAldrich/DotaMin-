package com.example.levent_j.dotamin_.utils;

import android.text.format.DateUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by levent_j on 16-3-3.
 */
public class Util {

    public static String get64Id(long id){
        long add = 76561197960265728L;
        return String.valueOf(id+add);
    }

    public static String get32Id(long id){
        long add = 76561197960265728L;
        return String.valueOf(id-add);
    }

    public static String formDate(long microSecond) {
        SimpleDateFormat sdf = new SimpleDateFormat("创建于 yyyy-MM-dd HH:mm", Locale.CHINA);
        return sdf.format(new Date(1000 * microSecond));
    }


    public static String formRelativeDate(long microSecond) {
        long milliSecond = 1000 * microSecond;
        long now = System.currentTimeMillis();
        CharSequence date;
        if (now - milliSecond >= DateUtils.MINUTE_IN_MILLIS) {
            date = DateUtils.getRelativeTimeSpanString(milliSecond,
                    now,
                    DateUtils.MINUTE_IN_MILLIS,
                    DateUtils.FORMAT_ABBREV_RELATIVE);
        } else {
            date = "刚刚";
        }
        return date.toString();
    }

    public static boolean isRadiant(int slot){
        if (slot<4){
            return true;
        }else {
            return false;
        }
    }

    public static String getState(int personastate) {
        switch (personastate){
            case 0:
                return "离线";
            case 1:
                return "在线";
            case 2:
                return "忙碌";
            case 3:
                return "离开";
            case 4:
                return "打盹";
            case 5:
                return "浏览商店";
            default:
                return "游戏中";
        }
    }
}
