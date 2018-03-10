package com.example.bindu.inclassfall2013;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int i=0;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
    }
    public void getRED(View v){
        i++;
        tv.setText("No. Of Buttons Clicked :"+i);
        getWindow().getDecorView().setBackgroundColor(Color.RED);
        Log.d("demo","red");
    }
    public void getBLACK(View v){
        i++;
        tv.setText("No. Of Buttons Clicked :"+i);
        tv.setTextColor(Color.WHITE);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        Log.d("demo","black");
    }
    public void getWHITE(View v){
        i++;
        tv.setText("No. Of Buttons Clicked :"+i);
        tv.setTextColor(Color.BLACK);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        Log.d("demo","white");
    } public void getPINK(View v){
        i++;
        tv.setText("No. Of Buttons Clicked :"+i);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#EC407A"));
        Log.d("demo","pink");
    }

}
