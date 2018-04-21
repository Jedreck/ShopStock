package com.example.jedreck.shopstock.Store;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jedreck.shopstock.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Storeyes extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_in:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_out:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeyes);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        sendRequestWithOKHttp();
    }
    private void sendRequestWithOKHttp()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request requst=new Request.Builder().url("http://rb47h9.natappfree.cc/storage/SearchIDLite_Servlet").build();
                    Response response=client.newCall(requst).execute();
                    String responseData=response.body().string();
                    parseJsonWithGSON(responseData);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJsonWithGSON(String jsonData)
    {
        Gson gson=new Gson();
        List<StoreApp> appList=gson.fromJson(jsonData, new TypeToken<List<StoreApp>>() {}.getType());
        for (StoreApp app :appList)
        {
            Log.d("Activity","id is"+app.getId());
            Log.d("Activity","name is"+app.getName());
            Log.d("Activity","price is"+app.getPrice());
            Log.d("Activity","stock is"+app.getStock());
        }
    }
}
