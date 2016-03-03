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

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.base.BaseActivity;
import com.example.levent_j.dotamin_.fragment.HeroFragment;
import com.example.levent_j.dotamin_.fragment.HistoryFragment;
import com.example.levent_j.dotamin_.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

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

    private MyFragmentAdapter myFragmentAdapter;
    private static final String[] TITLE = {"User","History","Hero"};

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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                String s = (String) myFragmentAdapter.getPageTitle(viewPager.getCurrentItem());
                Snackbar.make(v, "搜索"+s+"相关的内容", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }

    }

    private class MyFragmentAdapter extends FragmentPagerAdapter{

        private final List<Fragment> fragmentList;
        private final List<String> titleList;
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

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "用户主页";
                case 1:
                    return "比赛记录";
                case 2:
                    return "英雄数据";
                default:
                    return null;
            }
        }

        public void addFragment(Fragment fragment,String title){
            fragmentList.add(fragment);
            titleList.add(title);
        }
    }
}
