package com.example.levent_j.dotamin_.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.activity.HeroDetailActivity;
import com.example.levent_j.dotamin_.model.MatchPlayer;
import com.example.levent_j.dotamin_.model.PlayerDetailBean;
import com.example.levent_j.dotamin_.utils.Heroes;
import com.example.levent_j.dotamin_.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by levent_j on 16-3-9.
 */
public class DetailsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<MatchPlayer> matchPlayerList;
    private Map<MatchPlayer,List<PlayerDetailBean>> matchPlayerListMap;


    public DetailsAdapter(List<MatchPlayer> players,Map<MatchPlayer,List<PlayerDetailBean>> map,Context mcontext){
        this.context = mcontext;
        this.matchPlayerList = players;
        this.matchPlayerListMap = map;
    }

    @Override
    public int getGroupCount() {
        return matchPlayerList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        MatchPlayer key = matchPlayerList.get(groupPosition);
        return matchPlayerListMap.get(key).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return matchPlayerList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        MatchPlayer key = matchPlayerList.get(groupPosition);
        return matchPlayerListMap.get(key).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_parent,null);
        }

        TextView heroname = (TextView) convertView.findViewById(R.id.tv_hero_name);
        TextView herolevel = (TextView) convertView.findViewById(R.id.tv_hero_level);
        TextView heroteam = (TextView) convertView.findViewById(R.id.tv_hero_team);
        TextView herokill = (TextView) convertView.findViewById(R.id.tv_hero_kill);
        TextView herodeath = (TextView) convertView.findViewById(R.id.tv_hero_death);
        TextView heroass = (TextView) convertView.findViewById(R.id.tv_hero_ass);
        ImageView avater = (ImageView) convertView.findViewById(R.id.iv_hero_avater);

        MatchPlayer matchPlayer = matchPlayerList.get(groupPosition);
        final String name = Heroes.HERO_NAME[matchPlayer.getHero_id() - 1];
        heroname.setText(name);
        herolevel.setText("等级"+matchPlayer.getLevel());
        heroteam.setText((Util.isRadiant(matchPlayer.getPlayerSlot()))?"天辉":"夜魇");
        herokill.setText("击杀："+matchPlayer.getKills());
        herodeath.setText("死亡：" + matchPlayer.getDeaths());
        heroass.setText("助攻：" + matchPlayer.getAssists());

        Picasso.with(context).load(Heroes.HERO_IMAGE_FULL[matchPlayer.getHero_id() - 1]).into(avater);
        if ("天辉".equals(heroteam.getText().toString())){
            heroteam.setTextColor(context.getResources().getColor(R.color.material_green));
        }else {
            heroteam.setTextColor(context.getResources().getColor(R.color.material_red));
        }

        avater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HeroDetailActivity.class);
                intent.putExtra("name",name);
                context.startActivity(intent);
                Log.d("Debug","heroname is"+name);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        MatchPlayer key = matchPlayerList.get(groupPosition);
        PlayerDetailBean info = matchPlayerListMap.get(key).get(childPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_children,null);
        }

        TextView hits = (TextView) convertView.findViewById(R.id.tv_hits);
        TextView denis = (TextView) convertView.findViewById(R.id.tv_denis);
        TextView xpm = (TextView) convertView.findViewById(R.id.tv_XPM);
        TextView gpm = (TextView) convertView.findViewById(R.id.tv_GPM);
        TextView heroDamage = (TextView) convertView.findViewById(R.id.tv_hero_damage);
        TextView towerDamage = (TextView) convertView.findViewById(R.id.tv_tower_damage);
        TextView heroHealing = (TextView) convertView.findViewById(R.id.tv_hero_healing);
        TextView kda = (TextView) convertView.findViewById(R.id.tv_hero_kda);
        TextView fight = (TextView) convertView.findViewById(R.id.tv_hero_fight);
        ImageView item0 = (ImageView) convertView.findViewById(R.id.iv_item_0);
        ImageView item1 = (ImageView) convertView.findViewById(R.id.iv_item_1);
        ImageView item2 = (ImageView) convertView.findViewById(R.id.iv_item_2);
        ImageView item3 = (ImageView) convertView.findViewById(R.id.iv_item_3);
        ImageView item4 = (ImageView) convertView.findViewById(R.id.iv_item_4);
        ImageView item5 = (ImageView) convertView.findViewById(R.id.iv_item_5);

        hits.setText("正补数："+info.getHits());
        denis.setText("反补数：" + info.getDenies());
        xpm.setText("XPM：" + info.getXPM());
        gpm.setText("GPM：" + info.getGPM());
        heroDamage.setText("英雄伤害：" + info.getHeroDamage());
        towerDamage.setText("建筑伤害：" + info.getTowerDamage());
        heroHealing.setText("治疗量：" + info.getHealing());
        kda.setText("KDA："+info.getKda());
        fight.setText("参战率："+info.getFight()+"%");

        Picasso.with(context).load(info.getItemUrl_1()).into(item0);
        Picasso.with(context).load(info.getItemUrl_2()).into(item1);
        Picasso.with(context).load(info.getItemUrl_3()).into(item2);
        Picasso.with(context).load(info.getItemUrl_4()).into(item3);
        Picasso.with(context).load(info.getItemUrl_5()).into(item4);
        Picasso.with(context).load(info.getItemUrl_6()).into(item5);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
