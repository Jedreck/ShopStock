package com.example.jedreck.shopstock.OUTPart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jedreck.shopstock.BarCodeActivity.TestScanActivity;
import com.example.jedreck.shopstock.R;

import java.util.ArrayList;
import java.util.List;


import okhttp3.OkHttpClient;
import okhttp3.Response;

public class OutActivity extends AppCompatActivity {

    //private TextView mTextMessage;
    //private DrawerLayout mDrawerLayout;
    private thingsadapter adapter;


    private List<OutBean> thingsList=new ArrayList<>();
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    //mTextMessage.setText(R.string.title_shop);
                    return true;
                case R.id.navigation_in:
                    //mTextMessage.setText(R.string.title_in);
                    Intent intent = new Intent(OutActivity.this, TestScanActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_out:
                    mTextMessage.setText(R.string.title_out);
                    return true;
            }
            return false;
        }
    };
    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        sendRequestWithOkHttp();
    }

    private void sendRequestWithOkHttp() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client=new OkHttpClient();
                    okhttp3.Request request= RequestManager.getSaleWell();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    thingsList.clear();
                    thingsList=OutBean.json2Objectives(responseData);
                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private  void showResponse(final String responseData)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                adapter=new thingsadapter(thingsList);
                recyclerView.setAdapter(adapter);
            }
        });
    }


}
