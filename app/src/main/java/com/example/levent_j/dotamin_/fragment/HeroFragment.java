package com.example.levent_j.dotamin_.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.adapter.HeroesAdapter;
import com.example.levent_j.dotamin_.base.BaseFragment;
import com.example.levent_j.dotamin_.utils.Heroes;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by levent_j on 16-3-3.
 */
public class HeroFragment extends BaseFragment{
    @Bind(R.id.recycler_view_heros)
    RecyclerView recyclerView;

    private static final String ARGS = "HERO";
    private static final String KEY_HERO = "Hero";
    private String mPage;
    private HeroesAdapter heroesAdapter;

    public static HeroFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(ARGS,KEY_HERO);
        HeroFragment fragment = new HeroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            mPage = getArguments().getString(ARGS,KEY_HERO);
        }
        heroesAdapter = new HeroesAdapter(getActivity());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        loadDate();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setAdapter(heroesAdapter);
    }

    private void loadDate() {
        List<String> strings = new ArrayList<>();
        for (int i=0;i<10;i++){
            strings.add(Heroes.HERO_NAME[i]);
        }
        heroesAdapter.initArrayList(strings);
    }

    @Override
    protected int setRootViewId() {
        return R.layout.item_hero_fragment;
    }
}
