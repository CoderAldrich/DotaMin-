package com.example.levent_j.dotamin_.activity;

import android.annotation.TargetApi;
import android.gesture.GestureOverlayView;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by levent_j on 16-3-23.
 */
public class FeedActivity extends BaseActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {
    @Bind(R.id.layout_feed)
    CoordinatorLayout coordinatorLayout;

    GestureDetector gestureDetector;
    private int verticalMinDistance = 20;
    private int minVelocity = 0;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void init() {
        gestureDetector = new GestureDetector(this);
        coordinatorLayout.setOnTouchListener(this);
        coordinatorLayout.setLongClickable(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feed;
    }

    @Override
    protected void setListener() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
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

            return false;
    }
}
