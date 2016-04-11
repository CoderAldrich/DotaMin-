package com.example.levent_j.dotamin_.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.method.ScrollingMovementMethod;
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
import java.io.IOException;
import java.io.InputStream;
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
    TextView mHeroName;
    @Bind(R.id.iv_detail_hero_avater)
    ImageView mHeroAvater;
    @Bind(R.id.tv_power)
    TextView mHeroPower;
    @Bind(R.id.tv_agile)
    TextView mHroAgile;
    @Bind(R.id.tv_know)
    TextView mHeroKnow;
    @Bind(R.id.tv_attack)
    TextView mHeroAttack;
    @Bind(R.id.tv_armor)
    TextView mHeroArmor;
    @Bind(R.id.tv_hero_description)
    TextView mHeroDescription;
    @Bind(R.id.iv_skill_1)
    ImageView mSkillAvater1;
    @Bind(R.id.iv_skill_2)
    ImageView mSkillAvater2;
    @Bind(R.id.iv_skill_3)
    ImageView mSkillAvater3;
    @Bind(R.id.iv_skill_4)
    ImageView mSkillAvater4;
    @Bind(R.id.iv_skill_5)
    ImageView mSkillAvater5;
    @Bind(R.id.tv_skill_description)
    TextView mSkillDescription;
    @Bind(R.id.tv_skill_name)
    TextView mSkillName;
    @Bind(R.id.layout)
    LinearLayout mLinearLayout;
    @Bind(R.id.loading)
    LoadingPopPoint mPopPoint;

    private final ThreadLocal<String> nameGetted = new ThreadLocal<>();
    private int heroIndex;
    private int heroMin;
    private int skillCount;
    private Map<String,String> skillMap;
    private String[] skillNames;
    private boolean isVisible;

    @Override
    protected void init() {
        mLinearLayout.setVisibility(View.INVISIBLE);
        nameGetted.set(getIntent().getStringExtra("name"));
        heroIndex = getHeroIndex(nameGetted.get());
        loadDate(nameGetted.get());
        skillMap = new HashMap<>();
        mSkillAvater1.setOnClickListener(this);
        mSkillAvater2.setOnClickListener(this);
        mSkillAvater3.setOnClickListener(this);
        mSkillAvater4.setOnClickListener(this);
        mSkillAvater5.setOnClickListener(this);
        mHeroDescription.setOnClickListener(this);
        mHeroDescription.setMovementMethod(new ScrollingMovementMethod());
        isVisible = false;
    }

    private int getHeroIndex(String getName) {
        for (int i=0;i<Heroes.HERO_NAME.length;i++){
            if (getName.equals(Heroes.HERO_NAME[i])){
                return i;
            }
        }
        return 0;
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
                        mPopPoint.setVisibility(View.INVISIBLE);
                        mLinearLayout.setVisibility(View.VISIBLE);

                        msg("Hero", "NET_SUCCESS,name is " + objects.get(i).getString("heroName"));
                        mHeroName.setText(objects.get(i).getString("heroName"));
                        mHeroPower.setText(objects.get(i).getString("heroPow"));
                        mHroAgile.setText(objects.get(i).getString("heroAgi"));
                        mHeroKnow.setText(objects.get(i).getString("heroKno"));
                        mHeroAttack.setText(objects.get(i).getString("heroAttack"));
                        mHeroArmor.setText(objects.get(i).getString("heroArmor"));
                        mHeroDescription.setText(objects.get(i).getString("heroDescription"));
                        heroMin = objects.get(i).getInt("heroMin");

                        skillCount = objects.get(i).getInt("skillCount");
                        skillNames = new String[skillCount];
                        for (int j = 1; j <= skillCount; j++) {
                            skillNames[j - 1] = objects.get(i).getString("heroSkill_" + j);
                            skillMap.put(objects.get(i).getString("heroSkill_" + j), objects.get(i).getString("heroSkill_" + j + "_des"));
                        }

                        if (skillCount == 4) {
                            mSkillAvater5.setVisibility(View.GONE);
                        }else {
                            mSkillAvater5.setVisibility(View.VISIBLE);
                            setSkill(5, mSkillAvater5);
                        }
                        setSkill(4, mSkillAvater4);
                        setSkill(3, mSkillAvater3);
                        setSkill(2, mSkillAvater2);
                        setSkill(1, mSkillAvater1);
                        mSkillAvater1.setAlpha(180);
                        setHeroMain(heroMin);

                        int index = 0;
                        for (int j = 0; j < Heroes.HERO_NAME.length; j++) {
                            if (Heroes.HERO_NAME[j].equals(getName)) {
                                index = j;
                                break;
                            }
                        }
                        mHeroAvater.setBackgroundResource(Heroes.HERO_IMAGE_VERT[index]);

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "网络错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void setHeroMain(int heroMin) {
        switch (heroMin){
            case 1:
                mHeroPower.setText(mHeroPower.getText().toString().concat("(主)"));
                break;
            case 2:
                mHroAgile.setText(mHroAgile.getText().toString().concat("(主)"));
                break;
            case 3:
                mHeroKnow.setText(mHeroKnow.getText().toString().concat("(主)"));
                break;
        }
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
        mSkillAvater1.setAlpha(255);
        mSkillAvater2.setAlpha(255);
        mSkillAvater3.setAlpha(255);
        mSkillAvater4.setAlpha(255);
        mSkillAvater5.setAlpha(255);
        switch (v.getId()){
            case R.id.iv_skill_1:
                setSkill(1, mSkillAvater1);
                mSkillAvater1.setAlpha(160);
                break;
            case R.id.iv_skill_2:
                setSkill(2, mSkillAvater2);
                mSkillAvater2.setAlpha(160);
                break;
            case R.id.iv_skill_3:
                setSkill(3, mSkillAvater3);
                mSkillAvater3.setAlpha(160);
                break;
            case R.id.iv_skill_4:
                setSkill(4, mSkillAvater4);
                mSkillAvater4.setAlpha(160);
                break;
            case R.id.iv_skill_5:
                setSkill(5, mSkillAvater5);
                mSkillAvater5.setAlpha(180);
                break;
            case R.id.tv_hero_description:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    if (mHeroDescription.getMaxLines()==5){
                        mHeroDescription.setMaxLines(100);
                    }else {
                        mHeroDescription.setMaxLines(5);
                    }
                }
                break;

        }
    }

    private void setSkill(int i,ImageView imageView) {
        mSkillName.setText(skillNames[i - 1]);
        mSkillDescription.setText(skillMap.get(skillNames[i - 1]));
        setSkillAvater(heroIndex,i,imageView);
    }

    private void setSkillAvater(int heroId,int skillId,ImageView imageView){
        try {
            InputStream inputStream = getAssets().open(heroId+"/"+skillId+".png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(bitmap);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
