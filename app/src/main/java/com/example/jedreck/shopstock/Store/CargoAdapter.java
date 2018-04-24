
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

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by zjx on 2018/4/20.
 */

public class CargoAdapter extends ArrayAdapter<Cargo> {
    private int resourceId;
    private List<Cargo> tests;
    private Context context;
    LayoutInflater layoutInflater;
    public CargoAdapter(Context context, int textViewResourceId, List<Cargo> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.tests=tests;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
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
            viewHolder.cargoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:13821846482"));
                    context.startActivity(intent);
                }
            });
        } else {
            view = converView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.cargoImage.setImageResource(cargo.getImageid());
        viewHolder.cargoInfo.setText(cargo.getInfo());
        viewHolder.cargoButton.setImageResource(R.drawable.call2);
        return view;
    }
    class ViewHolder
    {
        ImageView cargoImage;
        TextView cargoInfo;
        ImageButton cargoButton;
    }
}

