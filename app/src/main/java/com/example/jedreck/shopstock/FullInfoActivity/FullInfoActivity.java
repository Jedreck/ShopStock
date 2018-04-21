package com.example.jedreck.shopstock.FullInfoActivity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jedreck.shopstock.Bean.CommodityInfoBean;
import com.example.jedreck.shopstock.Bean.EnterBean;
import com.example.jedreck.shopstock.Bean.OutBean;
import com.example.jedreck.shopstock.Internet.RequestManager;
import com.example.jedreck.shopstock.R;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FullInfoActivity extends Activity {

    private LoadingDialog loadingDialog;
    private String ID;
    private TextView id;
    private TextView name;
    private TextView price;
    private TextView stock;
    private String responseData = "{}";
    private CommodityInfoBean commodityInfoBean;
    private static final int MESSAGE_FLAG = 1;

    @SuppressLint("HandlerLeak")//接受线程返回的东西
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_FLAG:
//                    responseData = msg.getData().getString("responseData");
                    Log.d("responseData", "handleMessage: " + responseData);
                    showInfo();
                    loadingDialog.onStop();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_info);
        //获取相关必要资源
        loadingDialog = new LoadingDialog(FullInfoActivity.this);
        loadingDialog.show();
        Intent intent = getIntent();
        ID = intent.getStringExtra("id");
        id = findViewById(R.id.FullInfo_ID_Text);
        name = findViewById(R.id.FullInfo_Name_Text);
        price = findViewById(R.id.FullInfo_Price_Text);
        stock = findViewById(R.id.FullInfo_Stock_Text);
        Log.d("FullInfoActivity", "onCreate: "+ID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("FullInfoActivity", "inStart: "+ID);
        //开始显示进度条
        loadingDialog.show();
        //获取信息
        startGetInfo();
    }

    private void startGetInfo() {
        Log.d("FullInfoActivity", "startGetInfo: "+ID);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = RequestManager.getIDFull(ID);
                    Response response = okHttpClient.newCall(request).execute();
                    responseData = response.body().string();
                    Log.d("responseData", "run1: " + responseData);
                    Message message = new Message();
                    message.what = MESSAGE_FLAG;
                    Bundle bundle = new Bundle();
                    bundle.putString("responseData",responseData);
                    Log.d("responseData", "run2: " + responseData);
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showInfo(){
        //显示信息
        commodityInfoBean=CommodityInfoBean.json2Objective(responseData);
        if(commodityInfoBean==null){
            Toast.makeText(this,"搜索无此商品",Toast.LENGTH_SHORT).show();
//            this.onDestroy();
            finish();
            return;
        }
        id.setText(commodityInfoBean.getStock().getId());
        name.setText(commodityInfoBean.getStock().getName());
        price.setText(commodityInfoBean.getStock().getPrice());
        stock.setText(commodityInfoBean.getStock().getStock());
        showEnterInfo(commodityInfoBean.getEnter());
        showOutInfo(commodityInfoBean.getOut());
    }

    private void showEnterInfo(List<EnterBean> enterBeans) {
        RecyclerView recyclerView = findViewById(R.id.ShowEntry_List);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        EnterOrOut_Adapter enterOrOut_adapter = new EnterOrOut_Adapter(enterBeans, EnterOrOut_Adapter.ENTERBEAN_FLAG);
        recyclerView.setAdapter(enterOrOut_adapter);
    }

    private void showOutInfo(List<OutBean> outBeans) {
        RecyclerView recyclerView = findViewById(R.id.ShowOut_List);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        EnterOrOut_Adapter enterOrOut_adapter = new EnterOrOut_Adapter(outBeans, EnterOrOut_Adapter.OUTBEAN_FLAG);
        recyclerView.setAdapter(enterOrOut_adapter);
    }
}
