package com.example.jedreck.shopstock.MajorSearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jedreck.shopstock.Bean.StockBean;
import com.example.jedreck.shopstock.R;

import java.util.List;

public class StockBeanAdapter extends ArrayAdapter<StockBean> {

    private int resourceId;

    public StockBeanAdapter(Context context, int textViewResourceId,
                        List<StockBean> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    /*每个子项滚动到屏幕中间时调用，调用一个StockBean实例加载布局*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StockBean stockBean = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
//            view = LayoutInflater.from(getContext()).inflate(R.layout.stockbean_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.pic=view.findViewById(R.id.pic);
            viewHolder.name = (TextView) view.findViewById (R.id.name);
            viewHolder.price=view.findViewById(R.id.price);
            viewHolder.stock=view.findViewById(R.id.stock);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.pic.setImageResource(R.drawable.key);
        viewHolder.name.setText(stockBean.getName());
        viewHolder.price.setText(stockBean.getPrice());
        viewHolder.stock.setText(stockBean.getStock());
        return view;
    }

    class ViewHolder {
        ImageView pic;
        TextView name;
        TextView price;
        TextView stock;

    }

}
