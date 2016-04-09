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
    @Bind(R.id.iv_skill_1)
    ImageView skillAvater1;
    @Bind(R.id.iv_skill_2)
    ImageView skillAvater2;
    @Bind(R.id.iv_skill_3)
    ImageView skillAvater3;
    @Bind(R.id.iv_skill_4)
    ImageView skillAvater4;
    @Bind(R.id.iv_skill_5)
    ImageView skillAvater5;
    @Bind(R.id.tv_skill_description)
    TextView skillDescription;
    @Bind(R.id.tv_skill_name)
    TextView skillName;
    @Bind(R.id.layout)
    LinearLayout linearLayout;
    @Bind(R.id.loading)
    LoadingPopPoint loadingPopPoint;

    private final ThreadLocal<String> nameGetted = new ThreadLocal<>();
    private int heroIndex;
    private int heroMin;
    private int skillCount;
    private Map<String,String> skillMap;
    private String[] skillNames;
    private boolean isVisible;

    @Override
    protected void init() {
        linearLayout.setVisibility(View.INVISIBLE);
        nameGetted.set(getIntent().getStringExtra("name"));
        heroIndex = getHeroIndex(nameGetted.get());
        loadDate(nameGetted.get());
        skillMap = new HashMap<>();
        skillAvater1.setOnClickListener(this);
        skillAvater2.setOnClickListener(this);
        skillAvater3.setOnClickListener(this);
        skillAvater4.setOnClickListener(this);
        skillAvater5.setOnClickListener(this);
        heroDescription.setOnClickListener(this);
        heroDescription.setMovementMethod(new ScrollingMovementMethod());
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
                        loadingPopPoint.setVisibility(View.INVISIBLE);
                        linearLayout.setVisibility(View.VISIBLE);

                        msg("Hero", "NET_SUCCESS,name is " + objects.get(i).getString("heroName"));
                        heroName.setText(objects.get(i).getString("heroName"));
                        heroPower.setText(objects.get(i).getString("heroPow"));
                        heroAgile.setText(objects.get(i).getString("heroAgi"));
                        heroKnow.setText(objects.get(i).getString("heroKno"));
                        heroAttack.setText(objects.get(i).getString("heroAttack"));
                        heroArmor.setText(objects.get(i).getString("heroArmor"));
                        heroDescription.setText(objects.get(i).getString("heroDescription"));
                        heroMin = objects.get(i).getInt("heroMin");

                        skillCount = objects.get(i).getInt("skillCount");
                        skillNames = new String[skillCount];
                        for (int j = 1; j <= skillCount; j++) {
                            skillNames[j - 1] = objects.get(i).getString("heroSkill_" + j);
                            skillMap.put(objects.get(i).getString("heroSkill_" + j), objects.get(i).getString("heroSkill_" + j + "_des"));
                        }

                        if (skillCount == 4) {
                            skillAvater5.setVisibility(View.GONE);
                        }else {
                            skillAvater5.setVisibility(View.VISIBLE);
                            setSkill(5, skillAvater5);
                        }
                        setSkill(4,skillAvater4);
                        setSkill(3,skillAvater3);
                        setSkill(2, skillAvater2);
                        setSkill(1, skillAvater1);
                        skillAvater1.setAlpha(180);
                        setHeroMain(heroMin);

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
                }
            }
        });
    }


    private void setHeroMain(int heroMin) {
        switch (heroMin){
            case 1:
                heroPower.setText(heroPower.getText().toString().concat("(主)"));
                break;
            case 2:
                heroAgile.setText(heroAgile.getText().toString().concat("(主)"));
                break;
            case 3:
                heroKnow.setText(heroKnow.getText().toString().concat("(主)"));
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
        skillAvater1.setAlpha(255);
        skillAvater2.setAlpha(255);
        skillAvater3.setAlpha(255);
        skillAvater4.setAlpha(255);
        skillAvater5.setAlpha(255);
        switch (v.getId()){
            case R.id.iv_skill_1:
                setSkill(1, skillAvater1);
                skillAvater1.setAlpha(160);
                break;
            case R.id.iv_skill_2:
                setSkill(2, skillAvater2);
                skillAvater2.setAlpha(160);
                break;
            case R.id.iv_skill_3:
                setSkill(3,skillAvater3);
                skillAvater3.setAlpha(160);
                break;
            case R.id.iv_skill_4:
                setSkill(4,skillAvater4);
                skillAvater4.setAlpha(160);
                break;
            case R.id.iv_skill_5:
                setSkill(5,skillAvater5);
                skillAvater5.setAlpha(180);
                break;
            case R.id.tv_hero_description:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    if (heroDescription.getMaxLines()==5){
                        heroDescription.setMaxLines(100);
                    }else {
                        heroDescription.setMaxLines(5);
                    }
                }
                break;

        }
    }

    private void setSkill(int i,ImageView imageView) {
        skillName.setText(skillNames[i-1]);
        skillDescription.setText(skillMap.get(skillNames[i-1]));
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
