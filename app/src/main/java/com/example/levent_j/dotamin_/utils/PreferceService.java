package com.example.levent_j.dotamin_.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by levent_j on 16-3-31.
 */
public class PreferceService {
    private Context context;
    public final static String KEY = "steamId";
    public  final static String SHARE = "backup";
    public PreferceService(Context context){
        super();
        this.context = context;
    }

    public void save(String key,String value){
        SharedPreferences preferences = context.getSharedPreferences(SHARE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY, value);
        editor.commit();
        Log.d("save","save ok");
    }

    public Map<String,String> getPreferences(){
        SharedPreferences preferences = context.getSharedPreferences(SHARE,Context.MODE_PRIVATE);
        Map<String,String> params = new HashMap<String,String>();
        params.put(KEY,preferences.getString(KEY,"null"));
        return params;
    }
}
