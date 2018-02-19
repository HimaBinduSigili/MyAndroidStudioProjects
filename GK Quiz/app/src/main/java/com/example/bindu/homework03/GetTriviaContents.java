package com.example.bindu.homework03;

import android.os.AsyncTask;

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
 * Created by bindu on 2/16/2018.
 */

public class GetTriviaContents extends AsyncTask<String,Void,Void> {
IData iData;

    public GetTriviaContents(IData iData) {
        this.iData = iData;
    }

    HttpURLConnection connection = null;
BufferedReader reader=null;
StringBuilder stringBuilder = new StringBuilder();
List<String> ques = null;
    List<String> result = null;
    Question question = null;
    ArrayList<Question> finalques = new ArrayList<>();

    @Override
    protected Void doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line=reader.readLine())!=null){
                    stringBuilder.append(line);
                    stringBuilder.append(",");
                }
               ques = Arrays.asList(stringBuilder.toString().split(","));
                for (int i=0; i<ques.size();i++) {
                    result= Arrays.asList(ques.get(i).split(";"));
                    ArrayList<String> answers = new ArrayList<>();
                    for (int j=3;j<result.size()-1;j++){
                        answers.add(result.get(j));
                    }
                    question=new Question(result.get(1),result.get(2),answers,Integer.parseInt(result.get(result.size()-1)));
                    finalques.add(question);
                }

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null)
                connection.disconnect();
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        iData.handlePreexecute();
    }

    @Override
    protected void onPostExecute(Void avoid) {
        iData.handlePostexecute(finalques);
    }
    public interface IData{
         void handlePreexecute();
         void handlePostexecute(ArrayList<Question> finalquestions);
    }
}
