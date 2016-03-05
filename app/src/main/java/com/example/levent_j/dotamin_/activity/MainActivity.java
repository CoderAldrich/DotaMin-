package com.example.levent_j.dotamin_.activity;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.base.BaseActivity;
import com.example.levent_j.dotamin_.fragment.HeroFragment;
import com.example.levent_j.dotamin_.fragment.HistoryFragment;
import com.example.levent_j.dotamin_.fragment.UserFragment;
import com.example.levent_j.dotamin_.utils.InputDialog;
import com.example.levent_j.dotamin_.utils.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import xhome.uestcfei.com.loadingpoppoint.LoadingPopPoint;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;


    public MyFragmentAdapter myFragmentAdapter;
    private static final String[] TITLE = {"User","History","Hero"};
    private int[] tabicon = {R.drawable.ic_user,R.drawable.ic_history,R.drawable.ic_dota};
    private int searchFlag;
    private String searchTitle;

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),this);

        myFragmentAdapter.addFragment(UserFragment.newInstance(TITLE[0]),TITLE[0]);
        myFragmentAdapter.addFragment(HistoryFragment.newInstance(TITLE[1]),TITLE[1]);
        myFragmentAdapter.addFragment(HeroFragment.newInstance(TITLE[2]),TITLE[2]);
        viewPager.setAdapter(myFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        //为tab的标题设置icon
        for (int i=0;i<tabLayout.getTabCount();i++){
            tabLayout.getTabAt(i).setIcon(tabicon[i]);
        }


        //在此处理显示好友信息界面
        String s = getIntent().getStringExtra("id");
        if (s==null){
            msg("Intent","First come");
        }else {
            msg("Intent","again and s is:"+s);
            UserFragment userFragment = (UserFragment) myFragmentAdapter.getItem(viewPager.getCurrentItem());
            userFragment.loadUserDate(s);
            userFragment.loadFrinedsDate(s);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setListener() {
        fab.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            // Handle the camera action
            msg("item", "nav_main");
        } else if (id == R.id.nav_gallery) {
            msg("item", "nav_gallery");
        } else if (id == R.id.nav_slideshow) {
            msg("item", "nav_slideshow");
        } else if (id == R.id.nav_manage) {
            msg("item", "nav_manage");
        } else if (id == R.id.nav_share) {
            msg("item", "nav_share");
        } else if (id == R.id.nav_send) {
            msg("item", "nav_send");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            case R.id.fab:
                final String s = myFragmentAdapter.getTitle(viewPager.getCurrentItem());
                if (s.equals("User")){
                    searchFlag = 1;
                    searchTitle = "steam id";
                }else if (s.equals("History")){
                    searchFlag = 2;
                    searchTitle = "比赛 id";
                }else {
                    searchFlag = 3;
                    searchTitle = "英雄名称";
                }
                new InputDialog.Builder(this)
                        .setTitle("搜索")
                        .setInputMaxWords(9)
                        .setInputHint("在此填入" + searchTitle)
                        .setPositiveButton("搜索", new InputDialog.ButtonActionListener() {
                            @Override
                            public void onClick(CharSequence inputText) {
                                switch (searchFlag){
                                    case 1:
                                        if (inputText.length() != 9) {
                                            Snackbar.make(v, "填写错误！", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                        } else {
                                            String s = inputText.toString().trim();
                                            UserFragment userFragment = (UserFragment) myFragmentAdapter.getItem(viewPager.getCurrentItem());
                                            userFragment.loadUserDate(Util.get64Id(Long.parseLong(s)));
                                            userFragment.loadFrinedsDate(Util.get64Id(Long.parseLong(s)));
                                            userFragment.loadingPopPoint.setVisibility(View.VISIBLE);
                                        }
                                        break;
                                    case 2:
                                        //搜索比赛的活动
                                        break;
                                    case 3:
                                        //搜索英雄的活动
                                        break;
                                }

                            }
                        })
                        .setNegativeButton("取消", new InputDialog.ButtonActionListener() {
                            @Override
                            public void onClick(CharSequence inputText) {
                                //取消之后的动作
                            }
                        })
                        .show();


                msg("url", "url is http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&steamids=" + Util.get64Id(129639720));
                break;
        }

    }

    private class MyFragmentAdapter extends FragmentPagerAdapter{

        private final List<Fragment> fragmentList;
        public final List<String> titleList;
        private Context context;

        public MyFragmentAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
            fragmentList = new ArrayList<>();
            titleList = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position){
//                case 0:
//                    return "User";
//                case 1:
//                    return "History";
//                default:
//                    return "hero";
//            }
//        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        public void addFragment(Fragment fragment,String title){
            fragmentList.add(fragment);
            titleList.add(title);
        }

        public String getTitle(int position){
            switch (position){
                case 0:
                    return "User";
                case 1:
                    return "History";
                default:
                    return "hero";
            }
        }
    }
}
