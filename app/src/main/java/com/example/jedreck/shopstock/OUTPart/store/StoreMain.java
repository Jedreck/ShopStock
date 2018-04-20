package com.example.jedreck.shopstock.OUTPart.store;

import android.drm.DrmStore;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jedreck.shopstock.R;

import java.util.ArrayList;
import java.util.List;

public class StoreMain extends AppCompatActivity {

    private TextView mTextMessage;
    private List<Cargo> cargoList=new ArrayList<>();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop:
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_in:
//                    mTextMessage.setText(R.string.title_dashboard);
//                    mTextMessage.setText(R.string.title_in);
                    return true;
                case R.id.navigation_out:
//                    mTextMessage.setText(R.string.title_notifications);
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
    }
    private void initCargo()
    {
        Cargo binggan=new Cargo("雀巢脆脆鲨威化奶香巧克力夹心饼干500g散装休闲食品零食包邮喜糖20.0/件",R.drawable.binggan);
        cargoList.add(binggan);
        Cargo benzi=new Cargo("十二星座小清新大学生笔记本加厚复古韩国日记本子简约创意记事本40.0/件",R.drawable.benzi);
        cargoList.add(benzi);
        Cargo gangbi=new Cargo("包邮批发 爱好墨囊钢笔小学生抽墨水墨囊两用 学生写字练字钢笔100.0/件",R.drawable.gangbi);
        cargoList.add(gangbi);
        Cargo maojin=new Cargo("洁丽雅毛巾纯棉男士英伦全棉洗脸批发吸水大毛巾家用成人柔软面巾16.0/件",R.drawable.maojin);
        cargoList.add(maojin);
        Cargo suannai=new Cargo("新希望 airsnow轻爱常温原味酸奶整箱200g*12盒 巴氏杀菌酸牛奶40.0/件",R.drawable.suannai);
        cargoList.add(suannai);
        Cargo yagao=new Cargo("高露洁光感劲白牙膏120g*6 家庭大包装 美白去除牙渍30.0/件",R.drawable.yagao);
        cargoList.add(yagao);
    }
}
