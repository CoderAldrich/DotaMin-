package com.example.levent_j.dotamin_.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.adapter.DetailsAdapter;
import com.example.levent_j.dotamin_.base.BaseActivity;
import com.example.levent_j.dotamin_.net.Api;
import com.example.levent_j.dotamin_.model.Item;
import com.example.levent_j.dotamin_.model.Match;
import com.example.levent_j.dotamin_.model.MatchPlayer;
import com.example.levent_j.dotamin_.model.PlayerDetailBean;
import com.example.levent_j.dotamin_.utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import rx.Observer;

/**
 * Created by levent_j on 16-3-7.
 */
public class MatchDetailActivity extends BaseActivity implements GestureDetector.OnGestureListener, View.OnTouchListener {
    @Bind(R.id.details_expandablelistview)
    ExpandableListView expandableListView;
    @Bind(R.id.tv_match_lobby)
    TextView matchLobby;
    @Bind(R.id.tv_match_mode)
    TextView matchMode;
    @Bind(R.id.tv_match_time)
    TextView matchTime;
    @Bind(R.id.tv_rad_kills)
    TextView radKills;
    @Bind(R.id.tv_rad_tower)
    TextView radTower;
    @Bind(R.id.tv_rad_bar)
    TextView radBar;
    @Bind(R.id.tv_dir_kills)
    TextView dirKills;
    @Bind(R.id.tv_dir_tower)
    TextView dirWower;
    @Bind(R.id.tv_dir_bar)
    TextView dirBar;
    @Bind(R.id.tv_rad_name)
    TextView radName;
    @Bind(R.id.tv_dir_name)
    TextView dirName;
    @Bind(R.id.toolbar_detail)
    Toolbar toolbar;
    @Bind(R.id.layout_match_detail)
    CoordinatorLayout coordinatorLayout;



    private String matchID;
    public List<MatchPlayer> father;
    public Map<MatchPlayer,List<PlayerDetailBean>> map;
    private DetailsAdapter detailsAdapter;
    private Match match;
    private Map<Integer,String> itemUrl;
    private Item item;
    GestureDetector gestureDetector;
    private static final int verticalMinDistance = 20;
    private static final int minVelocity = 0;

    @Override
    protected void init() {
        //获取比赛结果
        matchID = getIntent().getStringExtra("matchid");
        msg("Details", "intent ,id is" + matchID);
        match = new Match();
        Api.getInstance().getItems(itemObserver);
        father = new ArrayList<>();
        map = new HashMap<>();
        itemUrl = new HashMap<>();
        item = new Item();
        gestureDetector = new GestureDetector(this);
        coordinatorLayout.setOnTouchListener(this);
        coordinatorLayout.setLongClickable(true);
    }

    private void initListData() {

        toolbar.setTitle("比赛id:"+matchID);
        matchTime.setText(Util.Second2Minute(Integer.parseInt(match.getResult().getDuration()))+"分钟");
        matchLobby.setText(Util.getLobby(match.getResult().getLobbyType()));
        matchMode.setText(Util.getMode(match.getResult().getGameMode()));
        radTower.setText("防御塔："+Util.getTowerNumber(match.getResult().getTower_status_radiant()));
        radBar.setText("兵营："+Util.getBarNumber(match.getResult().getBarracks_status_radiant()));
        dirWower.setText("防御塔："+Util.getTowerNumber(match.getResult().getTower_status_dire()));
        dirBar.setText("兵营：" + Util.getBarNumber(match.getResult().getBarracks_status_dire()));

        if (match.getResult().isRadiantWin()){
            radName.setText("天辉（胜）");
            dirName.setText("夜魇（败）");
        }else {
            radName.setText("天辉（败）");
            dirName.setText("夜魇（胜）");
        }

        int RadKill = 0;
        int DirKill = 0;

        for (int i=0;i<match.getResult().getPlayers().size(); i++) {
            father.add(match.getResult().getPlayers().get(i));

            if (Util.isRadiant(match.getResult().getPlayers().get(i).getPlayerSlot())){
                RadKill = RadKill + match.getResult().getPlayers().get(i).getKills();
            }else {
                DirKill = DirKill + match.getResult().getPlayers().get(i).getKills();
            }
        }
        radKills.setText("击杀:"+RadKill);
        dirKills.setText("击杀:"+DirKill);


        for (int j=0;j<father.size();j++){
            List<PlayerDetailBean> list = new ArrayList<>();
            PlayerDetailBean player = new PlayerDetailBean();

            player.setHits(father.get(j).getLast_hits());
            player.setDenies(father.get(j).getDenies());
            player.setXPM(father.get(j).getXp_per_min());
            player.setGPM(father.get(j).getGold_per_min());
            player.setHeroDamage(father.get(j).getHero_damage());
            player.setTowerDamage(father.get(j).getTower_damage());
            player.setHealing(father.get(j).getHero_healing());

            player.setItemUrl_1(itemUrl.get(father.get(j).getItem0()));
            player.setItemUrl_2(itemUrl.get(father.get(j).getItem1()));
            player.setItemUrl_3(itemUrl.get(father.get(j).getItem2()));
            player.setItemUrl_4(itemUrl.get(father.get(j).getItem3()));
            player.setItemUrl_5(itemUrl.get(father.get(j).getItem4()));
            player.setItemUrl_6(itemUrl.get(father.get(j).getItem5()));

            //计算参战率
            if (Util.isRadiant(father.get(j).getPlayerSlot())){
                int he = father.get(j).getKills()+father.get(j).getAssists();
                player.setFight((float)(he*100/RadKill));
            }else {
                int he = father.get(j).getKills()+father.get(j).getAssists();
                player.setFight((float)(he*100/DirKill));
            }
            //计算kda
            int d=father.get(j).getDeaths();
            if (d==0){
                d=1;
            }
            player.setKda((float) ((father.get(j).getKills() + father.get(j).getAssists()) / d));
            list.add(player);
            map.put(father.get(j),list);
        }

        detailsAdapter = new DetailsAdapter(father,map,this);
        expandableListView.setAdapter(detailsAdapter);
        expandableListView.setGroupIndicator(null);
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
            initListData();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(Match m) {
            if (m.getResult().getPlayers()==null){

                Toast.makeText(getApplicationContext(),"未找到任何比赛信息",Toast.LENGTH_SHORT).show();
                finish();
            }else {
                match.setResult(m.getResult());
            }

        }
    };

    private Observer<Item> itemObserver = new Observer<Item>() {
        @Override
        public void onCompleted() {
            for (int i=0;i<item.getItemResult().getItmes().size();i++){
                //http://cdn.dota2.com/apps/dota2/images/items/<name>_lg.png
                String url = "http://cdn.dota2.com/apps/dota2/images/items/"+item.getItemResult().getItmes().get(i).getName().substring(5) + "_lg.png";
                itemUrl.put(item.getItemResult().getItmes().get(i).getId(),url);
            }
            Api.getInstance().getMatchDeatials(matchID, matchObserver);

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(Item i) {
            item.setItemResult(i.getItemResult());
        }
    };

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
