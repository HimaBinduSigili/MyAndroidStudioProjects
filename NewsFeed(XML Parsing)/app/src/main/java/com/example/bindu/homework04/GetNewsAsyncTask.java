package com.example.bindu.homework04;

import android.os.AsyncTask;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
        * Created by bindu on 2/23/2018.
 /**
 * Assignment: Homework04
 * FileName: MainActivity.java
 * Group 15: Hima Bindu Sigili
 *           Bryson Shannon
 */
public class GetNewsAsyncTask extends AsyncTask<String,Void,ArrayList<Article>> {
    NewsInterface newsInterface;

    public GetNewsAsyncTask(NewsInterface newsInterface) {
        this.newsInterface = newsInterface;
    }

    @Override
    protected ArrayList<Article> doInBackground(String... strings) {
        HttpURLConnection connection = null;
        ArrayList<Article> result = null;
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                result= new ArrayList<>();
               // result = ArticleParser.ArticleSAXParser.ParseArticles(connection.getInputStream());
                result = ArticleParser.ArticlesPULLParser.parseArticles(connection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }/* catch (SAXException e) {
            e.printStackTrace();
        }*/ catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    @Override
    protected void onPreExecute() {
        newsInterface.handlePreExecute();

    }

    @Override
    protected void onPostExecute(ArrayList<Article> articles) {
        newsInterface.handlePostExecute(articles);
    }

    public interface NewsInterface{
        void handlePreExecute();
        void handlePostExecute(ArrayList<Article> article);
    }
}

