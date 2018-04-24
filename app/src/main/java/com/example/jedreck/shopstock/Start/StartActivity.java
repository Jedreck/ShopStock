package com.example.jedreck.shopstock.Start;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jedreck.shopstock.BarCodeActivity.CaptureActivity;
import com.example.jedreck.shopstock.Bean.OutBean;
import com.example.jedreck.shopstock.Bean.StockBean;
import com.example.jedreck.shopstock.FullInfoActivity.FullInfoActivity;
import com.example.jedreck.shopstock.MajorSearch.MainActivity;
import com.example.jedreck.shopstock.OUTPart.OutActivity;
import com.example.jedreck.shopstock.OUTPart.thingsadapter;
import com.example.jedreck.shopstock.R;
import com.example.jedreck.shopstock.Store.StoreMain;
import com.hejunlin.superindicatorlibray.CircleIndicator;
import com.hejunlin.superindicatorlibray.LoopViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartActivity extends AppCompatActivity {

    Intent intent;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    return true;
                case R.id.navigation_in:
                    intent = new Intent(StartActivity.this,StoreMain.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_out:
                    intent = new Intent(StartActivity.this,OutActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };
    private List<StartBean> startList=new ArrayList<>();
    private Startadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        SearchView sv=findViewById(R.id.searchView);
        Button searchbutton=(Button) findViewById(R.id.searchbutton);

        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageView=(ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StartActivity.this, CaptureActivity.class);
                intent.putExtra("flag",CaptureActivity.TO_FULLINFO);
                startActivity(intent);
            }
        });


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        LoopViewPager viewpager = (LoopViewPager) findViewById(R.id.viewpager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        viewpager.setAdapter(new PicAdapter(StartActivity.this));
        viewpager.setLooperPic(true);
        indicator.setViewPager(viewpager);


        initthings();
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new Startadapter(startList);
        recyclerView.setAdapter(adapter);
    }
    private StartBean[] ths={
            new StartBean("良品铺子山楂片","5",R.drawable.shanzha),
            new StartBean("小苏打牙膏","10",R.drawable.sudayagao),
            new StartBean("纤维抹布","1",R.drawable.mabu),
            new StartBean("御泥坊面膜","6",R.drawable.mianmo),
            new StartBean("雀巢咖啡","2",R.drawable.coffee),
            new StartBean("牛奶沐浴乳","8",R.drawable.muyulu),
            new StartBean("蓝月亮洗衣液","4",R.drawable.wash),
            new StartBean("衣架","5",R.drawable.yijia),
            new StartBean("奇异果","33",R.drawable.kiwi),
            new StartBean("梨子","33",R.drawable.pear),
            new StartBean("德芙巧克力","33",R.drawable.dove),
            new StartBean("拖鞋","2",R.drawable.tuoxie),

    };
    public void initthings(){
        startList.clear();
        for(int i=0;i<12;i++){
            startList.add(ths[i]);
        }
    }

}
