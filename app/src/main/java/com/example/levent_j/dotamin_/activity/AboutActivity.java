package com.example.levent_j.dotamin_.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;

/**
 * Created by levent_j on 16-3-20.
 */
public class AboutActivity extends BaseActivity implements View.OnClickListener, GestureDetector.OnGestureListener, View.OnTouchListener {
    @Bind(R.id.fab_gain)
    FloatingActionButton gain;
    @Bind(R.id.layout_lwj)
    LinearLayout layoutlwj;
    @Bind(R.id.layout_hjq)
    LinearLayout layouthjq;
    @Bind(R.id.layout_about)
    CoordinatorLayout coordinatorLayout;

    GestureDetector gestureDetector;
    private int verticalMinDistance = 20;
    private int minVelocity = 0;

    @Override
    protected void init() {
        gestureDetector = new GestureDetector(this);
        coordinatorLayout.setOnTouchListener(this);
        coordinatorLayout.setLongClickable(true);
        gain.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void setListener() {
        layoutlwj.setOnClickListener(this);
        layouthjq.setOnClickListener(this);
    }
    private void saveImage(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_gain_2);
        MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", "description");
        Toast.makeText(this,"图片已保存至本地相册",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_gain:
                View view = View.inflate(this,R.layout.item_gain,null);
                new AlertDialog.Builder(this)
                        .setView(view)
                        .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                saveImage();
                            }
                        })
                        .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

                break;
            case R.id.layout_lwj:
                Intent intent1 = new Intent();
                intent1.setAction("android.intent.action.VIEW");
                Uri content_url_lwj = Uri.parse("https://github.com/Levent-J");
                intent1.setData(content_url_lwj);
                startActivity(intent1);
                break;
            case R.id.layout_hjq:
                Intent intent2 = new Intent();
                intent2.setAction("android.intent.action.VIEW");
                Uri content_url_hjq = Uri.parse("https://github.com/AwesomeIcon");
                intent2.setData(content_url_hjq);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
            //左
        } else if (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
            //右
            finish();
        }

        return false;    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
