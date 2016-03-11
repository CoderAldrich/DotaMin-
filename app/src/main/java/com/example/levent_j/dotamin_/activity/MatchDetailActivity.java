package com.example.levent_j.dotamin_.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.adapter.DetailsAdapter;
import com.example.levent_j.dotamin_.base.BaseActivity;
import com.example.levent_j.dotamin_.net.Api;
import com.example.levent_j.dotamin_.pojo.Item;
import com.example.levent_j.dotamin_.pojo.Items;
import com.example.levent_j.dotamin_.pojo.Match;
import com.example.levent_j.dotamin_.pojo.MatchPlayer;
import com.example.levent_j.dotamin_.pojo.MatchResult;
import com.example.levent_j.dotamin_.pojo.PlayerDetailBean;
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
public class MatchDetailActivity extends BaseActivity {
    @Bind(R.id.details_expandablelistview)
    ExpandableListView expandableListView;
    @Bind(R.id.tv_match_id)
    TextView matchId;
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

    @Bind(R.id.linearLayout4)
    LinearLayout linearLayout;

    private String matchID;
    public List<MatchPlayer> father;
    public Map<MatchPlayer,List<PlayerDetailBean>> map;
    private DetailsAdapter detailsAdapter;
    private MatchResult matchResult;
    private Match match;
    private Map<Integer,String> itemUrl;
    private Item item;

    @Override
    protected void init() {
        //获取比赛结果
        matchID = getIntent().getStringExtra("matchid");
        msg("Details", "intent ,id is" + matchID);
        match = new Match();
        //发起网络请求
//        Api.getInstance().getMatchDeatials(matchID, matchObserver);

        Api.getInstance().getItems(itemObserver);
        father = new ArrayList<>();
        map = new HashMap<>();
//        for (int i=0;i<1500)
        itemUrl = new HashMap<>();
        item = new Item();
    }

    private void initListData() {

        matchId.setText("比赛id:"+matchID);
        matchTime.setText(Util.Second2Minute(Integer.parseInt(match.getResult().getDuration()))+"分钟");
        matchLobby.setText(Util.getLobby(match.getResult().getLobbyType()));
        matchMode.setText(Util.getMode(match.getResult().getGameMode()));
        radTower.setText("防御塔数："+Util.getTowerNumber(match.getResult().getTower_status_radiant()));
        radBar.setText("兵营数："+Util.getBarNumber(match.getResult().getBarracks_status_radiant()));
        dirWower.setText("防御塔数："+Util.getTowerNumber(match.getResult().getTower_status_dire()));
        dirBar.setText("兵营数：" + Util.getBarNumber(match.getResult().getBarracks_status_dire()));

        if (match.getResult().isRadiantWin()){
            radName.setText("天辉（胜）");
            dirName.setText("夜魇（败）");
        }else {
            radName.setText("夜魇（胜）");
            dirName.setText("夜魇（败）");
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
        radKills.setText("人头:"+RadKill);
        dirKills.setText("人头:"+DirKill);


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

    private Observer<Item> itemObserver = new Observer<Item>() {
        @Override
        public void onCompleted() {
            for (int i=0;i<item.getItemResult().getItmes().size();i++){
                //http://cdn.dota2.com/apps/dota2/images/items/<name>_lg.png
                String url = "http://cdn.dota2.com/apps/dota2/images/items/"+item.getItemResult().getItmes().get(i).getName().substring(5) + "_lg.png";
                itemUrl.put(item.getItemResult().getItmes().get(i).getId(),url);
            }
            Api.getInstance().getMatchDeatials(matchID, matchObserver);
            msg("item",""+itemUrl.get(1));

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            msg("item","error:"+e.getLocalizedMessage());
        }

        @Override
        public void onNext(Item i) {
            item.setItemResult(i.getItemResult());
            msg("item",""+item.getItemResult().getItmes().size());
        }
    };
}
