package com.example.jedreck.shopstock.OUTPart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jedreck.shopstock.BarCodeActivity.TestScanActivity;
import com.example.jedreck.shopstock.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OutActivity extends AppCompatActivity {

    //private TextView mTextMessage;
    //private DrawerLayout mDrawerLayout;
    private thingsadapter adapter;
    private things[] ths={new things("book",R.drawable.our,"s001",50),
            new things("pen",R.drawable.pencil,"s002",40),
            new things("clock",R.drawable.key,"s003",90),
            new things("shoes",R.drawable.key,"s004",20),
            new things("phone",R.drawable.our,"s005",70)
    };

    private List<things> thingsList=new ArrayList<>();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        initthings();
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new thingsadapter(thingsList);
        recyclerView.setAdapter(adapter);
    }
    public void initthings(){
        thingsList.clear();
        for(int i=0;i<10;i++){
            Random random=new Random();
            int index=random.nextInt(ths.length);
            thingsList.add(ths[index]);
        }
    }

}
