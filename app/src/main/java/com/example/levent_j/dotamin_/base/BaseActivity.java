package com.example.levent_j.dotamin_.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.levent_j.dotamin_.utils.Heroes;
import com.parse.Parse;
import com.parse.ParseObject;

import butterknife.ButterKnife;

/**
 * Created by levent_j on 16-3-1.
 * 这里是所有Activity的父类，封装了基本的方法
 */

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

    /**封装了设置布局文件的方法
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract void setListener();

    /**封装了Log方法，使用起来更方便了
     * @param tag
     * @param s
     */
    protected void msg(String tag,String s){
        Log.d(tag, s);
    }
}
