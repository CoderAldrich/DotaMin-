package com.example.levent_j.dotamin_.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.adapter.HistoryAdapter;
import com.example.levent_j.dotamin_.base.BaseFragment;
import com.example.levent_j.dotamin_.net.Api;
import com.example.levent_j.dotamin_.pojo.HistoryItemBean;
import com.example.levent_j.dotamin_.pojo.Match;
import com.example.levent_j.dotamin_.pojo.MatchPlayer;
import com.example.levent_j.dotamin_.pojo.Matches;
import com.example.levent_j.dotamin_.pojo.MatchesHistory;
import com.example.levent_j.dotamin_.utils.Heroes;
import com.example.levent_j.dotamin_.utils.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Observer;

/**
 * Created by levent_j on 16-3-1.
 */
public class HistoryFragment extends BaseFragment{
    @Bind(R.id.recycler_view_history)
    RecyclerView historyrecyclerView;
    @Bind(R.id.refresh_history)
    MaterialRefreshLayout materialRefreshLayout;

    private static final String ARGS = "ARGS";
    private static final String KEY_HISTORY = "History";
    private String mPage;
    private MatchesHistory mymatchesHistory;
    private String id;
    private List<Matches> matchesList;
    private HistoryAdapter historyAdapter;
    private Match match;
    private List<HistoryItemBean> historyItemBeans;
    private int count;
    private boolean isLoading;
    private boolean isloadmore;
    private int hisIndex=0;

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
        historyAdapter = new HistoryAdapter(getActivity());
        historyItemBeans = new ArrayList<>();
        matchesList = new ArrayList<>();
        count = 10;
        isLoading = true;
        isloadmore = false;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        historyrecyclerView.setLayoutManager(new LinearLayoutManager(historyrecyclerView.getContext()));
        historyrecyclerView.setItemAnimator(new DefaultItemAnimator());
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isLoading = true;
                loadDate(id);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                isloadmore = true;
                count += 5;
                loadDate(id);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void loadDate(String s) {
        id = s;
        if (isLoading||isloadmore){
            //在此发起网络请求获取数据
            //s是64 bit
            Api.getInstance().getMatchesHistory(s,String.valueOf(count),matchesHistoryObserver);
        }



    }

    @Override
    protected int setRootViewId() {
        return R.layout.item_history_fragment;
    }

    private Observer<MatchesHistory> matchesHistoryObserver = new Observer<MatchesHistory>() {
        @Override
        public void onCompleted() {
            matchesList = new ArrayList<>(mymatchesHistory.getResult().getMatches());
            Api.getInstance().getMatchDeatials(""+matchesList.get(hisIndex).getMatch_id(), matchObserver);

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            msg("Net", "Mess:" + e.getLocalizedMessage());
            if (e.getLocalizedMessage().equals("collection == null")){
                Snackbar.make(getView(), "无比赛信息", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                materialRefreshLayout.finishRefresh();
            }else if (e.getLocalizedMessage().equals("HTTP 503 Service Unavailable")){
                //如果服务器炸了，就在此发起网络请求
                Snackbar.make(getView(), "网络链接失败，请重试", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        }

        @Override
        public void onNext(MatchesHistory matchesHistory) {
            if (matchesHistory==null){
            }else {
                historyItemBeans.clear();
                mymatchesHistory = matchesHistory;
            }

        }
    };

    private Observer<Match> matchObserver = new Observer<Match>() {
        @Override
        public void onCompleted() {
            HistoryItemBean historyItemBean = new HistoryItemBean();

            historyItemBean.setMatchid(match.getResult().getMatchId());
            historyItemBean.setTime(Util.formRelativeDate(match.getResult().getStart_time()));
            historyItemBean.setType(Util.getLobby(match.getResult().getLobbyType()));

            for (int i =0;i<match.getResult().getPlayers().size();i++){
                if (id.equals(match.getResult().getPlayers().get(i).getAccountId())){
                    MatchPlayer player = match.getResult().getPlayers().get(i);
                    //查询英雄名
                    historyItemBean.setHeroName("" + player.getHero_id());
                    //查询阵营名
                    if (Util.isRadiant(player.getPlayerSlot())){
                        historyItemBean.setTeam("天辉");
                    }else {
                        historyItemBean.setTeam("夜魇");
                    }
                    //查询是否获胜
                    msg("TAG","i am"+Util.isRadiant(player.getPlayerSlot())+"rad win?"+match.getResult().isRadiantWin());
                    if (
                            (Util.isRadiant(player.getPlayerSlot())&&match.getResult().isRadiantWin())
                                ||
                            (!(Util.isRadiant(player.getPlayerSlot()))&&!(match.getResult().isRadiantWin()))
                            ){
                        historyItemBean.setWin("获胜");
                    }else {
                        historyItemBean.setWin("失败");
                    }
                    //查询kda
                    historyItemBean.setK(player.getKills());
                    historyItemBean.setD(player.getDeaths());
                    historyItemBean.setA(player.getAssists());

                    break;
                }
            }
            historyItemBeans.add(historyItemBean);
            if (hisIndex==count-1){
                hisIndex = 0;
                historyAdapter.updateHistoryList(historyItemBeans);
                historyrecyclerView.setAdapter(historyAdapter);
                matchesList.clear();
                mymatchesHistory = null;
                match = null;
                if (isloadmore){
                    materialRefreshLayout.finishRefreshLoadMore();
                    isloadmore = false;
                }
                if (isLoading){
                    materialRefreshLayout.finishRefresh();
                    isLoading = false;
                }
            }else {
                hisIndex++;
                match = null;
                Api.getInstance().getMatchDeatials(""+matchesList.get(hisIndex).getMatch_id(), matchObserver);
            }
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            msg("Net", "HIS_NET_ERROR");
        }

        @Override
        public void onNext(Match m) {
            if (m==null){
                msg("Match","no match!");
            }else {
                match = m;
                msg("DEBUG","id:"+m.getResult().getMatchId());
            }

        }
    };
}
