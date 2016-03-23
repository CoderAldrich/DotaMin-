package com.example.levent_j.dotamin_.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by levent_j on 16-3-1.
 * 这里是所有Activity的父类，封装了基本的方法
 */
public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setRootViewId(),container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    protected abstract int setRootViewId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    protected void msg(String tag,String s){
        Log.d(tag,s);
    }
}
