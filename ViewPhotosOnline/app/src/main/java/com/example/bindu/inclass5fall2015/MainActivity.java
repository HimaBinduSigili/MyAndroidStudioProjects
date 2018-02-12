package com.example.bindu.inclass5fall2015;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
List<String> pids = null;
ImageView imageView;
ImageView next,prev;
TextView prog;
ProgressBar progressBar;
int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        prog =(TextView)findViewById(R.id.progress);
        prog.setVisibility(View.INVISIBLE);
        progressBar =(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        next = (ImageView)findViewById(R.id.imageView3);
        prev=(ImageView)findViewById(R.id.imageView2);
        if(isNetworkConnected()){
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
            new GetPhotoIDAsync().execute("http://dev.theappsdr.com/lectures/inclass_photos/index.php");

        }else{
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<pids.size()-1)
                    i++;
                else
                    i=0;
                if(isNetworkConnected())
                new GetPhotoAsync().execute("http://dev.theappsdr.com/lectures/inclass_photos/index.php?pid="+pids.get(i));
                else
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>0)
                    i--;
                else
                    i=pids.size()-1;
                if(isNetworkConnected())
                    new GetPhotoAsync().execute("http://dev.theappsdr.com/lectures/inclass_photos/index.php?pid="+pids.get(i));
                else
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean isNetworkConnected(){
        ConnectivityManager connection = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connection.getActiveNetworkInfo();
        if(networkInfo==null||!networkInfo.isConnected()||(networkInfo.getType()!=ConnectivityManager.TYPE_MOBILE)&&networkInfo.getType()!=ConnectivityManager.TYPE_WIFI)
            return false;
        return true;
    }
    public class GetPhotoIDAsync extends AsyncTask<String,Void,List<String>>{
    HttpURLConnection connection=null;
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader reader = null;
    List<String> photoIds = null;
        @Override
        protected List<String> doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                connection.setRequestMethod("GET");
                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line ="";
                    while((line=reader.readLine())!=null){
                        stringBuilder.append(line);
                        stringBuilder.append(",");
                    }
                    photoIds = Arrays.asList(stringBuilder.toString().split(","));
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
            return photoIds;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            pids = strings;
            new GetPhotoAsync().execute("http://dev.theappsdr.com/lectures/inclass_photos/index.php?pid="+strings.get(0));
        }
    }
    public class GetPhotoAsync extends AsyncTask<String,Integer,Bitmap>{
        HttpURLConnection connection=null;
        ArrayList<String> photoIds = null;
        Bitmap image = null;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    image = BitmapFactory.decodeStream(connection.getInputStream());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(connection!=null){
                    connection.disconnect();
                }
            }
            return image;
        }

        @Override
        protected void onPreExecute() {
            prog.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap image) {
           progressBar.setVisibility(View.INVISIBLE);
            prog.setVisibility(View.INVISIBLE);
           imageView.setImageBitmap(image);
        }
    }
}
