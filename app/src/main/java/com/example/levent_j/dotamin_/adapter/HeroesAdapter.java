package com.example.levent_j.dotamin_.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.levent_j.dotamin_.R;
import com.example.levent_j.dotamin_.utils.Heroes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by levent_j on 16-3-14.
 */
public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.mViewHolder>{

    private Context context;
    private final LayoutInflater layoutInflater;
    private ArrayList<String> heroeslist;


    public HeroesAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        heroeslist = new ArrayList<>();
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.heroes_item,null);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        if (Heroes.HERO_IMAGE_VERT[position]!=0){
            holder.heroName.setText(heroeslist.get(position));
            int max = 470;
            int min = 335;
            Random random = new Random();
            int width = random.nextInt(max)%(max-min+1)+min;
            int height = (int) (width*1.16);
            Picasso.with(context).load(Heroes.HERO_IMAGE_VERT[position]).resize(width,height).into(holder.heroAvater);
        }
    }

    @Override
    public int getItemCount() {
        return heroeslist.size();
    }

    public void initArrayList(List<String> strings){
        this.heroeslist.clear();
        this.heroeslist.addAll(strings);
        notifyDataSetChanged();
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_heroes_name)
        TextView heroName;
        @Bind(R.id.iv_heros_avater)
        ImageView heroAvater;
        @Bind(R.id.card_hero)
        CardView cardView;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,heroeslist.get(getPosition()),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
