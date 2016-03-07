package com.example.levent_j.dotamin_.fragment;

import android.os.Bundle;
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
    private int flag;
    private int count;

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
        mymatchesHistory = new MatchesHistory();
        historyAdapter = new HistoryAdapter(getActivity());
        match = new Match();
        historyItemBeans = new ArrayList<>();
        flag = 1;
        count = 10;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        historyrecyclerView.setLayoutManager(new LinearLayoutManager(historyrecyclerView.getContext()));
        historyrecyclerView.setAdapter(historyAdapter);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                loadDate(id);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void loadDate(String s) {
        //在此发起网络请求获取数据
        //s是64 bit
        id = s;
        Api.getInstance().getMatchesHistory(s,String.valueOf(count),matchesHistoryObserver);
    }

    @Override
    protected int setRootViewId() {
        return R.layout.item_history_fragment;
    }

    private Observer<MatchesHistory> matchesHistoryObserver = new Observer<MatchesHistory>() {
        @Override
        public void onCompleted() {
            matchesList = new ArrayList<>(mymatchesHistory.getResult().getMatches());
            for (int i=0;i<matchesList.size();i++){
                msg("His","the games are:"+matchesList.get(i).getMatch_id());
                Api.getInstance().getMatchDeatials(""+matchesList.get(i).getMatch_id(), matchObserver);
            }

//            for (int i=0;i<matchesList.size();i++){
//                //自定义一个list，发起网络请求
//                Api.getInstance().getMatchDeatials(matchesList.get(0).getMatch_id(),matchObserver);
//            }
            //遍历matchesList,对于list中的每一次比赛，通过获取比赛id发起网络请求获取其战况，并在next中填充新的list,在complete中把新的list传递到adpter中。这个新的list属于自己定义的。
            //下一步，先写recyclerview的adapter，然后写比赛详情的api，最后自定义一个list，将其填充到adapter中

            msg("His", "显示完毕");
            msg("His", "match list is:" + matchesList.size());
            msg("His", "my bean list is:" + historyItemBeans.size());

            historyAdapter.updateHistoryList(historyItemBeans);
            historyrecyclerView.setAdapter(historyAdapter);
            materialRefreshLayout.finishRefresh();

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            msg("Net", "NET_ERROR");
            loadDate(id);
            //如果服务器炸了，就在此发起网络请求
        }

        @Override
        public void onNext(MatchesHistory matchesHistory) {
            if (matchesHistory==null){
                msg("His","Null");
            }else {
                msg("His","it is"+matchesHistory);
            }
            mymatchesHistory = matchesHistory;
        }
    };

    private Observer<Match> matchObserver = new Observer<Match>() {
        @Override
        public void onCompleted() {
            HistoryItemBean historyItemBean = new HistoryItemBean();

            historyItemBean.setTime(Util.formRelativeDate(match.getResult().getStart_time()));
            historyItemBean.setType(Util.getLobby(match.getResult().getLobbyType()));

            for (int i =0;i<match.getResult().getPlayers().size();i++){
                msg("His","in the for");
                msg("His","my id is"+id+"but this id is"+match.getResult().getPlayers().get(i).getAccountId());
                if (id.equals(match.getResult().getPlayers().get(i).getAccountId())){
                    msg("His","break");
                    MatchPlayer player = match.getResult().getPlayers().get(i);
                    //查询英雄名
                    historyItemBean.setHeroName(""+player.getHero_id());
                    //查询阵营名

                    msg("Match","队伍"+historyItemBean.getTeam());
                    if (Util.isRadiant(player.getPlayerSlot())){
                        historyItemBean.setTeam("天辉");
                    }else {
                        historyItemBean.setTeam("夜魇");
                    }
                    //查询是否获胜
                    if (Util.isRadiant(player.getPlayerSlot())&&match.getResult().isRadiantWin()){
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

            if (flag>10){
//                historyItemBeans.clear();
//                flag = 1;
            }else {
                historyItemBeans.add(historyItemBean);
                flag++;
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
                msg("Match","id is"+m.getResult().getMatchId());
                if (m.getResult().isRadiantWin()){
                    msg("Match","win");
                }else {
                    msg("Match","false");
                }
                match = m;
            }

        }
    };
}
