package com.example.levent_j.dotamin_.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import butterknife.ButterKnife;

/**
 * Created by levent_j on 16-3-1.
 * 这里是所有Activity的父类，封装了基本的方法
 */

public abstract class BaseActivity extends AppCompatActivity {

    private long lastExitTime = 0;


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

    @Override
    public void onBackPressed() {

        if ((System.currentTimeMillis() - lastExitTime) > 3000) {
            Toast.makeText(this,"重复操作退出应用",Toast.LENGTH_SHORT).show();
            lastExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
