package com.example.levent_j.dotamin_.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.pojo.MatchPlayer;
import com.example.levent_j.dotamin_.utils.Heroes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by levent_j on 16-3-9.
 */
public class DetailsAdapter extends BaseExpandableListAdapter {

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
        TextView textView = (TextView) convertView.findViewById(R.id.parent);
        textView.setText(Heroes.HERO_NAME[matchPlayerList.get(groupPosition).getHero_id()-1]);
        return textView;
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
