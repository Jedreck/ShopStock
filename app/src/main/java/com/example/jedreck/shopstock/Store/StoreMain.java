package com.example.jedreck.shopstock.Store;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jedreck.shopstock.BarCodeActivity.CaptureActivity;
import com.example.jedreck.shopstock.Bean.StockBean;
import com.example.jedreck.shopstock.Internet.RequestManager;
import com.example.jedreck.shopstock.MajorSearch.MainActivity;
import com.example.jedreck.shopstock.OUTPart.OutActivity;
import com.example.jedreck.shopstock.R;
import com.example.jedreck.shopstock.Start.StartActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class StoreMain extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextMessage;
    private List<Cargo> cargoList=new ArrayList<>();
    private EditText editText;
    String input;
    Intent intent;
    ImageButton button1;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    intent=new Intent(StoreMain.this, StartActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_in:
                    return true;
                case R.id.navigation_out:
                    intent=new Intent(StoreMain.this, OutActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_main);
        ActionBar actionBar=getSupportActionBar();
        button1=findViewById(R.id.scan);
        if (actionBar!=null)
        {
            actionBar.hide();
        }
        initCargo();
        CargoAdapter adapter=new CargoAdapter(StoreMain.this, R.layout.item,cargoList);
        ListView listView=(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Button button=(Button) findViewById(R.id.ruku);
        editText=(EditText) findViewById(R.id.shuru);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.ruku)
        {
            input=editText.getText().toString();
            if (isNumber(input)==false) {
                Toast.makeText(StoreMain.this, "条形码号输入错误，请重新输入", Toast.LENGTH_SHORT).show();
                showResponse("");
            }
            else
            sendRequestWithOkHttp();
        }
        if(v.getId()==R.id.scan)
        {
            intent = new Intent(StoreMain.this, CaptureActivity.class);
            intent.putExtra("flag",CaptureActivity.TO_SEARCHLITE);
            startActivity(intent);
        }
    }
    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("id", input)
                            .build();
                    Request request = RequestManager.getIDLite(requestBody);
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                  //  Log.d(TAG, "run: OkHttp response --- "+responseData);
                    StockBean stockBean = StockBean.josn2Objective(responseData);
                    String r = stockBean.getId();
                    if (r.equals("000000")) {
                        Intent intent = new Intent(StoreMain.this, Storeno.class);
                        intent.putExtra("id", input);
                        startActivity(intent);
                    } else {
                        Intent intent1 = new Intent(StoreMain.this, Storeyes.class);
                        String s1, s2, s3, s4;
                        s1 = stockBean.getId();
                        intent1.putExtra("id", s1);
                        s2 = stockBean.getName();
                        intent1.putExtra("name", s2);
                        s3 = stockBean.getPrice();
                        intent1.putExtra("price", s3);
                        s4 = stockBean.getStock();
                        intent1.putExtra("stock", s4);
                        startActivity(intent1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private boolean isNumber(String s)
    {
        if(s.length()!=13)
            return false;
        for (int i=s.length();--i>=0;)
        {
            if(!Character.isDigit(s.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
//    public void parseJsonWithGSON(String jsonData,final String response)
//    {
//        Gson gson=new Gson();
//        final List<StoreApp> appList=gson.fromJson(jsonData, new TypeToken<List<StoreApp>>() {}.getType());
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//               // editText.setText(response);
//                for (StoreApp app :appList)
//                {
//                    editText.setText(app.getId());
//                }
//            }
//        });
//    }
    public void showResponse(final String s)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                editText.setText(s);
            }
        });
    }
    private void initCargo()
    {
        Cargo suannai=new Cargo("新希望airsnow轻爱常温原味酸奶整箱200g*12盒 巴氏杀菌酸牛奶40.0/件",R.drawable.suannai);
        cargoList.add(suannai);
        Cargo gangbi=new Cargo("包邮批发爱好墨囊钢笔小学生抽墨水墨囊两用学生写字练字钢笔100.0/件",R.drawable.gangbi);
        cargoList.add(gangbi);
        Cargo maojin=new Cargo("洁丽雅毛巾纯棉男士英伦全棉洗脸批发吸水大毛巾家用成人柔软面巾16.0/件",R.drawable.maojin);
        cargoList.add(maojin);
        Cargo wanju=new Cargo("恐龙抱枕公仔玩偶毛绒玩具女生可爱萌韩国睡觉抱女孩布娃娃礼物",R.drawable.wanju);
        cargoList.add(wanju);
        Cargo jingzi=new Cargo("欧式双面大号台式高清化妆镜随身便携公主梳妆镜美容院折叠小镜子",R.drawable.jingzi);
        cargoList.add(jingzi);
        Cargo benzi=new Cargo("十二星座小清新大学生笔记本加厚复古韩国日记本子简约创意记事本40.0/件",R.drawable.benzi);
        cargoList.add(benzi);
        Cargo yagao=new Cargo("高露洁光感劲白牙膏120g*6 家庭大包装美白去除牙渍30.0/件",R.drawable.yagao);
        cargoList.add(yagao);
        Cargo binggan=new Cargo("雀巢脆脆鲨威化奶香巧克力夹心饼干500g散装休闲食品零食包邮喜糖20.0/件",R.drawable.binggan);
        cargoList.add(binggan);
    }
}
