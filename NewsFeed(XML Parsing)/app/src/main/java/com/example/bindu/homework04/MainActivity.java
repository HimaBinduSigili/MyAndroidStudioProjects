package com.example.bindu.homework04;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
/**
 * Assignment: Homework04
 * FileName: MainActivity.java
 * Group 15: Hima Bindu Sigili
 *           Bryson Shannon
 */
public class MainActivity extends AppCompatActivity implements GetNewsAsyncTask.NewsInterface {
    Button Go;
    String[] keywords= {"Top Stories","World","U.S.","Business","Politics","Technology","Health","Entertainment","Travel","Living","Most Recent"};
    TextView show, progload, title,publishat,descr,Outof;
    //String myapiKey="18dbb5feb9a84577855df1ca27ee77be";
    ArrayList<Article> finalArticles;
    ProgressBar pg;
    ImageView imageView,Next,Prev;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Activity");
        Go=(Button)findViewById(R.id.buttonGo);
        show=(TextView)findViewById(R.id.textViewShow);
        pg=(ProgressBar)findViewById(R.id.progressBar);
        pg.setVisibility(View.INVISIBLE);
        progload=(TextView)findViewById(R.id.textViewProg);
        progload.setVisibility(View.INVISIBLE);
        imageView=(ImageView)findViewById(R.id.imageView);
        title=(TextView)findViewById(R.id.textViewtitle);
        publishat=(TextView)findViewById(R.id.textViewpublish);
        descr=(TextView)findViewById(R.id.textView5Descr);
        Outof=(TextView)findViewById(R.id.textViewoutOf);
        Next=(ImageView)findViewById(R.id.imageViewNext);
        Prev=(ImageView)findViewById(R.id.imageViewprev);
        Next.setEnabled(false);
        Prev.setEnabled(false);
        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Choose Category");
                    builder.setItems(keywords, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            show.setText(keywords[which]);
                            new GetNewsAsyncTask(MainActivity.this).execute("http://rss.cnn.com/rss/"+getKeywords1(keywords)[which]+".rss");
                        }
                    });
                    builder.create().show();


                }else
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()){
                    if(i<finalArticles.size()-1){
                        i++;
                        DisplayResults(i);
                    }else{
                        i=0;
                        DisplayResults(i);
                    }


                }else
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()){
                    if(i>0){
                        i--;
                        DisplayResults(i);
                    }else{
                        i=finalArticles.size()-1;
                        DisplayResults(i);
                    }


                }else
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalArticles.get(i).link));
                startActivity(browserIntent);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalArticles.get(i).link));
                startActivity(browserIntent);
            }
        });
    }
    public boolean isNetworkConnected(){
        ConnectivityManager connection = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connection.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;

    }
    public String[] getKeywords1(String[] keys){
        String[] keys1 = {"cnn_topstories","cnn_world","cnn_us","money_latest","cnn_allpolitics","cnn_tech","cnn_health","cnn_showbiz","cnn_travel","cnn_living","cnn_latest"};
        return keys1;
    }
    public void DisplayResults(int k){
        Log.d("demo",""+finalArticles.get(k));
        title.setText(finalArticles.get(k).title);
        publishat.setText(finalArticles.get(k).publishedDate);
        String des= Html.escapeHtml(Html.fromHtml(finalArticles.get(k).description));
        if(!des.equals(null)||des.length()!=0)
        des=des.substring(0,des.indexOf("&"));
        if(finalArticles.get(k).mediaImage!=null && !finalArticles.get(k).mediaImage.equals(""))
            Picasso.with(MainActivity.this).load(finalArticles.get(k).mediaImage).into(imageView);
        else
            imageView.setImageBitmap(null);
        if(!des.equals(null))
            descr.setText(des);
        else
            descr.setText(null);
        Log.d("demo",""+des);
        Outof.setText(i+1+" out of "+finalArticles.size());
    }
    @Override
    public void handlePreExecute() {
        finalArticles= new ArrayList<>();
        pg.setVisibility(View.VISIBLE);
        progload.setVisibility(View.VISIBLE);

    }

    @Override
    public void handlePostExecute(ArrayList<Article> articles) {
        if(articles.size()==0||articles==null||articles.equals(null))
            Toast.makeText(this, "No News found", Toast.LENGTH_SHORT).show();
        finalArticles=articles;
        Log.d("demo",finalArticles.toString());
        pg.setVisibility(View.INVISIBLE);
        progload.setVisibility(View.INVISIBLE);
        if(!(finalArticles.size()==0 || finalArticles.size()==1)){
            Next.setEnabled(true);
            Prev.setEnabled(true);
        }
        i=0;
        DisplayResults(i);
    }

}
