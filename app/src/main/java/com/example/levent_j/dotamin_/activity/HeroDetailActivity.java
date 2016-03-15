package com.example.levent_j.dotamin_.activity;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.base.BaseActivity;
import com.example.levent_j.dotamin_.pojo.Hero;
import com.example.levent_j.dotamin_.utils.Heroes;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;

/**
 * Created by levent_j on 16-3-15.
 */
public class HeroDetailActivity extends BaseActivity{
    @Bind(R.id.tv_detail_hero_name)
    TextView heroName;
    @Bind(R.id.iv_detail_hero_avater)
    ImageView heroAvater;

    private String getName;
    private int flag = 0;

    @Override
    protected void init() {
//        //与parse取得链接
//        Parse.enableLocalDatastore(this);
//        Parse.initialize(this);

        getName = getIntent().getStringExtra("name");
        loadDate(getName);

//        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
    }

    private void loadDate(final String getName) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Hero");
        query.whereEqualTo("heroName",getName);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++) {
                        msg("Hero", "NET_SUCCESS,name is " + objects.get(i).getString("heroName"));
                        heroName.setText(objects.get(i).getString("heroName"));
                        int index=0;
                        for (int j=0;j< Heroes.HERO_NAME.length;j++){
                            if (Heroes.HERO_NAME[j].equals(getName)){
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
}
