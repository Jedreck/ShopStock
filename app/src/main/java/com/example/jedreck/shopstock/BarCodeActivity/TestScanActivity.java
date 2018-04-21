package com.example.jedreck.shopstock.BarCodeActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jedreck.shopstock.R;

public class TestScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scan);
        Button button = findViewById(R.id.scan_button
        );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestScanActivity.this, CaptureActivity.class);
                startActivity(intent);
            }
        });
    }
}
