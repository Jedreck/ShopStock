package com.example.jedreck.shopstock.MajorSearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.jedreck.shopstock.BarCodeActivity.CaptureActivity;
import com.example.jedreck.shopstock.BarCodeActivity.TestScanActivity;
import com.example.jedreck.shopstock.Bean.StockBean;
import com.example.jedreck.shopstock.FullInfoActivity.FullInfoActivity;
import com.example.jedreck.shopstock.Internet.RequestManager;
import com.example.jedreck.shopstock.OUTPart.OutActivity;
import com.example.jedreck.shopstock.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private List<StockBean> stockBeanList=new ArrayList<>();
    StockBeanAdapter adapter;
    ListView listView;
    Intent intent;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    //mTextMessage.setText(R.string.title_shop);
                    return true;
                case R.id.navigation_in:
                    //intent = new Intent(MainActivity.this, InActivity.class);
                    //startActivity(intent);
                    return true;
                case R.id.navigation_out:
                    intent = new Intent(MainActivity.this, OutActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView=(ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        listView = (ListView) findViewById(R.id.list_view);

        final SearchView sv=findViewById(R.id.searchView);
        // 设置该SearchView内默认显示的提示文本
        sv.setQueryHint("查找");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StockBean stockBean = stockBeanList.get(position);
                intent=new Intent(MainActivity.this, FullInfoActivity.class);
                startActivity(intent);
            }
        });


        Button searchbutton=(Button) findViewById(R.id.searchbutton);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendRequestWithOkHttp(sv.getQuery().toString());
            }
        });


        //searchView监听事件
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private String TAG = getClass().getSimpleName();
            @Override
            public boolean onQueryTextChange(String queryText) {
                Log.d(TAG, "onQueryTextChange = " + queryText);
                String selection = ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY + " LIKE '%" + queryText + "%' " + " OR "
                        + ContactsContract.RawContacts.SORT_KEY_PRIMARY + " LIKE '%" + queryText + "%' ";
                // String[] selectionArg = { queryText };
                /*mCursor = getContentResolver().query(RawContacts.CONTENT_URI, PROJECTION, selection, null, null);
                mAdapter.swapCursor(mCursor); // 交换指针，展示新的数据*/
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String queryText) {
                sendRequestWithOkHttp(queryText);
                Log.d(TAG, "onQueryTextSubmit = " + queryText);
                if (sv != null) {
                    // 得到输入管理对象
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        // 这将让键盘在所有的情况下都被隐藏，但是一般我们在点击搜索按钮后，输入法都会乖乖的自动隐藏的。
                        imm.hideSoftInputFromWindow(sv.getWindowToken(), 0); // 输入法如果是显示状态，那么就隐藏输入法
                    }
                    sv.clearFocus(); // 不获取焦点
                }
                return true;
            }
        });
    }

    private void sendRequestWithOkHttp(final String query) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    // 指定访问的服务器地址
                    RequestBody requestBody=new FormBody.Builder()
                            .add("data",query)
                            .build();
                    //Log.d("MainActivity","data:"+query);
                    Request request = RequestManager.getFuzzyIDOrName(requestBody);
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("MainActivity","response:"+responseData);
                    stockBeanList.clear();
                    stockBeanList=StockBean.json2Objectives(responseData);
                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private  void showResponse(final String response)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                adapter=new StockBeanAdapter(MainActivity.this,R.layout.stockbean_item,stockBeanList);
                listView.setAdapter(adapter);
            }
        });
    }


}


