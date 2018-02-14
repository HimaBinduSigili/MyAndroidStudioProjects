package com.example.bindu.inclass05;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Assignment: Inclass 05
 * FileName: MainActivity.java
 * Group 15: Hima Bindu Sigili
 *           Bryson Shannon
 */
public class MainActivity extends AppCompatActivity implements GetImageAsync.ImageInterface{
String[] keywords = null;
List<String> finalUrls = new ArrayList<>();
TextView search,prog;
ProgressBar pg;
int i=0;
ImageView next,prev,imageView;
Button Go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Activity");
        search =(TextView)findViewById(R.id.textViewSearch);
        Go=(Button)findViewById(R.id.buttonGo);
        prog=(TextView)findViewById(R.id.textViewProgress);
        pg=(ProgressBar)findViewById(R.id.progressBar);
        prog.setVisibility(View.INVISIBLE);
        pg.setVisibility(View.INVISIBLE);
        imageView=(ImageView)findViewById(R.id.imageView);
        next=(ImageView)findViewById(R.id.imageViewNext);
        next.setEnabled(false);
        prev =(ImageView)findViewById(R.id.imageViewPrev);
        prev.setEnabled(false);
        if(isNetworkConnected()){
            new GetKeywords().execute("http://dev.theappsdr.com/apis/photos/keywords.php");
        }else{
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Choose a Keyword");
                    builder.setItems(keywords, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            search.setText(keywords[which]);
                            new GetURLsAsync().execute("http://dev.theappsdr.com/apis/photos/index.php?keyword="+keywords[which]);

                        }
                    });
                    builder.create().show();


                }else
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isNetworkConnected()){
                        if(i<finalUrls.size()-1){
                            i++;
                        }else
                            i=0;
                        new GetImageAsync(MainActivity.this).execute(finalUrls.get(i));
                    }
                    else
                        Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            });
            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isNetworkConnected()){
                        if(i>0){
                            i--;
                        }else
                            i=finalUrls.size()-1;
                        new GetImageAsync(MainActivity.this).execute(finalUrls.get(i));
                    }
                    else
                        Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
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

    @Override
    public void handlePostImage(Bitmap image) {
        prog.setVisibility(View.INVISIBLE);
        pg.setVisibility(View.INVISIBLE);
        imageView.setImageBitmap(image);
    }

    @Override
    public void handlePreImage() {
        prog.setVisibility(View.VISIBLE);
        pg.setVisibility(View.VISIBLE);
    }

    public class GetKeywords extends AsyncTask<String, Void, String> {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    reader =new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String Line ="";
                    while ((Line=reader.readLine())!=null){
                        stringBuilder.append(Line);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String keys) {
            keywords = keys.split(";");
        }
    }
    public class GetURLsAsync extends AsyncTask<String,Void,List<String>>{

        @Override
        protected List<String> doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    reader =new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String Line ="";
                    while ((Line=reader.readLine())!=null){
                        stringBuilder.append(Line);
                        stringBuilder.append(",");
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(connection!=null){
                    connection.disconnect();
                }
                if(reader!=null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return Arrays.asList(stringBuilder.toString().split(","));

        }

        @Override
        protected void onPostExecute(List<String> strings) {

            if (strings.size()==1 && (strings.get(0).equals("") ||strings.get(0).length()==0)){
                Toast.makeText(MainActivity.this, "No Images found", Toast.LENGTH_LONG).show();
                imageView.setImageBitmap(null);
            }
                finalUrls = strings;
            if(finalUrls.size()>1){
                next.setEnabled(true);
                prev.setEnabled(true);
                new GetImageAsync(MainActivity.this).execute(finalUrls.get(i));
            }else if(finalUrls.size()==1&&!strings.get(0).equals(""))
            new GetImageAsync(MainActivity.this).execute(finalUrls.get(i));
        }
    }

}
