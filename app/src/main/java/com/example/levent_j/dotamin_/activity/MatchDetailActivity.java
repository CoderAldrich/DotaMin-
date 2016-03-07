package com.example.levent_j.dotamin_.activity;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.base.BaseActivity;
import com.example.levent_j.dotamin_.pojo.Match;
import com.example.levent_j.dotamin_.pojo.MatchResult;

import butterknife.Bind;

/**
 * Created by levent_j on 16-3-7.
 */
public class MatchDetailActivity extends BaseActivity{
    MatchResult matchResult;

    @Override
    protected void init() {
        //获取比赛结果
        matchResult = getIntent().getParcelableExtra("match");
        msg("Details","id is"+matchResult.getMatchId());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_match_detail;
    }

    @Override
    protected void setListener() {

    }
}
