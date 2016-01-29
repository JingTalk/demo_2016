package com.jinghuang.demo.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.jinghuang.demo.R;

/**
 * Created by hakimhuang on 2016/1/26.
 */
public class ChatActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity003_chart);

        Button btnPie = (Button) findViewById(R.id.btn_pie);
        Button btnLine1 = (Button) findViewById(R.id.btn_line1);
        Button btnLine2 = (Button) findViewById(R.id.btn_line2);
        Button btnLineColor = (Button) findViewById(R.id.btn_lineColor);
        Button btnBar = (Button) findViewById(R.id.btn_bar);
        Button btnBarHorizontal = (Button) findViewById(R.id.btn_barHorizontal);
        Button btnBarAnother = (Button) findViewById(R.id.btn_barAnother);
        Button btnBarMulti = (Button) findViewById(R.id.btn_barMultiDataset);

        btnPie.setOnClickListener(this);
        btnLine1.setOnClickListener(this);
        btnLine2.setOnClickListener(this);
        btnLineColor.setOnClickListener(this);
        btnBar.setOnClickListener(this);
        btnBarHorizontal.setOnClickListener(this);
        btnBarAnother.setOnClickListener(this);
        btnBarMulti.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.btn_pie:
                i = new Intent(ChatActivity.this, PieChartActivity.class);
                startActivity(i);
                break;
            case R.id.btn_line1:
                i = new Intent(ChatActivity.this, LineChartActivity1.class);
                startActivity(i);
                break;
            case R.id.btn_line2:
                i = new Intent(ChatActivity.this, LineChartActivity2.class);
                startActivity(i);
                break;
            case R.id.btn_lineColor:
                i = new Intent(ChatActivity.this, LineChartActivityColored.class);
                startActivity(i);
                break;
            case R.id.btn_bar:
                i = new Intent(ChatActivity.this, BarChartActivity.class);
                startActivity(i);
                break;
            case R.id.btn_barHorizontal:
                i = new Intent(ChatActivity.this, HorizontalBarChartActivity.class);
                startActivity(i);
                break;
            case R.id.btn_barAnother:
                i = new Intent(ChatActivity.this, AnotherBarActivity.class);
                startActivity(i);
                break;
            case R.id.btn_barMultiDataset:
                i = new Intent(ChatActivity.this, BarChartActivityMultiDataset.class);
                startActivity(i);
                break;
        }
    }
}
