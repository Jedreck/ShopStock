
package com.example.jedreck.shopstock.Store;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jedreck.shopstock.R;

import java.util.List;

/**
 * Created by zjx on 2018/4/20.
 */

public class CargoAdapter extends ArrayAdapter<Cargo> {
    private int resourceId;

    public CargoAdapter(Context context, int textViewResourceId, List<Cargo> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        Cargo cargo = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (converView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.cargoImage = (ImageView) view.findViewById(R.id.cargo_image);
            viewHolder.cargoInfo = (TextView) view.findViewById(R.id.cargo_info);
            viewHolder.cargoButton = (ImageButton) view.findViewById(R.id.ImageButton);
            view.setTag(viewHolder);
        } else {
            view = converView;
            viewHolder=(ViewHolder)view.getTag();
        }
//        cargoImage = (ImageView) view.findViewById(R.id.cargo_image);
//        cargoInfo = (TextView) view.findViewById(R.id.cargo_info);
//        cargoButton = (ImageButton) view.findViewById(R.id.ImageButton);
        viewHolder.cargoImage.setImageResource(cargo.getImageid());
        viewHolder.cargoInfo.setText(cargo.getInfo());
        viewHolder.cargoButton.setImageResource(R.drawable.call);
        return view;
    }
    class ViewHolder
    {
        ImageView cargoImage;
        TextView cargoInfo;
        ImageButton cargoButton;
    }
}

