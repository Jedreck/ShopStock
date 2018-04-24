package com.example.jedreck.shopstock.OUTPart;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jedreck.shopstock.Bean.OutBean;
import com.example.jedreck.shopstock.R;

import java.util.List;

/**
 * Created by i on 2018/4/19.
 */
public class thingsadapter extends RecyclerView.Adapter<thingsadapter.ViewHolder>{
    private Context mContext;
    private List<OutBean> mthingslist;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView thingsimage;
        TextView thingsname;
        public ViewHolder(View view){
            super(view);
            cardView=(CardView) view;
            thingsimage=(ImageView) cardView.findViewById(R.id.things_image);
            thingsname=(TextView) cardView.findViewById(R.id.things_name);

        }
    }
    public thingsadapter(List<OutBean> thingslist){
        mthingslist=thingslist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.things_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        OutBean th=mthingslist.get(position);
        String context="商品编号："+th.getId()+System.getProperty("line.separator")+"商品名称："+th.getName()+System.getProperty("line.separator")+
                "出货时间:"+th.getTime()+System.getProperty("line.separator")+ "商品单价："+th.getPrice_out()+System.getProperty("line.separator")+
                "销量:"+th.getNum()+System.getProperty("line.separator");
        holder.thingsname.setText(context);
    }
    @Override
    public int getItemCount(){
        return mthingslist.size();
    }
}
