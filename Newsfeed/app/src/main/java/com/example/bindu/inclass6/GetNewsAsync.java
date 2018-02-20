package com.example.bindu.inclass6;
/**
 * Assignment: Inclass 06
 * FileName: MainActivity.java
 * Group 15: Hima Bindu Sigili
 *           Bryson Shannon
 */
import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by bindu on 2/19/2018.
 */

public class GetNewsAsync extends AsyncTask<String,Void,ArrayList<Article>> {
  NewsInterface newsInterface;

    public GetNewsAsync(NewsInterface newsInterface) {
        this.newsInterface = newsInterface;
    }

    @Override
    protected ArrayList<Article> doInBackground(String... strings) {

        HttpURLConnection connection = null;
        ArrayList<Article> result = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF-8");
                JSONObject root = new JSONObject(json);
                JSONArray articles1 = root.getJSONArray("articles");
                for (int i = 0; i < articles1.length(); i++) {
                    JSONObject articleJson = articles1.getJSONObject(i);
                    Article article= new Article();
                    article.author = articleJson.getString("author");
                    article.title = articleJson.getString("title");
                    article.description = articleJson.getString("description");
                    article.url = articleJson.getString("url");
                    article.urlToImage = articleJson.getString("urlToImage");
                    article.publishedAt = articleJson.getString("publishedAt");
                    JSONObject sourceJSON = articleJson.getJSONObject("source");
                    Source source= new Source();
                    source.id = sourceJSON.getString("id");
                   source.name=sourceJSON.getString("name");
                   article.source=source;
                    result.add(article);
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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
