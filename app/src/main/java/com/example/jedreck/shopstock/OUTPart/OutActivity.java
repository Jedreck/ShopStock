package com.example.jedreck.shopstock.OUTPart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import com.example.jedreck.shopstock.Bean.OutBean;
import com.example.jedreck.shopstock.Internet.RequestManager;
import com.example.jedreck.shopstock.MajorSearch.MainActivity;
import com.example.jedreck.shopstock.R;
import com.example.jedreck.shopstock.Start.StartActivity;
import com.example.jedreck.shopstock.Store.StoreMain;

import java.util.ArrayList;
import java.util.List;


import okhttp3.OkHttpClient;
import okhttp3.Response;

public class OutActivity extends AppCompatActivity {

    private thingsadapter adapter;
    Intent intent;
    private List<OutBean> thingsList=new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    intent = new Intent(OutActivity.this, StartActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_in:
                    intent = new Intent(OutActivity.this,StoreMain.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_out:
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new thingsadapter(thingsList);
        recyclerView.setAdapter(adapter);
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
