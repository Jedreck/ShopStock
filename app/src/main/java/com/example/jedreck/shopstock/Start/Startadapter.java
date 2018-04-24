package com.example.jedreck.shopstock.Start;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.jedreck.shopstock.R;

import java.util.List;

/**
 * Created by i on 2018/4/23.
 */

public class Startadapter  extends RecyclerView.Adapter<Startadapter.ViewHolder> {

    private Context mContext;
    private List<StartBean> mstartlist;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView startimage;
        TextView startname;
        public ViewHolder(View view){
            super(view);
            cardView=(CardView) view;
            startimage=(ImageView) cardView.findViewById(R.id.things_image);
            startname=(TextView) cardView.findViewById(R.id.things_name);

        }
    }
    public Startadapter(List<StartBean> startlist){
        mstartlist=startlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.start_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        StartBean th=mstartlist.get(position);
        String context=th.Getname()+System.getProperty("line.separator")+"剩余库存:"+
                th.Getstock();
        holder.startname.setText(context);
        Glide.with(mContext).load(th.Getimageid()).into(holder.startimage);
    }
    @Override
    public int getItemCount(){
        return mstartlist.size();
    }
}
