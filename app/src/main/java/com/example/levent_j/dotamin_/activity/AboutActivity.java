package com.example.levent_j.dotamin_.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
public class AboutActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.fab_gain)
    FloatingActionButton gain;

    @Override
    protected void init() {
        gain.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void setListener() {

    }
    private void saveImage(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_gain_2);
        MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", "description");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_gain:
                ImageView imageView = new ImageView(this);
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_gain_2));

                new AlertDialog.Builder(this)
                        .setIcon(getResources().getDrawable(R.drawable.ic_gain))
                        .setView(imageView)
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
        }
    }
}
