package com.example.levent_j.dotamin_.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.base.BaseFragment;
import com.example.levent_j.dotamin_.net.Api;
import com.example.levent_j.dotamin_.pojo.Player;
import com.example.levent_j.dotamin_.pojo.User;
import com.example.levent_j.dotamin_.utils.Util;
import com.squareup.picasso.Picasso;

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

    private static final String ARGS = "USER";
    private static final String KEY_USER = "User";
    private String mPage;
    private User muser;
    private String steamURL;

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
        muser = new User();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        steamurl.setOnClickListener(this);
        loadingPopPoint.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void loadUserDate(String id) {
        //在此发起网络请求获取数据
//        Api.getInstance().getUsers(String.valueOf(Util.get64Id(mid)), userObserver);
        Api.getInstance().getUsers(id,userObserver);
    }

    private void changeView(User u){
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

    private Observer<User> userObserver = new Observer<User>() {
        @Override
        public void onCompleted() {
            msg("Net", "NET_SUCCESS");
            msg("Net","name is "+muser.getResponse().getPlayers().get(0).getPersonaname());
            changeView(muser);
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
                break;
        }
    }

}
