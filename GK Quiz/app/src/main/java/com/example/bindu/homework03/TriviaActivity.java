package com.example.bindu.homework03;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity implements GetImageAsync.ImageInterface {
ArrayList<Question> questions = null;
Button q,time,quit,next;
TextView ques;
ImageView imageView;
ProgressBar prog;
RadioGroup radioGroup;
TextView progloading;
int correctAnswers= 0;
public static String RESULT="Result";
public static String RSIZE="Resultsize";
int i=0;// i is the Index of Arraylist
CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        setTitle("");
        q=(Button)findViewById(R.id.buttonQnumber);
        time=(Button)findViewById(R.id.buttonTime);
        quit=(Button)findViewById(R.id.buttonQuit);
        next=(Button)findViewById(R.id.buttonnext);
        ques=(TextView)findViewById(R.id.textViewQuestion);
        imageView=(ImageView)findViewById(R.id.imageView2);
        prog=(ProgressBar)findViewById(R.id.progressBar2);
        progloading=(TextView)findViewById(R.id.textViewProg);
        progloading.setVisibility(View.INVISIBLE);
        prog.setVisibility(View.INVISIBLE);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        if(isNetworkConnected()){
            if(getIntent()!=null&& getIntent().getExtras()!=null ){
                timer= new CountDownTimer(120000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        time.setText("Time Left: " + millisUntilFinished / 1000+"seconds");
                    }

                    public void onFinish() {
                        if(radioGroup.getCheckedRadioButtonId()==questions.get(i).getAnswer()){
                            correctAnswers++;
                        }
                        Intent intent = new Intent("com.example.bindu.homework03.intent.action.VIEW");
                        intent.putExtra(RESULT,correctAnswers);
                        intent.putExtra(RSIZE,questions.size());
                        startActivityForResult(intent,100);
                        //finish();
                    }
                }.start();
                questions= (ArrayList<Question>) getIntent().getExtras().getSerializable(MainActivity.QUESTION_KEY);
                SetDisplay(i);
            }
        }
        else
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()){
                    if(i<questions.size()-1){
                          //  i++;
                        if(radioGroup.getCheckedRadioButtonId()==questions.get(i).getAnswer()){
                            correctAnswers++;
                        }
                        i++;
                            Log.d("demo",""+correctAnswers);
                            radioGroup.removeAllViews();
                            radioGroup.clearCheck();
                            SetDisplay(i);

                    }else {
                        if(radioGroup.getCheckedRadioButtonId()==questions.get(i).getAnswer()){
                            correctAnswers++;
                        }
                        Log.d("demo",""+correctAnswers);
                       timer.cancel();
                        Intent intent = new Intent("com.example.bindu.homework03.intent.action.VIEW");
                        intent.putExtra(RESULT,correctAnswers);
                        intent.putExtra(RSIZE,questions.size());
                        startActivityForResult(intent,100);
                        //finish();


                    }
                }
                else
                    Toast.makeText(TriviaActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_CANCELED) {
                finish();

            } else if (resultCode == RESULT_OK) {
                i=0;
                correctAnswers=0;
                radioGroup.removeAllViews();
                timer.cancel();
                timer= new CountDownTimer(120000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        time.setText("Time Left: " + millisUntilFinished / 1000+"seconds");
                    }

                    public void onFinish() {
                        if(radioGroup.getCheckedRadioButtonId()==questions.get(i).getAnswer()){
                            correctAnswers++;
                        }
                        int i=0;
                        Intent intent = new Intent("com.example.bindu.homework03.intent.action.VIEW");
                        intent.putExtra(RESULT,correctAnswers);
                        intent.putExtra(RSIZE,questions.size());
                        startActivityForResult(intent,100);
                        // finish();
                    }
                }.start();
               SetDisplay(i);
            }

        }
    }
    public void SetDisplay(int k){
        q.setText("Q"+(k+1));
        new GetImageAsync(TriviaActivity.this).execute(questions.get(k).getImageURL());
     /*   if(questions.get(k).getImageURL()!=null && !questions.get(k).getImageURL().equals(""))
        Picasso.with(TriviaActivity.this).load(questions.get(k).getImageURL()).into(imageView);
        else
            imageView.setImageBitmap(null);*/
        ques.setText(questions.get(k).getQuestion());
        for(int j = 0; j < questions.get(k).getAnswers().size(); j++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(j);
            rdbtn.setText(questions.get(k).getAnswers().get(j));
            radioGroup.addView(rdbtn);
        }
    }
    public boolean isNetworkConnected(){
        ConnectivityManager connection =(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= (NetworkInfo)connection.getActiveNetworkInfo();
        if(networkInfo==null || !networkInfo.isConnected() || (networkInfo.getType()!=ConnectivityManager.TYPE_WIFI && networkInfo.getType()!=ConnectivityManager.TYPE_MOBILE))
            return false;
        return true;
    }

    @Override
    public void handleImagePreExec() {
        imageView.setImageBitmap(null);
    prog.setVisibility(View.VISIBLE);
    progloading.setVisibility(View.VISIBLE);
    }

    @Override
    public void handleImagePostExec(Bitmap bitmap) {
        progloading.setVisibility(View.INVISIBLE);
        prog.setVisibility(View.INVISIBLE);
        imageView.setImageBitmap(bitmap);

    }
}
