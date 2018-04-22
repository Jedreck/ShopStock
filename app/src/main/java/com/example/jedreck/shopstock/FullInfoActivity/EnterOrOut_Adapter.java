package com.example.jedreck.shopstock.FullInfoActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jedreck.shopstock.Bean.EnterBean;
import com.example.jedreck.shopstock.Bean.OutBean;
import com.example.jedreck.shopstock.R;

import java.util.List;

public class EnterOrOut_Adapter extends RecyclerView.Adapter<EnterOrOut_Adapter.ViewHolder>{
    public static final int ENTERBEAN_FLAG = 0;
    public static final int OUTBEAN_FLAG = 1;
    private int Flag;
    private List<EnterBean> enterBeans;
    private List<OutBean> outBeans;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.enterandout_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (Flag){
            case ENTERBEAN_FLAG:
                EnterBean enterBean = enterBeans.get(position);
                holder.Image.setImageResource(R.drawable.enterstock);
                holder.time.setText(enterBean.getTime());
                holder.num.setText(enterBean.getNum());
                holder.price.setText(enterBean.getPrice_in());
                break;
            case OUTBEAN_FLAG:
                OutBean outBean = outBeans.get(position);
                holder.Image.setImageResource(R.drawable.outstock);
                holder.time.setText(outBean.getTime());
                holder.num.setText(outBean.getNum());
                holder.price.setText(outBean.getPrice_out());
                break;
        }
    }

    @Override
    public int getItemCount() {
        switch (Flag){
            case ENTERBEAN_FLAG:
                return enterBeans.size();
            case OUTBEAN_FLAG:
                return outBeans.size();
        }
        return enterBeans.size();
    }

    public EnterOrOut_Adapter(List<?> list, int flag){
        Flag=flag;
        if(flag==ENTERBEAN_FLAG){
            enterBeans= (List<EnterBean>) list;
        }
        else{
            outBeans= (List<OutBean>) list;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView time;
        TextView num;
        TextView price;
        public ViewHolder(View view){
            super(view);
            Image=view.findViewById(R.id.EnterOrOutImage);
            time=view.findViewById(R.id.DealTime);
            num=view.findViewById(R.id.DealNum);
            price=view.findViewById(R.id.DealPrice);
        }
    }
}
