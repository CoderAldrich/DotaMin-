package com.example.levent_j.dotamin_.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by levent_j on 16-3-1.
 */
public class HistoryFragment extends BaseFragment{
    @Bind(R.id.txt)
    TextView textView;
    private static final String ARGS = "ARGS";
    private static final String KEY_HISTORY = "History";
    private String mPage;

    public static HistoryFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(ARGS,KEY_HISTORY);
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            mPage = getArguments().getString(ARGS,KEY_HISTORY);
        }
        //填充list
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView.setText(mPage);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDate();
    }

    private void loadDate() {
        //在此发起网络请求获取数据
    }

    @Override
    protected int setRootViewId() {
        return R.layout.item_history_fragment;
    }
}
