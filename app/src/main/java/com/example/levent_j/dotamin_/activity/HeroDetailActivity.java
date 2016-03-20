package com.example.levent_j.dotamin_.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.base.BaseActivity;
import com.example.levent_j.dotamin_.utils.Heroes;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import xhome.uestcfei.com.loadingpoppoint.LoadingPopPoint;

/**
 * Created by levent_j on 16-3-15.
 */
public class HeroDetailActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tv_detail_hero_name)
    TextView heroName;
    @Bind(R.id.iv_detail_hero_avater)
    ImageView heroAvater;
    @Bind(R.id.tv_power)
    TextView heroPower;
    @Bind(R.id.tv_agile)
    TextView heroAgile;
    @Bind(R.id.tv_know)
    TextView heroKnow;
    @Bind(R.id.tv_attack)
    TextView heroAttack;
    @Bind(R.id.tv_armor)
    TextView heroArmor;
    @Bind(R.id.tv_hero_description)
    TextView heroDescription;
    @Bind(R.id.skill_1)
    ImageView skillAvater1;
    @Bind(R.id.skill_2)
    ImageView skillAvater2;
    @Bind(R.id.skill_3)
    ImageView skillAvater3;
    @Bind(R.id.skill_4)
    ImageView skillAvater4;
    @Bind(R.id.skill_5)
    ImageView skillAvater5;
    @Bind(R.id.tv_skill_description)
    TextView skillDescription;
    @Bind(R.id.tv_skill_name)
    TextView skillName;
    @Bind(R.id.layout)
    LinearLayout linearLayout;
    @Bind(R.id.loading)
    LoadingPopPoint loadingPopPoint;

    private String getName;
    private int flag = 0;
    private int heroMin;
    private Map<String,String> skillMap;
    private String[] skillNames;

    @Override
    protected void init() {
//        //与parse取得链接
//        Parse.enableLocalDatastore(this);
//        Parse.initialize(this);
        linearLayout.setVisibility(View.INVISIBLE);
        getName = getIntent().getStringExtra("name");
        loadDate(getName);
        skillMap = new HashMap<>();
        skillAvater1.setOnClickListener(this);
        skillAvater2.setOnClickListener(this);
        skillAvater3.setOnClickListener(this);
        skillAvater4.setOnClickListener(this);
        skillAvater5.setOnClickListener(this);

//        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
    }

    private void loadDate(final String getName) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Hero");
        query.whereEqualTo("heroName", getName);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++) {
                        //设置可见
                        loadingPopPoint.setVisibility(View.INVISIBLE);
                        linearLayout.setVisibility(View.VISIBLE);

                        msg("Hero", "NET_SUCCESS,name is " + objects.get(i).getString("heroName"));
                        heroName.setText(objects.get(i).getString("heroName"));
                        heroPower.setText(objects.get(i).getString("heroPow"));
                        heroAgile.setText(objects.get(i).getString("heroAgi"));
                        heroKnow.setText(objects.get(i).getString("heroKno"));
                        heroAttack.setText(objects.get(i).getString("heroAttack"));
                        heroArmor.setText(objects.get(i).getString("heroArmor"));
                        heroDescription.setText(objects.get(i).getString("Description"));
                        heroMin = objects.get(i).getInt("heroMin");
                        int count = objects.get(i).getInt("skillCount");
                        if (count == 4) {
                            skillAvater5.setVisibility(View.INVISIBLE);
                        }
                        skillNames = new String[count];
                        for (int j = 1; j <= count; j++) {
                            skillNames[j - 1] = objects.get(i).getString("heroSkill_" + j);
                            skillMap.put(objects.get(i).getString("heroSkill_" + j), objects.get(i).getString("heroSkill_" + j + "_des"));
                        }
                        //Test
                        for (int j = 1; j <= count; j++) {
                            msg("TestDetail", "name is:" + skillNames[j - 1] + "\nand des is:" + skillMap.get(skillNames[j - 1]));
                        }
                        setSkill(1);
                        int index = 0;
                        for (int j = 0; j < Heroes.HERO_NAME.length; j++) {
                            if (Heroes.HERO_NAME[j].equals(getName)) {
                                index = j;
                                break;
                            }
                        }
                        heroAvater.setBackgroundResource(Heroes.HERO_IMAGE_VERT[index]);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "网络错误", Toast.LENGTH_SHORT).show();
                    msg("Hero", "NET_ERROR,msg is" + e.getMessage());
                }
            }
        });
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_hero_detail;
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.skill_1:
                setSkill(1);
                break;
            case R.id.skill_2:
                setSkill(2);
                break;
            case R.id.skill_3:
                setSkill(3);
                break;
            case R.id.skill_4:
                setSkill(4);
                break;
            case R.id.skill_5:
                setSkill(5);
                break;
        }
    }

    private void setSkill(int i) {
        skillName.setText(skillNames[i-1]);
        skillDescription.setText(skillMap.get(skillNames[i-1]));
    }


}
