package com.example.levent_j.dotamin_.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.adapter.FriendsAdapter;
import com.example.levent_j.dotamin_.base.BaseFragment;
import com.example.levent_j.dotamin_.net.Api;
import com.example.levent_j.dotamin_.pojo.FriendResult;
import com.example.levent_j.dotamin_.pojo.Friends;
import com.example.levent_j.dotamin_.pojo.Player;
import com.example.levent_j.dotamin_.pojo.User;
import com.example.levent_j.dotamin_.utils.DividerItemDecoration;
import com.example.levent_j.dotamin_.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import rx.Observer;
import xhome.uestcfei.com.loadingpoppoint.LoadingPopPoint;

/**
 * Created by levent_j on 16-3-3.
 */
public class UserFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.iv_user_avater)
    ImageView avater;
    @Bind(R.id.tv_user_name)
    TextView username;
    @Bind(R.id.tv_last_logoff)
    TextView logoff;
    @Bind(R.id.tv_user_state)
    TextView state;
    @Bind(R.id.tv_user_id)
    TextView steamid;
    @Bind(R.id.tv_steam_url)
    TextView steamurl;
    @Bind(R.id.loadingpoppoint)
    public LoadingPopPoint loadingPopPoint;
    @Bind(R.id.recycler_friends_list)
    RecyclerView recyclerView_friends;
    @Bind(R.id.friends_refresh)
    MaterialRefreshLayout materialRefreshLayout;

    private static final String ARGS = "USER";
    private static final String KEY_USER = "User";
    private String mPage;
    private User muser;
    private User mfrienduser;
    private FriendResult mfriends;
    private String steamURL;
    private String steamID;
    FriendsAdapter friendsAdapter;
    private ArrayList<Player> basePlayerList;
    private int count;
    public boolean flag;
    private boolean isClear;
    private int userIndex;

    public static UserFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(ARGS,KEY_USER);
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            mPage = getArguments().getString(ARGS,KEY_USER);
        }
        basePlayerList = new ArrayList<>();
        muser = new User();
        mfrienduser = new User();
        mfriends = new FriendResult();
        friendsAdapter = new FriendsAdapter(getActivity());
        count = 5;
        flag = true;
        isClear = true;
        userIndex = 0;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        steamurl.setOnClickListener(this);
        loadingPopPoint.setVisibility(View.INVISIBLE);
        recyclerView_friends.setLayoutManager(new LinearLayoutManager(recyclerView_friends.getContext()));
        recyclerView_friends.setItemAnimator(new DefaultItemAnimator());
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                loadFrinedsDate(steamID);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                loadMore(steamID);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void loadFrinedsDate(String id){
        //在此发起网络请求
        //Api......
        steamID = id;
        flag = true;
        Api.getInstance().getFriends(id, friendResultObserver);
    }

    public void loadMore(String id){
        //在此发起网络请求
        //Api......
        count=count+1;
        msg("load","load more,count is "+count);
        steamID = id;
        flag = true;
        Api.getInstance().getFriends(id, friendResultObserver);
    }

    public void loadUserDate(String id) {
        //在此发起网络请求获取数据
        Api.getInstance().getUsers(id, userObserver);
    }

    private void changeUserView(User u){
        Player player = u.getResponse().getPlayers().get(0);
        username.setText(player.getPersonaname());
        logoff.setText("上次登陆：" + Util.formRelativeDate(player.getLastlogoff()));
        steamid.setText("steam id:" + Util.get32Id(Long.parseLong(player.getSteamid())));
        steamURL = player.getProfileurl();
        steamurl.setText("点击访问社区页面");
        state.setText("当前："+Util.getState(player.getPersonastate()));
        Picasso.with(getContext()).load(player.getAvatarfull()).into(avater);
    }

    @Override
    protected int setRootViewId() {
        return R.layout.item_user_fragment;
    }

    private Observer<FriendResult> friendResultObserver = new Observer<FriendResult>() {
        @Override
        public void onCompleted() {
            msg("Net", "NET_SUCCESS");
            if (flag&&mfriends.getFriendslist().getFriends().size()<count){
                count = mfriends.getFriendslist().getFriends().size();
                flag = false;
            }
            Api.getInstance().getUsers(mfriends.getFriendslist().getFriends().get(userIndex).getSteamid(), userFriendObserver);
            materialRefreshLayout.finishRefreshLoadMore();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            msg("Net","NET_ERROR");
        }

        @Override
        public void onNext(FriendResult friendResult) {
            if (friendResult!=null){
                mfriends.setFriendslist(friendResult.getFriendslist());
            }else {
                msg("Net","friends is null");
            }
        }
    };

    private Observer<User> userFriendObserver = new Observer<User>() {
        @Override
        public void onCompleted() {
            basePlayerList.add(mfrienduser.getResponse().getPlayers().get(0));
            if (userIndex==count-1){
                friendsAdapter.updateFriends(basePlayerList, isClear);
                recyclerView_friends.setAdapter(friendsAdapter);
                materialRefreshLayout.finishRefresh();
                userIndex = 0;
                basePlayerList.clear();
            }else {
                userIndex++;
                Api.getInstance().getUsers(mfriends.getFriendslist().getFriends().get(userIndex).getSteamid(), userFriendObserver);
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(User user) {
            mfrienduser.setResponse(user.getResponse());
        }
    };

    private Observer<User> userObserver = new Observer<User>() {
        @Override
        public void onCompleted() {
            msg("Net", "NET_SUCCESS");
            msg("Net","name is "+muser.getResponse().getPlayers().get(0).getPersonaname());
            changeUserView(muser);
            loadingPopPoint.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            msg("Net","NET_ERROR");
        }

        @Override
        public void onNext(User user) {
            if (user!=null){
                msg("Net","size is"+user.getResponse().getPlayers().size());
                muser.setResponse(user.getResponse());
            }else {
                msg("Net","user is null");
            }

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_steam_url:
                //跳转至浏览器
                Snackbar.make(v, "跳转至浏览器", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(steamURL);
                intent.setData(content_url);
                startActivity(intent);
                break;
        }
    }

}
