package com.example.jedreck.shopstock.Store;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.Request;
import com.example.jedreck.shopstock.Internet.RequestManager;
import com.example.jedreck.shopstock.R;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Storeno extends AppCompatActivity implements View.OnClickListener{


    private TextView mTextMessage;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private Button button1;
    private Button button2;
    String s1,s2,s3,s4,s5,s6;
    String re;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_in:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_out:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_shop:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeno);
        editText1 = (EditText) findViewById(R.id.id2);
        editText2 = (EditText) findViewById(R.id.name2);
        editText3 = (EditText) findViewById(R.id.price2);
        editText4 = (EditText) findViewById(R.id.stock2);
        editText5 = (EditText) findViewById(R.id.number2);
        editText6 = (EditText) findViewById(R.id.pricein2);
        textView1 = (TextView) findViewById(R.id.id1);
        textView2 = (TextView) findViewById(R.id.name1);
        textView3 = (TextView) findViewById(R.id.price1);

        textView4 = (TextView) findViewById(R.id.stock1);
        textView5 = (TextView) findViewById(R.id.number1);
        textView6 = (TextView) findViewById(R.id.pricein1);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        Intent intent = getIntent();
        s1 = intent.getStringExtra("id");
        showResponse(s1);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.button1)
        {
            Intent intent=new Intent(Storeno.this,StoreMain.class);
            startActivity(intent);
        }
        if (v.getId()==R.id.button2)
        {
            s2= editText2.getText().toString();
            s3= editText3.getText().toString();
            s4= editText4.getText().toString();
            s5 = editText5.getText().toString();
            s6 = editText6.getText().toString();
            sendRequestWithOKHttp();
                Toast.makeText(Storeno.this,"入库成功",Toast.LENGTH_SHORT).show();
        }
    }
    public void showResponse(final String s1)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                editText1.setText(s1);
                editText4.setText("0");
            }
        });
    }
    private void sendRequestWithOKHttp()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    OkHttpClient client= new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("id",s1)
                            .add("name",s2)
                            .add("price",s3)
                            .add("num",s5)
                            .add("price_in",s6)
                            .build();
                    okhttp3.Request request = RequestManager.getEnterStock(requestBody);
                    Response response=client.newCall(RequestManager.getEnterStock(requestBody)).execute();
                     re=response.body().string();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
