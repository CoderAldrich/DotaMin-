package com.example.levent_j.dotamin_;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by levent_j on 16-3-15.
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //与parse取得链接
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
    }
}
