package com.example.levent_j.dotamin_.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.pojo.HistoryItemBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by levent_j on 16-3-6.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.mViewHolder>{

    private Context context;
    private final LayoutInflater layoutInflater;
    private ArrayList<HistoryItemBean> historyItemBeanArrayList;

    public HistoryAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        historyItemBeanArrayList = new ArrayList<>();
        //list = new list
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.history_item,null);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        //设置view
        HistoryItemBean bean = historyItemBeanArrayList.get(position);

        holder.name.setText(bean.getHeroName());
//        Picasso.with(context).load(bean.getHeroAvaterUrl()).into(holder.avater);
        holder.team.setText(bean.getTeam());
        holder.team.setText(bean.getTeam());
        holder.win.setText(bean.isWin());
        holder.k.setText("K:"+bean.getK());
        holder.d.setText("D:"+bean.getD());
        holder.a.setText("A:"+bean.getA());
    }

    @Override
    public int getItemCount() {
        //list的长度
        return historyItemBeanArrayList.size();
    }

    public void updateHistoryList(List<HistoryItemBean> historyItemBeans){
        historyItemBeanArrayList.clear();
        historyItemBeanArrayList.addAll(historyItemBeans);
        notifyDataSetChanged();
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_history_hero_name)
        TextView name;
        @Bind(R.id.tv_history_team)
        TextView team;
        @Bind(R.id.tv_history_win)
        TextView win;
        @Bind(R.id.tv_history_type)
        TextView type;
        @Bind(R.id.tv_history_k)
        TextView k;
        @Bind(R.id.tv_history_d)
        TextView d;
        @Bind(R.id.tv_history_a)
        TextView a;
        @Bind(R.id.iv_history_hero_avater)
        ImageView avater;
        @Bind(R.id.tv_history_time)
        TextView time;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
