package com.example.levent_j.dotamin_.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init();
        setListener();
    }

    protected abstract void init();

    protected abstract int getLayoutId();

    protected abstract void setListener();

    protected void msg(String tag,String s){
        Log.d(tag, s);
    }
}
