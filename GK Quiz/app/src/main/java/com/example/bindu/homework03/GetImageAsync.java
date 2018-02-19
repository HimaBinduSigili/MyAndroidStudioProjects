package com.example.bindu.homework03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by bindu on 2/16/2018.
 */

public class GetImageAsync extends AsyncTask<String,Void,Bitmap> {
    ImageInterface imageInterface;
    HttpURLConnection connection=null;
    Bitmap image = null;

    public GetImageAsync(ImageInterface imageInterface) {
        this.imageInterface = imageInterface;
    }

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
        imageInterface.handleImagePreExec();
    }

    @Override
    protected void onPostExecute(Bitmap image) {
      imageInterface.handleImagePostExec(image);
    }
    public interface ImageInterface{
        void handleImagePreExec();
     void handleImagePostExec(Bitmap image);
    }
}

