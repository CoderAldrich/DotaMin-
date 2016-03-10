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
        return String.valueOf(id - add);
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
        if (slot<5){
            return true;
        }else {
            return false;
        }
    }

    public static String Second2Minute(int second){
        int minute = second/60;
        return String.valueOf(minute);
    }

    public static String getLobby(int lobby){
        switch (lobby){
            case 0:
                return "公共比赛";
            case 1:
                return "练习赛";
            case 2:
                return "联赛";
            case 3:
                return "教程";
            case 4:
                return "人机对战";
            case 5:
                return "团队比赛";
            case 6:
                return "队列solo";
            case 7:
                return "天梯比赛";
            case 8:
                return "中路solo";
            default:
                return "无效的";
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
