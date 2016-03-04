package com.example.levent_j.dotamin_.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.net.Api;
import com.example.levent_j.dotamin_.pojo.Friend;
import com.example.levent_j.dotamin_.pojo.FriendResult;
import com.example.levent_j.dotamin_.pojo.Friends;
import com.example.levent_j.dotamin_.pojo.Player;
import com.example.levent_j.dotamin_.pojo.User;
import com.example.levent_j.dotamin_.pojo.UserResponse;
import com.example.levent_j.dotamin_.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by levent_j on 16-3-4.
 */
public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.mViewHolder>{

    private Context context;
    private final LayoutInflater layoutInflater;
    private ArrayList<Player> playerArrayList;


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
//        if (!"离线".equals(Util.getState(playerArrayList.get(position).getPersonastate())){
//
//        }
        holder.name.setText(playerArrayList.get(position).getPersonaname());
        holder.state.setText(Util.getState(playerArrayList.get(position).getPersonastate()));
        Picasso.with(context).load(playerArrayList.get(position).getAvatar()).into(holder.avater);

    }

    @Override
    public int getItemCount() {
        return playerArrayList.size();
    }

    public void updateFriends(List<Player> players,boolean isClear){
        if (isClear){
            playerArrayList.clear();
        }
        playerArrayList = new ArrayList<>(players);
        notifyDataSetChanged();

    }

    class mViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_friend_name)
        TextView name;
        @Bind(R.id.iv_friend_avater)
        ImageView avater;
        @Bind(R.id.tv_friend_state)
        TextView state;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
