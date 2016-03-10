package com.example.levent_j.dotamin_.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.pojo.MatchPlayer;
import com.example.levent_j.dotamin_.utils.Heroes;
import com.example.levent_j.dotamin_.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by levent_j on 16-3-9.
 */
public class DetailsAdapter extends BaseExpandableListAdapter {
    @Bind(R.id.tv_hero_name)
    TextView heroname;
    @Bind(R.id.tv_hero_level)
    TextView herolevel;
    @Bind(R.id.tv_hero_team)
    TextView heroteam;
    @Bind(R.id.tv_hero_kill)
    TextView herokill;
    @Bind(R.id.tv_hero_death)
    TextView herodeath;
    @Bind(R.id.tv_hero_ass)
    TextView heroass;
    @Bind(R.id.iv_hero_avater)
    ImageView avater;

    private Context context;
    private List<MatchPlayer> matchPlayerList;
    private Map<MatchPlayer,List<String>> matchPlayerListMap;


    public DetailsAdapter(List<MatchPlayer> players,Map<MatchPlayer,List<String>> map,Context mcontext){
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
        int size = matchPlayerListMap.get(key).size();
        return size;
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
        ButterKnife.bind(this, convertView);
        MatchPlayer matchPlayer = matchPlayerList.get(groupPosition);
        heroname.setText(Heroes.HERO_NAME[matchPlayer.getHero_id() - 1]);
        herolevel.setText("等级"+matchPlayer.getLevel());
        heroteam.setText((Util.isRadiant(matchPlayer.getPlayerSlot()))?"天辉":"夜魇");
        herokill.setText("击杀："+matchPlayer.getKills());
        herodeath.setText("死亡："+matchPlayer.getDeaths());
        heroass.setText("助攻："+matchPlayer.getAssists());
        Picasso.with(context).load(Heroes.HERO_IMAGE_FULL[matchPlayer.getHero_id() - 1]).into(avater);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        MatchPlayer key = matchPlayerList.get(groupPosition);
        String info = matchPlayerListMap.get(key).get(childPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_children,null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.child);
        textView.setText(info);
        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
