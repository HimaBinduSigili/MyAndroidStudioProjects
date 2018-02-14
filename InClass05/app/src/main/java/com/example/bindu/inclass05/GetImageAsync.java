package com.example.bindu.inclass05;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Assignment: Inclass 05
 * FileName: GetImageAsync.java
 * Group 15: Hima Bindu Sigili
 *           Bryson Shannon
 */

public class GetImageAsync extends AsyncTask<String,Integer,Void> {
    Bitmap image = null;
    ImageInterface imageInterface;

    public GetImageAsync(ImageInterface Interface) {
      imageInterface= Interface;
    }

    @Override
    protected void onPreExecute() {
        imageInterface.handlePreImage();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(image!=null){
        imageInterface.handlePostImage(image);
       }
    }

    @Override
    protected Void doInBackground(String... strings) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(strings[0]);
            connection=(HttpURLConnection)url.openConnection();
            connection.connect();
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                image=BitmapFactory.decodeStream(connection.getInputStream());
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
        return null;
    }
    public interface ImageInterface {
        public void handlePostImage(Bitmap image);
        public void handlePreImage();
    }
}
