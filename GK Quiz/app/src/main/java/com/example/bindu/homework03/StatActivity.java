package com.example.bindu.homework03;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class StatActivity extends AppCompatActivity {
TextView percent,finaldisplay;
ProgressBar pg;
Button quit, tryagain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        setTitle("");
        percent=(TextView)findViewById(R.id.textViewpercent);
        finaldisplay=(TextView)findViewById(R.id.textViewfinal);
        pg=(ProgressBar)findViewById(R.id.progressBar3);
        quit=(Button)findViewById(R.id.buttonqu);
        tryagain=(Button)findViewById(R.id.buttontryagain);
        if(isNetworkConnected()){
            if(getIntent()!=null && getIntent().getExtras()!=null){
                int count = getIntent().getExtras().getInt(TriviaActivity.RESULT);
                int siz= getIntent().getExtras().getInt(TriviaActivity.RSIZE);
                int x=  (count*100)/siz;
                percent.setText(x+"%");
                pg.setProgress(x);
                if(x/100<1)
                    finaldisplay.setText("Try again and see if you can get all the correct answers!");
                else if(count/siz==1)
                    finaldisplay.setText("Congrats!! all are correct");

            }
        }else
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j= new Intent();
                setResult(RESULT_CANCELED,j);
                finish();
            }
        });
        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // new GetTriviaContents(StatActivity.this).execute("http://dev.theappsdr.com/apis/trivia_json/trivia_text.php");
                if(getIntent()!=null&&getIntent().getExtras()!=null){
                    percent.setText("");
                    pg.setProgress(0);
                    Intent j= new Intent();
                    setResult(RESULT_OK,j);
                }
                finish();
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
}
