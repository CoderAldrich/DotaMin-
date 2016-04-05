package com.example.levent_j.dotamin_.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.activity.MainActivity;
import com.example.levent_j.dotamin_.model.Player;
import com.example.levent_j.dotamin_.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by levent_j on 16-3-4.
 */
public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.mViewHolder>{

    private Context context;
    private final LayoutInflater layoutInflater;
    private List<Player> playerArrayList;


    public FriendsAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        playerArrayList = new ArrayList<>();
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.friend_item, null);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        //更新各个view
        //发起网络请求，获取用户数据
        holder.name.setText(playerArrayList.get(position).getPersonaname());
        holder.state.setText(Util.getState(playerArrayList.get(position).getPersonastate()));
        Picasso.with(context).load(playerArrayList.get(position).getAvatar()).into(holder.avater);
        holder.last.setText("上次登陆："+Util.formRelativeDate(playerArrayList.get(position).getLastlogoff()));

    }

    @Override
    public int getItemCount() {
        return playerArrayList.size();
    }

    public void updateFriends(List<Player> players,boolean isClear){
        playerArrayList.clear();
        playerArrayList .addAll(players);
        notifyDataSetChanged();

    }

    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.tv_friend_name)
        TextView name;
        @Bind(R.id.iv_friend_avater)
        ImageView avater;
        @Bind(R.id.tv_friend_state)
        TextView state;
        @Bind(R.id.tv_friend_last)
        TextView last;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            name.setOnClickListener(this);
            avater.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_friend_name:
                case R.id.iv_friend_avater:
                    String s = playerArrayList.get(getPosition()).getSteamid();
                    Intent intent = new Intent(context,MainActivity.class);
                    intent.putExtra("id",s);
                    context.startActivity(intent);
                    break;
            }
        }
    }


}
