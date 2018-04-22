package com.example.jedreck.shopstock.Store;

import android.content.Context;
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
    public CargoAdapter(Context context, int textViewResourceId, List<Cargo> objects)
        {
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
        }
@Override
public View getView(int position, View converView, ViewGroup parent)
        {
        Cargo cargo=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView cargoImage=(ImageView) view.findViewById(R.id.cargo_image);
        TextView cargoInfo =(TextView) view.findViewById(R.id.cargo_info);
        ImageButton cargoButton=(ImageButton) view.findViewById(R.id.ImageButton);
        cargoImage.setImageResource(cargo.getImageid());
        cargoInfo.setText(cargo.getInfo());
        cargoButton.setImageResource(R.drawable.call);
        return view;
        }
}
