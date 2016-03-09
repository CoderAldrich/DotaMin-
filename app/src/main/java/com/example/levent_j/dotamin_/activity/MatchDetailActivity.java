package com.example.levent_j.dotamin_.activity;

import android.widget.ExpandableListView;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.adapter.DetailsAdapter;
import com.example.levent_j.dotamin_.base.BaseActivity;
import com.example.levent_j.dotamin_.net.Api;
import com.example.levent_j.dotamin_.pojo.Match;
import com.example.levent_j.dotamin_.pojo.MatchPlayer;
import com.example.levent_j.dotamin_.pojo.MatchResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import rx.Observer;

/**
 * Created by levent_j on 16-3-7.
 */
public class MatchDetailActivity extends BaseActivity {
    @Bind(R.id.details_expandablelistview)
    ExpandableListView expandableListView;


    private String matchID;
    public List<MatchPlayer> father;
    public Map<MatchPlayer,List<String>> map;
    private DetailsAdapter detailsAdapter;
    private MatchResult matchResult;
    private Match match;

    @Override
    protected void init() {

        //获取比赛结果
        matchID = getIntent().getStringExtra("matchid");
        msg("Details", "intent ,id is" + matchID);
        match = new Match();
        //发起网络请求
        Api.getInstance().getMatchDeatials(matchID,matchObserver);

        father = new ArrayList<>();
        map = new HashMap<>();
        //加载list数据


    }

    private void initListData() {
        for (int i=0;i<match.getResult().getPlayers().size();i++){
            father.add(match.getResult().getPlayers().get(i));
        }

        for (int j=0;j<father.size();j++){
            List<String> list = new ArrayList<String>();
            list.add("K:"+father.get(j).getKills());
            list.add("D:"+father.get(j).getDeaths());
            list.add("A:"+father.get(j).getAssists());
            map.put(father.get(j),list);
        }

        detailsAdapter = new DetailsAdapter(father,map,this);
        expandableListView.setAdapter(detailsAdapter);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_match_detail;
    }

    @Override
    protected void setListener() {

    }

    private Observer<Match> matchObserver = new Observer<Match>() {
        @Override
        public void onCompleted() {
            msg("Details","size is"+match.getResult().getPlayers().size());
            initListData();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            msg("Details","ERROR");
        }

        @Override
        public void onNext(Match m) {
            if (m.getResult()==null){
                msg("Details","NO MATCH RESULT!!!");
            }else {
//                match.setResult(m.getResult());
                msg("Details",""+m.getResult().getPlayers().size());
                match.setResult(m.getResult());
            }

        }
    };
}
