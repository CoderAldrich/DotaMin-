package com.example.levent_j.dotamin_.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.adapter.FriendsAdapter;
import com.example.levent_j.dotamin_.base.BaseFragment;
import com.example.levent_j.dotamin_.net.Api;
import com.example.levent_j.dotamin_.model.FriendResult;
import com.example.levent_j.dotamin_.model.Player;
import com.example.levent_j.dotamin_.model.User;
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
    ImageView mAvater;
    @Bind(R.id.tv_user_name)
    TextView mUserName;
    @Bind(R.id.tv_last_logoff)
    TextView mLogOff;
    @Bind(R.id.tv_user_state)
    TextView mUserState;
    @Bind(R.id.tv_user_id)
    TextView mSteamId;
    @Bind(R.id.tv_steam_url)
    TextView mSteamUrl;
    @Bind(R.id.loadingpoppoint)
    public LoadingPopPoint mPopPoint;
    @Bind(R.id.recycler_friends_list)
    RecyclerView mRecyclerFriends;
    @Bind(R.id.friends_refresh)
    MaterialRefreshLayout mMDRefresh;
    @Bind(R.id.tv_user_no_content)
    TextView noContent;

    private static final String ARGS = "USER";
    private static final String KEY_USER = "User";
    private String mPage;
    //一些处理网络请求时用到的实例
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
        mSteamUrl.setOnClickListener(this);
        mPopPoint.setVisibility(View.INVISIBLE);
        mRecyclerFriends.setLayoutManager(new LinearLayoutManager(mRecyclerFriends.getContext()));
        mRecyclerFriends.setItemAnimator(new DefaultItemAnimator());
        mMDRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
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
//        noContent.setText("请等待");
        steamID = id;
        flag = true;
        Api.getInstance().getFriends(id, friendResultObserver);
    }

    public void loadMore(String id){
        count=count+1;
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
        mUserName.setText(player.getPersonaname());
        mLogOff.setText("上次登陆：" + Util.formRelativeDate(player.getLastlogoff()));
        mSteamId.setText("Dota2 id:" + Util.get32Id(Long.parseLong(player.getSteamid())));
        steamURL = player.getProfileurl();
        mSteamUrl.setText("点击访问社区页面");
        mUserState.setText("当前：" + Util.getState(player.getPersonastate()));
        Picasso.with(getContext()).load(player.getAvatarfull()).into(mAvater);
    }

    @Override
    protected int setRootViewId() {
        return R.layout.item_user_fragment;
    }

    private Observer<FriendResult> friendResultObserver = new Observer<FriendResult>() {
        @Override
        public void onCompleted() {
            if (mfriends.getFriendslist().getFriends().size()<count){
                count = mfriends.getFriendslist().getFriends().size();
//                flag = false;
            }
            Api.getInstance().getUsers(mfriends.getFriendslist().getFriends().get(userIndex).getSteamid(), userFriendObserver);
            mMDRefresh.finishRefreshLoadMore();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            mMDRefresh.finishRefresh();
            mMDRefresh.finishRefreshLoadMore();
            noContent.setText("网络错误");
        }

        @Override
        public void onNext(FriendResult friendResult) {
            if (friendResult!=null){
                mfriends.setFriendslist(friendResult.getFriendslist());
            }
        }
    };

    private Observer<User> userFriendObserver = new Observer<User>() {
        @Override
        public void onCompleted() {
            basePlayerList.add(mfrienduser.getResponse().getPlayers().get(0));
            if (userIndex==count-1){
                friendsAdapter.updateFriends(basePlayerList, isClear);
                mRecyclerFriends.setAdapter(friendsAdapter);
                mMDRefresh.finishRefresh();
                userIndex = 0;
                basePlayerList.clear();
                noContent.setVisibility(View.GONE);
            }else {
                userIndex++;
                Api.getInstance().getUsers(mfriends.getFriendslist().getFriends().get(userIndex).getSteamid(), userFriendObserver);
            }
        }

        @Override
        public void onError(Throwable e) {
            mMDRefresh.finishRefresh();
            mMDRefresh.finishRefreshLoadMore();
        }

        @Override
        public void onNext(User user) {
            mfrienduser.setResponse(user.getResponse());
        }
    };

    private Observer<User> userObserver = new Observer<User>() {
        @Override
        public void onCompleted() {
            changeUserView(muser);
            mPopPoint.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            mMDRefresh.finishRefresh();
            mMDRefresh.finishRefreshLoadMore();
        }

        @Override
        public void onNext(User user) {
            if (user!=null){
                muser.setResponse(user.getResponse());
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
