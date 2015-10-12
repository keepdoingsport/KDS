package com.example.administrator.kdsdemo01.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.kdsdemo01.R;
import com.example.administrator.kdsdemo01.model.Gym;

import java.util.List;

/**
 * Created by Administrator on 2015/9/12.
 */
public class GymListAdapter extends RecyclerView.Adapter<GymListAdapter.CardViewHolder>{
    Context mContex;
    List<Gym> mGymList;
    public GymListAdapter(Context context,List<Gym> mGymList){
        this.mContex=context;
        this.mGymList=mGymList;
    }
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(mContex)
                .inflate(R.layout.card_gym,viewGroup,false);
        CardViewHolder cardViewHolder=new CardViewHolder(itemView);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Gym gym=mGymList.get(position);
        Glide.with(mContex).load(gym.getAddress()).placeholder(R.mipmap.loading).into(holder.mImageView);
        holder.mTitleView.setText(gym.getId()+"");
    }



    @Override
    public int getItemCount() {
        return mGymList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
    ImageView mImageView;
    TextView mTitleView;
        public CardViewHolder(View view){
            super(view);
            mImageView= (ImageView)view.findViewById(R.id.iv_cover);
            mTitleView=(TextView)view.findViewById(R.id.tv_title);
        }

    }

    public Gym getGym(int pos) {
        return mGymList.get(pos);
    }
}
