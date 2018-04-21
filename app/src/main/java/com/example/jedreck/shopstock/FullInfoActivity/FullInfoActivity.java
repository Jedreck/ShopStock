package com.example.jedreck.shopstock.FullInfoActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始显示进度条
        loadingDialog.show();
        //获取信息
        CommodityInfoBean commodityInfo = getCommodityInfo();
        //显示信息
        id.setText(commodityInfo.getStock().getId());
        name.setText(commodityInfo.getStock().getName());
        price.setText(commodityInfo.getStock().getPrice());
        stock.setText(commodityInfo.getStock().getStock());
        showEnterInfo(commodityInfo.getEnter());
        showOutInfo(commodityInfo.getOut());
        //关闭进度条
        loadingDialog.onStop();
    }

    private CommodityInfoBean getCommodityInfo() {
        String responseData = "{}";
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = RequestManager.getIDFull(ID);
            Response response = okHttpClient.newCall(request).execute();
            responseData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommodityInfoBean commodityInfoBeans = CommodityInfoBean.json2Objective(responseData);
        return commodityInfoBeans;
    }

    private void showEnterInfo(List<EnterBean> enterBeans){

    }
    private void showOutInfo(List<OutBean> outBeans){

    }
}
