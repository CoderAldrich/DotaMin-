package com.example.levent_j.dotamin_.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.levent_j.dotamin_.utils.Heroes;
import com.parse.Parse;
import com.parse.ParseObject;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init();
        setListener();
//
//        Parse.enableLocalDatastore(this);
//
//        Parse.initialize(this);

//
//        for (int i=0;i< Heroes.HERO_NAME.length;i++){
//            ParseObject testObject = new ParseObject("Hero");
//            testObject.put("heroName",Heroes.HERO_NAME[i]);
//            testObject.saveInBackground();
//            Log.d("Parse",Heroes.HERO_NAME[i]);
//        }


    }

    protected abstract void init();

    protected abstract int getLayoutId();

    protected abstract void setListener();

    protected void msg(String tag,String s){
        Log.d(tag, s);
    }
}
