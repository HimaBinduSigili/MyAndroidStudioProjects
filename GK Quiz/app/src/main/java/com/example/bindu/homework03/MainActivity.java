package com.example.bindu.homework03;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetTriviaContents.IData{
ProgressBar prog;
TextView progLoading;
ArrayList<Question> finalqu=new ArrayList<>();
Button start,exit;
ImageView imageView;
public static String QUESTION_KEY = "Question";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        prog =(ProgressBar)findViewById(R.id.progressBar);
        progLoading = (TextView)findViewById(R.id.textViewProgress);
        prog.setVisibility(View.INVISIBLE);
        progLoading.setVisibility(View.INVISIBLE);
        imageView=(ImageView)findViewById(R.id.imageView2);
        imageView.setVisibility(View.INVISIBLE);
        start=(Button)findViewById(R.id.buttonStart);
        start.setEnabled(false);
        exit=(Button)findViewById(R.id.buttonExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(isNetworkConnected()){
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
            new GetTriviaContents(MainActivity.this).execute("http://dev.theappsdr.com/apis/trivia_json/trivia_text.php");
        }else
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,TriviaActivity.class);
                i.putExtra(QUESTION_KEY,finalqu);
                startActivity(i);
            }
        });
    }
    public boolean isNetworkConnected(){
        ConnectivityManager connection =(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= (NetworkInfo)connection.getActiveNetworkInfo();
        if(networkInfo==null || !networkInfo.isConnected() || (networkInfo.getType()!=ConnectivityManager.TYPE_WIFI && networkInfo.getType()!=ConnectivityManager.TYPE_MOBILE))
            return false;
        return true;
    }

    @Override
    public void handlePreexecute() {
        prog.setVisibility(View.VISIBLE);
        progLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void handlePostexecute(ArrayList<Question> finalq) {
        prog.setVisibility(View.INVISIBLE);
        finalqu=finalq;
        start.setEnabled(true);
        imageView.setVisibility(View.VISIBLE);
        progLoading.setText("Trivia Ready");
        Log.d("demo",finalq.toString());
    }
}
